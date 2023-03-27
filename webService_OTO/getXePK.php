<?php
require 'dbConnect.php';


// http://localhost/webService_OTO/getXePK.php/?mapk="SD" 
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

// Lấy theo phan khuc
$query = "";
if (isset($_GET['mapk'])) {
    $mapk = $_GET['mapk'];
    $query = "select * from OTO where MaPK=$mapk";
   
} else {
    $query = "select * from OTO";
} 


$data = mysqli_query($connect, $query);
$arrayList = array();

while ($row = mysqli_fetch_assoc($data)) {
    array_push($arrayList, new OTO(
        $row["MaOTO"],
        $row["TenOTO"],
        $row["MaHangOTO"],
        $row["DonGiaOTO"],
        $row["SoLuongOTO"],
        $row["AnhOTO"],
        $row["MaPK"]
    ));
}

header("Content-type:application/json");
echo json_encode($arrayList, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>