<?php
require('dbconnect.php');
class KhachHang
{

    function __construct($MaKH, $TenKH, $Email, $Password, $DiaChi, $SoDT)
    {
        $this->MaKH = $MaKH;
        $this->TenKH = $TenKH;
        $this->Email = $Email;
        $this->Password = $Password;
        $this->DiaChi = $DiaChi;
        $this->SoDT = $SoDT;
    }
}
$query = "select * from khachhang";
$data = mysqli_query($connect, $query);
$arraylist = array();

while ($row = mysqli_fetch_assoc($data)) {
    array_push($arraylist, new KhachHang($row["MaKH"], $row["TenKH"], $row["Email"], $row["Password"], $row["DiaChi"], $row["SoDT"]));
}

header("Content-type:application/json");
echo json_encode($arraylist, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);



?>