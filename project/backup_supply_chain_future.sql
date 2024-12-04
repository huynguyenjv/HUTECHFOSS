-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               8.0.30 - MySQL Community Server - GPL
-- Server OS:                    Win64
-- HeidiSQL Version:             12.1.0.6537
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for supply_chain_future
CREATE DATABASE IF NOT EXISTS `supply_chain_future` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `supply_chain_future`;

-- Dumping structure for table supply_chain_future.essential_goods
DROP TABLE IF EXISTS `essential_goods`;
CREATE TABLE IF NOT EXISTS `essential_goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `category` varchar(50) NOT NULL,
  `unit` varchar(50) DEFAULT 'kg',
  `current_stock` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supply_chain_future.essential_goods: ~15 rows (approximately)
DELETE FROM `essential_goods`;
INSERT INTO `essential_goods` (`id`, `name`, `category`, `unit`, `current_stock`) VALUES
	(1, 'Gạo', 'Thực phẩm', 'kg', 10000),
	(2, 'Nước uống', 'Nước uống', 'lít', 5000),
	(3, 'Thuốc men', 'Y tế', 'hộp', 2000),
	(4, 'Lương khô', 'Thực phẩm', 'kg', 3000),
	(5, 'Chăn màn', 'Sinh hoạt', 'bộ', 1000),
	(6, 'Mì tôm', 'Thực phẩm', 'gói', 20000),
	(7, 'Dầu ăn', 'Thực phẩm', 'lít', 3000),
	(8, 'Bánh mì', 'Thực phẩm', 'ổ', 5000),
	(9, 'Đèn pin', 'Dụng cụ', 'cái', 1000),
	(10, 'Áo mưa', 'Dụng cụ', 'cái', 500),
	(11, 'Mì tôm', 'Thực phẩm', 'gói', 20000),
	(12, 'Dầu ăn', 'Thực phẩm', 'lít', 3000),
	(13, 'Bánh mì', 'Thực phẩm', 'ổ', 5000),
	(14, 'Đèn pin', 'Dụng cụ', 'cái', 1000),
	(15, 'Áo mưa', 'Dụng cụ', 'cái', 500);

-- Dumping structure for table supply_chain_future.provinces
DROP TABLE IF EXISTS `provinces`;
CREATE TABLE IF NOT EXISTS `provinces` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `region` varchar(50) NOT NULL,
  `population` int NOT NULL,
  `disaster_level` int DEFAULT '0',
  `relief_priority` int DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=73 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supply_chain_future.provinces: ~43 rows (approximately)
DELETE FROM `provinces`;
INSERT INTO `provinces` (`id`, `name`, `region`, `population`, `disaster_level`, `relief_priority`) VALUES
	(1, 'Hà Nội', 'Đồng Bằng Sông Hồng', 8000000, 0, 0),
	(2, 'Hồ Chí Minh', 'Đông Nam Bộ', 9000000, 0, 0),
	(3, 'Đà Nẵng', 'Duyên Hải Nam Trung Bộ', 1100000, 1, 1),
	(4, 'Quảng Bình', 'Bắc Trung Bộ', 850000, 3, 2),
	(5, 'Quảng Trị', 'Bắc Trung Bộ', 650000, 3, 2),
	(6, 'Nghệ An', 'Bắc Trung Bộ', 3300000, 1, 1),
	(7, 'Thanh Hóa', 'Bắc Trung Bộ', 3500000, 1, 1),
	(8, 'Hải Phòng', 'Đồng Bằng Sông Hồng', 2000000, 0, 0),
	(9, 'Cần Thơ', 'Đồng Bằng Sông Cửu Long', 1200000, 0, 0),
	(10, 'Thái Bình', 'Đồng Bằng Sông Hồng', 1700000, 0, 0),
	(11, 'Vĩnh Long', 'Đồng Bằng Sông Cửu Long', 1100000, 0, 0),
	(12, 'Bến Tre', 'Đồng Bằng Sông Cửu Long', 1300000, 0, 0),
	(13, 'Quảng Nam', 'Duyên Hải Nam Trung Bộ', 1500000, 1, 1),
	(14, 'Khánh Hòa', 'Duyên Hải Nam Trung Bộ', 1300000, 0, 0),
	(15, 'Lâm Đồng', 'Tây Nguyên', 1300000, 0, 0),
	(16, 'Gia Lai', 'Tây Nguyên', 1500000, 0, 0),
	(19, 'Bạc Liêu', 'Đồng Bằng Sông Cửu Long', 900000, 0, 0),
	(20, 'Cà Mau', 'Đồng Bằng Sông Cửu Long', 1200000, 0, 0),
	(21, 'Hà Giang', 'Đông Bắc Bộ', 850000, 0, 0),
	(22, 'Tuyên Quang', 'Đông Bắc Bộ', 760000, 0, 0),
	(23, 'Lạng Sơn', 'Đông Bắc Bộ', 720000, 0, 0),
	(24, 'Thái Nguyên', 'Đông Bắc Bộ', 1300000, 0, 0),
	(25, 'Cao Bằng', 'Đông Bắc Bộ', 550000, 0, 0),
	(26, 'Yên Bái', 'Tây Bắc Bộ', 860000, 0, 0),
	(27, 'Lào Cai', 'Tây Bắc Bộ', 730000, 0, 0),
	(28, 'Điện Biên', 'Tây Bắc Bộ', 590000, 0, 0),
	(29, 'Sơn La', 'Tây Bắc Bộ', 1200000, 0, 0),
	(30, 'Hòa Bình', 'Tây Bắc Bộ', 870000, 0, 0),
	(31, 'Quảng Ngãi', 'Duyên Hải Nam Trung Bộ', 1300000, 1, 1),
	(32, 'Phú Yên', 'Duyên Hải Nam Trung Bộ', 900000, 0, 0),
	(33, 'Bình Định', 'Duyên Hải Nam Trung Bộ', 1500000, 0, 0),
	(34, 'Ninh Thuận', 'Duyên Hải Nam Trung Bộ', 600000, 0, 0),
	(35, 'Bình Thuận', 'Duyên Hải Nam Trung Bộ', 1300000, 0, 0),
	(36, 'Kon Tum', 'Tây Nguyên', 560000, 0, 0),
	(37, 'Đắk Lắk', 'Tây Nguyên', 1900000, 0, 0),
	(38, 'Đắk Nông', 'Tây Nguyên', 670000, 0, 0),
	(45, 'Sóc Trăng', 'Đồng Bằng Sông Cửu Long', 1200000, 0, 0),
	(46, 'Hậu Giang', 'Đồng Bằng Sông Cửu Long', 750000, 0, 0),
	(65, 'Tây Ninh', 'Đông Nam Bộ', 1200000, 0, 0),
	(66, 'Bình Phước', 'Đông Nam Bộ', 1100000, 0, 0),
	(67, 'Đồng Nai', 'Đông Nam Bộ', 3100000, 0, 0),
	(68, 'Bà Rịa - Vũng Tàu', 'Đông Nam Bộ', 1200000, 0, 0),
	(69, 'Tiền Giang', 'Đồng Bằng Sông Cửu Long', 1900000, 0, 0);

-- Dumping structure for table supply_chain_future.stock_transactions
DROP TABLE IF EXISTS `stock_transactions`;
CREATE TABLE IF NOT EXISTS `stock_transactions` (
  `id` int NOT NULL AUTO_INCREMENT,
  `good_id` int NOT NULL,
  `transaction_type` enum('Import','Export') NOT NULL,
  `quantity` int NOT NULL,
  `transaction_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `note` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `good_id` (`good_id`),
  CONSTRAINT `stock_transactions_ibfk_1` FOREIGN KEY (`good_id`) REFERENCES `essential_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supply_chain_future.stock_transactions: ~2 rows (approximately)
DELETE FROM `stock_transactions`;
INSERT INTO `stock_transactions` (`id`, `good_id`, `transaction_type`, `quantity`, `transaction_date`, `note`) VALUES
	(1, 1, 'Import', 1000, '2024-12-01 12:18:03', 'Nhập kho hàng hóa mẫu'),
	(2, 1, 'Export', 500, '2024-12-01 12:18:03', 'Xuất kho hàng hóa mẫu');

-- Dumping structure for table supply_chain_future.suppliers
DROP TABLE IF EXISTS `suppliers`;
CREATE TABLE IF NOT EXISTS `suppliers` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `location` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supply_chain_future.suppliers: ~4 rows (approximately)
DELETE FROM `suppliers`;
INSERT INTO `suppliers` (`id`, `name`, `location`) VALUES
	(1, 'Vinafood', 'Hà Nội'),
	(2, 'Lavie', 'Long An'),
	(3, 'Dược Hậu Giang', 'Cần Thơ'),
	(4, 'Acecook', 'Bình Dương');

-- Dumping structure for table supply_chain_future.supplier_goods
DROP TABLE IF EXISTS `supplier_goods`;
CREATE TABLE IF NOT EXISTS `supplier_goods` (
  `id` int NOT NULL AUTO_INCREMENT,
  `supplier_id` int NOT NULL,
  `good_id` int NOT NULL,
  `quantity_available` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `good_id` (`good_id`),
  CONSTRAINT `supplier_goods_ibfk_1` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `supplier_goods_ibfk_2` FOREIGN KEY (`good_id`) REFERENCES `essential_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supply_chain_future.supplier_goods: ~7 rows (approximately)
