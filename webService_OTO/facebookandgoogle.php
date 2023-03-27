<?php

try {
    if ($_SERVER["REQUEST_METHOD"] == "POST") {
        $connect = mysqli_connect("localhost", "root", "", "qloto");
        mysqli_query($connect, "SET NAMES 'utf8'");
        if (isset($_POST["tenKH"], $_POST["emailKH"]))
       
        $TenKH = $_POST["tenKH"];
        $Email = $_POST["emailKH"];
        $query = "INSERT INTO `khachhang`(`MaKH`, `TenKH`, `Email`, `Password`, `DiaChi`, `SoDT`) 
     VALUES (Null,'$TenKH','$Email','','','')";
        $data = mysqli_query($connect, $query);
        if ($data != false) {
            echo 1;
        } else {
            echo 0;
        }
    }
} catch (Exception $e) {
    header("Content-type:application/json");
    echo json_encode($e, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
}
