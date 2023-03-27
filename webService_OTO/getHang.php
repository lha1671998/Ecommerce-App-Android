<?php
    require 'dbConnect.php';

    class HangOTO{
            function __construct($mahang,$tenhang,$hinhanh){
                    $this->mahang = $mahang;
                    $this->tenhang= $tenhang;
                    $this->hinhanh = $hinhanh;
            }           
    }

    $query = "select * from HangOTO";
    $data =mysqli_query($connect,$query);
    $arrayList = array();

    while($row=mysqli_fetch_assoc($data)){
        array_push($arrayList,new HangOTO($row["MaHangOTO"],$row["TenHangOTO"],$row["AnhHang"]));
    }

    header("Content-type:application/json");
    echo json_encode( $arrayList, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
    
?>

