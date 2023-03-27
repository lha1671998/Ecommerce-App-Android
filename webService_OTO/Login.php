<?php
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    $connect = mysqli_connect("localhost","root","","qloto");
    mysqli_query($connect,"SET NAMES 'utf8'");

    try{
       if(isset($_POST['email'],$_POST['password'])){
            $Email = $_POST['email'];
            $Password = $_POST['password'];
            $result = '';
            $query = "SELECT COUNT(*) AS user FROM `khachhang` WHERE Email = '$Email' AND Password = '$Password'";
            $data = mysqli_query($connect, $query);
            if (!$data) {
                $result = 0;
            } else {
                $row = $data->fetch_array(MYSQLI_ASSOC);
                if ($row['user'] == 1) {
                    $result = 1;
                } else {
                    $result = 0;
                }
            }
            echo $result;
        }
    }catch(Exception $e){
        echo $e;
    }
}
?>