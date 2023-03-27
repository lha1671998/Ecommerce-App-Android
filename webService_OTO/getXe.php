<?php
require 'dbConnect.php';

class OTO
{       
    function __construct($maxe, $tenxe, $mahang, $dongia, $soluongxe, $anhxe, $mapk)
    {
        $this->maxe = $maxe;
        $this->tenxe = $tenxe;
        $this->mahang = $mahang;
        $this->dongia = $dongia;
        $this->soluongxe = $soluongxe;
        $this->anhxe = $anhxe;
        $this->mapk = $mapk;
    }
}

$query = "";
if (isset($_GET['pricemin'], $_GET['pricemax'])) {
    $min = $_GET['pricemin'];
    $max = $_GET['pricemax'];
    $query = "select * from OTO Where DonGiaOTO >= $min AND DonGiaOTO <= $max";

} else if (isset($_GET['top6'])) {
    $query = "SELECT * FROM `oto` ORDER BY SoLuongOTO DESC LIMIT 6";

} else if (isset($_GET['page'])) {
    $page = $_GET['page'];
    $num_per_page = 5;
    $start_from = ($page - 1) * $num_per_page;
    $query = "SELECT * FROM OTO LIMIT $start_from,$num_per_page";

} else {
    $page = 1;
    $num_per_page = 5;
    $start_from = ($page - 1) * $num_per_page;
    $query = "SELECT * FROM OTO LIMIT $start_from,$num_per_page";
}

$data = mysqli_query($connect, $query);
$arrayList = array();

while ($row = mysqli_fetch_assoc($data)) {
    array_push($arrayList, new OTO($row["MaOTO"], $row["TenOTO"], $row["MaHangOTO"], $row["DonGiaOTO"], $row["SoLuongOTO"], $row["AnhOTO"], $row["MaPK"]));
}

header("Content-type:application/json");
echo json_encode($arrayList, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>