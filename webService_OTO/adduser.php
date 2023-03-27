<?php
 require 'dbConnect.php';

try{
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $connect = mysqli_connect("localhost","root","","qloto");
        mysqli_query($connect,"SET NAMES 'utf8'");
        if(isset($_POST["tenKH"], $_POST["emailKH"], $_POST["passwordKH"], $_POST["diachiKH"],$_POST["sodtKH"]))
            $TenKH = $_POST["tenKH"];
            $Email = $_POST["emailKH"];
            $Password = $_POST["passwordKH"];
            $DiaChi = $_POST["diachiKH"];
            $SoDT = $_POST["sodtKH"];

            $query="insert into khachhang values(null,'$TenKH','$Email','$Password','$DiaChi','$SoDT')";
            //$query = "INSERT INTO `khachhang` (MaKH, TenKH, Email, Password, DiaChi, SoDT) VALUES (Null,'.$tenKH.','.$emailKH.','. $passwordKH.','. $diachiKH.','. $sodtKH.')";
            $data = mysqli_query($connect,$query);
            if($data == 1){
                echo 1;
            }
            else {
                echo 0;
            }
   }
}catch(Exception $e){
    header("Content-type:application/json");
    echo json_encode($e, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}

?>