DELETE FROM `supplier_goods`;
INSERT INTO `supplier_goods` (`id`, `supplier_id`, `good_id`, `quantity_available`) VALUES
	(1, 1, 1, 3000),
	(2, 1, 2, 2000),
	(3, 2, 2, 2500),
	(4, 3, 3, 1500),
	(5, 4, 4, 2000),
	(6, 4, 5, 500),
	(7, 1, 1, 5000);

-- Dumping structure for table supply_chain_future.supply_routes
DROP TABLE IF EXISTS `supply_routes`;
CREATE TABLE IF NOT EXISTS `supply_routes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `province_id` int NOT NULL,
  `supplier_id` int NOT NULL,
  `vehicle_id` int NOT NULL,
  `good_id` int NOT NULL,
  `quantity` int NOT NULL,
  `distance_km` int NOT NULL,
  `estimated_time_hours` decimal(5,2) DEFAULT NULL,
  `delay_reason` varchar(255) DEFAULT NULL,
  `priority_level` int DEFAULT '1',
  `cost` decimal(10,2) NOT NULL,
  `status` enum('Active','Delayed','Completed') DEFAULT 'Active',
  PRIMARY KEY (`id`),
  KEY `province_id` (`province_id`),
  KEY `supplier_id` (`supplier_id`),
  KEY `vehicle_id` (`vehicle_id`),
  KEY `good_id` (`good_id`),
  CONSTRAINT `supply_routes_ibfk_1` FOREIGN KEY (`province_id`) REFERENCES `provinces` (`id`),
  CONSTRAINT `supply_routes_ibfk_2` FOREIGN KEY (`supplier_id`) REFERENCES `suppliers` (`id`),
  CONSTRAINT `supply_routes_ibfk_3` FOREIGN KEY (`vehicle_id`) REFERENCES `vehicles` (`id`),
  CONSTRAINT `supply_routes_ibfk_4` FOREIGN KEY (`good_id`) REFERENCES `essential_goods` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supply_chain_future.supply_routes: ~3 rows (approximately)
DELETE FROM `supply_routes`;
INSERT INTO `supply_routes` (`id`, `province_id`, `supplier_id`, `vehicle_id`, `good_id`, `quantity`, `distance_km`, `estimated_time_hours`, `delay_reason`, `priority_level`, `cost`, `status`) VALUES
	(1, 1, 1, 1, 1, 1000, 100, 5.50, NULL, 1, 2000.00, 'Active'),
	(2, 2, 2, 2, 2, 500, 50, 3.00, NULL, 1, 1000.00, 'Active'),
	(3, 66, 1, 1, 1, 1000, 100, 5.50, NULL, 1, 2000.00, 'Active');

-- Dumping structure for table supply_chain_future.vehicles
DROP TABLE IF EXISTS `vehicles`;
CREATE TABLE IF NOT EXISTS `vehicles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `type` varchar(50) NOT NULL,
  `capacity` int NOT NULL,
  `status` enum('Available','In Use','Maintenance') DEFAULT 'Available',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Dumping data for table supply_chain_future.vehicles: ~11 rows (approximately)
