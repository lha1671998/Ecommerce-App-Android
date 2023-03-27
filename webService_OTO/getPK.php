<?php
    require 'dbConnect.php';

    class HangOTO{
            function __construct($mapk,$tenpk,$hinhpk){
                    $this->mapk = $mapk;
                    $this->tenpk= $tenpk;
                    $this->hinhpk= $hinhpk;
            }           
    }

    $query = "select * from PhanKhuc";
    $data =mysqli_query($connect,$query);
    $arrayList = array();

    while($row=mysqli_fetch_assoc($data)){
        array_push($arrayList,new HangOTO($row["MaPK"],$row["TenPK"],$row["AnhPK"]));
    }

    header("Content-type:application/json");
    echo json_encode( $arrayList, JSON_UNESCAPED_UNICODE | JSON_PRETTY_PRINT);
    
?>

