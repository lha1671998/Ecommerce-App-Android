-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th3 19, 2023 lúc 04:44 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `qloto`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `chitiethoadon`
--

CREATE TABLE `chitiethoadon` (
  `MaHD` int(50) NOT NULL,
  `MaOTO` varchar(50) NOT NULL,
  `DonGia` float NOT NULL,
  `SoLuong` int(11) NOT NULL,
  `TongTien` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `chitiethoadon`
--

INSERT INTO `chitiethoadon` (`MaHD`, `MaOTO`, `DonGia`, `SoLuong`, `TongTien`) VALUES
(2, 'B2', 1313, 3, 123123000),
(2, 'B4', 43424, 242342, 2342),
(3, 'FLXR', 13, 13, 31312);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hangoto`
--

CREATE TABLE `hangoto` (
  `MaHangOTO` varchar(50) NOT NULL,
  `TenHangOTO` varchar(255) NOT NULL,
  `AnhHang` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `hangoto`
--

INSERT INTO `hangoto` (`MaHangOTO`, `TenHangOTO`, `AnhHang`) VALUES
('B', 'BMW', 'bmw.png'),
('FRR', 'FERRARI', 'ferrari.png'),
('HD', 'HYUNDAI', 'hyundai.png'),
('LMB', 'LAMBORGHINI', 'lambor.png'),
('LX', 'LEXUS', 'lexus.png'),
('MER', 'MERCEDES', 'mer.png'),
('POR', 'PORSCHE', 'por.png'),
('RR', 'ROLL ROYCE', 'rr.png'),
('TYT', 'TOYOTA', 'tyt.png');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hoadon`
--

CREATE TABLE `hoadon` (
  `MaHD` int(11) NOT NULL,
  `MaKH` int(11) DEFAULT NULL,
  `SDT` varchar(255) DEFAULT NULL,
  `DiaChi` varchar(255) DEFAULT NULL,
  `TongTien` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `hoadon`
--

INSERT INTO `hoadon` (`MaHD`, `MaKH`, `SDT`, `DiaChi`, `TongTien`) VALUES
(2, 3, '1312312', '31 tdt', 2132),
(3, 1, '113', '113 THD', 123);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `khachhang`
--

CREATE TABLE `khachhang` (
  `MaKH` int(50) NOT NULL,
  `TenKH` varchar(255) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `Password` varchar(50) NOT NULL,
  `DiaChi` varchar(255) NOT NULL,
  `SoDT` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Đang đổ dữ liệu cho bảng `khachhang`
--

INSERT INTO `khachhang` (`MaKH`, `TenKH`, `Email`, `Password`, `DiaChi`, `SoDT`) VALUES
(1, 'an1', 'an1@gmail.com', '123456', '31 tdt', '1313'),
(3, 'an2', 'an2@gmail.com', '123456', '32 tdt', '113143');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `oto`
--

CREATE TABLE `oto` (
  `MaOTO` varchar(50) NOT NULL,
  `TenOTO` varchar(50) NOT NULL,
  `MaHangOTO` varchar(50) NOT NULL,
  `DonGiaOTO` int(11) NOT NULL,
  `SoLuongOTO` int(11) NOT NULL,
  `AnhOTO` varchar(255) NOT NULL,
  `MaPK` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `oto`
--

INSERT INTO `oto` (`MaOTO`, `TenOTO`, `MaHangOTO`, `DonGiaOTO`, `SoLuongOTO`, `AnhOTO`, `MaPK`) VALUES
('B1', 'BMW 118i', 'B', 25500, 20, 'b1.png', 'HB'),
('B2', ' BMW 2 Series Active Tourer', 'B', 36600, 20, 'b2.png', 'MN'),
('B4', 'BMW 430i', 'B', 45600, 20, 'b4.png', 'CP'),
('B5', 'BMW 530Li', 'B', 56700, 20, 'b5.png', 'SD'),
('B7', 'BMW 730Li', 'B', 87800, 20, 'b7.png', 'LXR'),
('BI', 'BMW i8', 'B', 147500, 20, 'bi.png', 'SC'),
('BX5', 'BMW X5 xDrive40i', 'B', 61700, 20, 'bx5.png', 'SVCR'),
('FCP', 'Ferrari 488 GTB', 'FRR', 331000, 20, 'f488.png', 'CP'),
('FCRS', 'Ferrari Purosangue', 'FRR', 320000, 20, 'fc.png', 'SVCR'),
('FLXR', 'Ferrari GTC4Lusso', 'FRR', 350000, 15, 'flxr.png', 'LXR'),
('FSC', 'Ferrari LaFerrari', 'FRR', 1250000, 12, 'fla.png', 'SC'),
('HCP', 'Hyundai Genesis', 'HD', 67000, 20, 'hdcp.png', 'CP'),
('HDLXY', ' Hyundai Genesis G90', 'HD', 88400, 20, 'hdlxy.png', 'LXR'),
('HDMN', 'Hyundai Venue', 'HD', 19000, 20, 'hdmn.png', 'MN'),
('HDPK', 'Hyundai Santa Cruz', 'HD', 23990, 20, 'hdpk.png', 'PK'),
('HDSD', 'Hyundai Elantra', 'HD', 19000, 20, 'hdsd.png', 'SD'),
('HDSV', 'Hyundai Santa Fe', 'HD', 27000, 20, 'stf.png', 'SVCR'),
('HHB', 'Hyundai Elantra GT', 'HD', 21000, 20, 'hdhb.png', 'HB'),
('LA', 'Lamborghini Aventador', 'LMB', 393695, 5, 'la.png', 'CP'),
('LC', 'Lamborghini Centenario', 'LMB', 1750000, 5, 'centenario.png', 'SC'),
('LES', 'Lexus ES', 'LX', 40900, 15, 'les.png', 'SD'),
('LESV', 'Lexus LX570', 'LX', 86830, 15, 'lx570.png', 'SVCR'),
('LLC', 'Lexus LC', 'LX', 92950, 15, 'llc.png', 'LXR'),
('LLS', 'Lexus LS', 'LX', 77025, 15, 'lls.png', 'LXR'),
('LRC', 'Lexus RC', 'LX', 41145, 15, 'lrc.png', 'CP'),
('LSVCR', 'Lexus LX570', 'LX', 86830, 15, 'lx570.png', 'SVCR'),
('LU', 'Lamborghini Urus', 'LMB', 218000, 5, 'urus.png', 'LXR'),
('MERA', 'Mercedes-Benz A-Class ', 'MER', 33650, 15, 'merhb.png', 'HB'),
('MERCP', 'Mercedes-Benz C-Class Coupe', 'MER', 42650, 20, 'mercp.png', 'CP'),
('MERGT', 'Mercedes-AMG GT', 'MER', 116950, 15, 'mergt.png', 'SC'),
('MERLXR', 'Mercedes-Benz S-Class', 'MER', 110850, 10, 'mers.png', 'LXR'),
('MERSD', 'Mercedes-Benz E-Class', 'MER', 54250, 10, 'mere.png', 'SD'),
('MERSVCR', 'Mercedes-Benz GLC-Class', 'MER', 54250, 15, 'merglc.png', 'SVCR'),
('MERV', 'Mercedes-Benz Metris', 'MER', 28850, 12, 'mervan.png', 'MN'),
('P718', '718 Cayman', 'POR', 60500, 10, '718.PNG', 'CP'),
('P718B', '718 Boxster', 'POR', 63900, 5, '718b.png', 'CP'),
('P911GT3', '911 GT3 RS', 'POR', 216000, 7, '911gt3.png', 'SC'),
('P911SC', '911 Sport Classic ', 'POR', 225000, 3, '911sc.jpg', 'SC'),
('P911TS', '911 Turbo S', 'POR', 208000, 5, '911ts.png', 'SC'),
('PCY', 'Porsche Cayenne', 'POR', 71000, 8, 'pcy.png', 'SVCR'),
('PMC', 'Porsche Macan ', 'POR', 55700, 10, 'pmc.png', 'SVCR'),
('PNMRGTS', ' Panamera GTS', 'POR', 132000, 10, 'pnmr.png', 'SD'),
('PP4T', 'Panamera 4 Turismo', 'POR', 98200, 10, 'pp4t.png', 'HB'),
('PT4CT', 'Taycan 4 Cross Turismo', 'POR', 92250, 15, 'pt4ct.png', 'HB'),
('PTC4S', 'Taycan 4S', 'POR', 103800, 5, 'tc4s.png', 'SD'),
('RRCLN', 'Rolls-Royce Cullinan', 'RR', 355000, 8, 'rrcln.png', 'SVCR'),
('RRD', 'Rolls-Royce Dawn ', 'RR', 356500, 10, 'rrd.png', 'CP'),
('RRG', 'Rolls-Royce Ghost', 'RR', 420000, 5, 'rrg.png', 'SD'),
('RRPT', 'Rolls-Royce Phantom', 'RR', 480000, 5, 'rrpt.png', 'SD'),
('RRW', 'Rolls-Royce Wraith', 'RR', 380000, 10, 'rrw.png', 'CP'),
('TCR', 'Toyota Carmy', 'TYT', 25000, 15, 'tcmr.png', 'SD'),
('TCRLHB', 'Corolla Hatback', 'TYT', 24000, 10, 'tytcrlhb.png', 'HB'),
('TFTN', 'Toyota Fortuner', 'TYT', 51000, 20, 'ftn.png', 'SVCR'),
('TGR86', 'Toyota GR 86 ', 'TYT', 29000, 5, 'tgr86.png', 'CP'),
('THL', 'Toyota Hilux', 'TYT', 28000, 10, 'hilux.png', 'PK'),
('TLCRL', 'Land Cruiser Luxury', 'TYT', 91000, 5, 'lcruiser.png', 'LXR'),
('TSPGR', 'Toyota Supra GR', 'TYT', 56000, 5, 'spgr.png', 'SC'),
('TYS', 'Toyota Yaris', 'TYT', 18000, 16, 'yaris.png', 'MN');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `phankhuc`
--

CREATE TABLE `phankhuc` (
  `MaPK` varchar(50) NOT NULL,
  `TenPK` varchar(255) NOT NULL,
  `AnhPK` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `phankhuc`
--

INSERT INTO `phankhuc` (`MaPK`, `TenPK`, `AnhPK`) VALUES
('CP', 'Couple', 'cp.png'),
('HB', 'Hachtback', 'hb.png'),
('LXR', 'Luxury', 'luxury.png'),
('MN', 'Mini/Van', 'vm.png'),
('PK', 'Pickup', 'pickup.png'),
('SC', 'Super', 'super.png'),
('SD', 'Sedan', 'sedan.png'),
('SVCR', 'Suv/Cross', 'suv.png');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD PRIMARY KEY (`MaHD`,`MaOTO`),
  ADD KEY `CTHD_LK_OTO` (`MaOTO`);

--
-- Chỉ mục cho bảng `hangoto`
--
ALTER TABLE `hangoto`
  ADD PRIMARY KEY (`MaHangOTO`);

--
-- Chỉ mục cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD PRIMARY KEY (`MaHD`),
  ADD KEY `pk_makh` (`MaKH`);

--
-- Chỉ mục cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  ADD PRIMARY KEY (`MaKH`);

--
-- Chỉ mục cho bảng `oto`
--
ALTER TABLE `oto`
  ADD PRIMARY KEY (`MaOTO`),
  ADD KEY `oto_lk_hangoto` (`MaHangOTO`),
  ADD KEY `oto_lk_phankhuc` (`MaPK`);

--
-- Chỉ mục cho bảng `phankhuc`
--
ALTER TABLE `phankhuc`
  ADD PRIMARY KEY (`MaPK`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  MODIFY `MaHD` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  MODIFY `MaHD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `khachhang`
--
ALTER TABLE `khachhang`
  MODIFY `MaKH` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `chitiethoadon`
--
ALTER TABLE `chitiethoadon`
  ADD CONSTRAINT `CTHD_LK_OTO` FOREIGN KEY (`MaOTO`) REFERENCES `oto` (`MaOTO`),
  ADD CONSTRAINT `fk_MaHD` FOREIGN KEY (`MaHD`) REFERENCES `hoadon` (`MaHD`);

--
-- Các ràng buộc cho bảng `hoadon`
--
ALTER TABLE `hoadon`
  ADD CONSTRAINT `pk_makh` FOREIGN KEY (`MaKH`) REFERENCES `khachhang` (`MaKH`);

--
-- Các ràng buộc cho bảng `oto`
--
ALTER TABLE `oto`
  ADD CONSTRAINT `oto_lk_hangoto` FOREIGN KEY (`MaHangOTO`) REFERENCES `hangoto` (`MaHangOTO`),
  ADD CONSTRAINT `oto_lk_phankhuc` FOREIGN KEY (`MaPK`) REFERENCES `phankhuc` (`MaPK`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