DELETE FROM `vehicles`;
INSERT INTO `vehicles` (`id`, `type`, `capacity`, `status`) VALUES
	(1, 'Xe tải nhẹ', 2000, 'Available'),
	(2, 'Xe tải trung', 5000, 'In Use'),
	(3, 'Xe tải nặng', 10000, 'Available'),
	(4, 'Xe máy', 150, 'Available'),
	(5, 'Xe bán tải', 1000, 'Available'),
	(6, 'Tàu thuyền', 20000, 'In Use'),
	(7, 'Xe container', 30000, 'Available'),
	(8, 'Xe máy', 150, 'Available'),
	(9, 'Xe bán tải', 1000, 'Available'),
	(10, 'Tàu thuyền', 20000, 'In Use'),
	(11, 'Xe container', 30000, 'Available');

-- Dumping structure for trigger supply_chain_future.after_stock_update
DROP TRIGGER IF EXISTS `after_stock_update`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `after_stock_update` AFTER UPDATE ON `essential_goods` FOR EACH ROW BEGIN
    IF NEW.current_stock > OLD.current_stock THEN
        INSERT INTO stock_transactions (good_id, transaction_type, quantity, note)
        VALUES (NEW.id, 'Import', NEW.current_stock - OLD.current_stock, 'Nhập thêm vào kho');
    ELSEIF NEW.current_stock < OLD.current_stock THEN
        INSERT INTO stock_transactions (good_id, transaction_type, quantity, note)
        VALUES (NEW.id, 'Export', OLD.current_stock - NEW.current_stock, 'Xuất khỏi kho');
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger supply_chain_future.check_stock_before_export
DROP TRIGGER IF EXISTS `check_stock_before_export`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_stock_before_export` BEFORE UPDATE ON `essential_goods` FOR EACH ROW BEGIN
    IF NEW.current_stock < 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Không đủ hàng tồn kho để xuất!';
    END IF;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

-- Dumping structure for trigger supply_chain_future.check_supplier_stock_before_route
DROP TRIGGER IF EXISTS `check_supplier_stock_before_route`;
SET @OLDTMP_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
DELIMITER //
CREATE TRIGGER `check_supplier_stock_before_route` BEFORE INSERT ON `supply_routes` FOR EACH ROW BEGIN
    DECLARE current_stock INT;

    -- Lấy số lượng khả dụng từ bảng supplier_goods
    SELECT quantity_available INTO current_stock
    FROM supplier_goods
    WHERE supplier_id = NEW.supplier_id AND good_id = NEW.good_id;

    -- Kiểm tra nếu số lượng không đủ
    IF current_stock < NEW.quantity THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Nhà cung cấp không đủ số lượng hàng hóa!';
    END IF;

    -- Giảm số lượng khả dụng trong supplier_goods
    UPDATE supplier_goods
    SET quantity_available = quantity_available - NEW.quantity
    WHERE supplier_id = NEW.supplier_id AND good_id = NEW.good_id;
END//
DELIMITER ;
SET SQL_MODE=@OLDTMP_SQL_MODE;

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
