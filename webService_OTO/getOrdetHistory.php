<?php
    require 'dbConnect.php';

    $makh = $_GET['makh'];
    $query1 = "SELECT * FROM `hoadon` WHERE `MaKH` = $makh";
    $data1 = mysqli_query($connect, $query1);
    $result = array();

    while ($row1 = mysqli_fetch_assoc($data1)) {
        $maHD = $row1['MaHD'];
        $query2 = "SELECT chitiethoadon.*, oto.TenOTO, oto.DonGiaOTO, oto.AnhOTO FROM `chitiethoadon` INNER JOIN `oto` ON chitiethoadon.MaOTO = oto.MaOTO  WHERE `MaHD` = $maHD";
        $data2 = mysqli_query($connect, $query2);
        $item = array();
        while ($row2 = mysqli_fetch_assoc($data2)) {
            $item[] = $row2;
        }

        $row1['item'] = $item;
        $result[] = $row1;
    }

    header("Content-type:application/json");
    echo json_encode($result, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
?>
