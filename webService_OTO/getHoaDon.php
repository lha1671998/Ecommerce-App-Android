<?php
if($_SERVER['REQUEST_METHOD'] === 'POST'){
    require 'dbConnect.php';
    if(isset($_POST['makh'], $_POST['sdt'], $_POST['diachi'], $_POST['tongtien'],$_POST['ngaygio'] )){
        $makh = $_POST['makh'];
        $sdt = $_POST['sdt'];
        $diachi = $_POST['diachi'];
        $tongtien = $_POST['tongtien'];
        $ngaygio = $_POST['ngaygio'];
        if (strlen($makh) > 0 && strlen($sdt) > 0 && strlen($tongtien)>0 && strlen($diachi)>0 )
        {
            // Thêm hóa đơn mới vào bảng hoadon
            $query =  "INSERT INTO `hoadon`(`MaHD`, `MaKH`, `SDT`, `DiaChi`, `TongTien`, `NgayGio`) 
            VALUES ('null','$makh','$sdt','$diachi','$tongtien','$ngaygio')";
            if ($connect->query($query) === TRUE) {
                $mahd1 =$connect->insert_id; // Lấy MaHD mới tạo

                // Thêm chi tiết hóa đơn vào bảng chitiethoadon
                if(isset($_POST['listSP'])){
                    $listSP = json_decode("[{ 'maXe': 'B4', 'gia': 500000, 'slXe': 2}]");
                    print_r($listSP);
                    // foreach($listSP as $item){
                    //     //print_r($item);
                    //     $maXe = $item['maXe'];
                    //     $soluong = $item['slXe'];
                    //     $dongia = $item['gia'];
                    //     $query_cthd = "INSERT INTO `chitiethoadon`(`MaHD`, `MaOTO`, `DonGia`, `SoLuong`) 
                    //                     VALUES ('$mahd1', '$maXe', '$dongia', '$soluong')";
                    //      $data =mysqli_query($connect,$query_cthd);
                    //      echo $query_cthd;
                    // }
                    // echo $query_cthd;
                }
                echo "Mã HD: " .$mahd1;
              } else {
                echo "Error: " . $query . "<br>" . $connect->error;
              }
        }
        else
        {
            echo 'Kiểm tra lại dữ liệu';
        }
    }
    
}else{
    echo "lỗi";
}
?>