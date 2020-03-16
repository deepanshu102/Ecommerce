-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               10.4.12-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Dumping database structure for martian
CREATE DATABASE IF NOT EXISTS `martian` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `martian`;

-- Dumping structure for table martian.cuisine
CREATE TABLE IF NOT EXISTS `cuisine` (
  `cuisineId` varchar(50) NOT NULL,
  `cuisineName` varchar(50) NOT NULL DEFAULT '',
  PRIMARY KEY (`cuisineId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.cuisine: ~5 rows (approximately)
DELETE FROM `cuisine`;
/*!40000 ALTER TABLE `cuisine` DISABLE KEYS */;
INSERT INTO `cuisine` (`cuisineId`, `cuisineName`) VALUES
	('C1', 'South Indian'),
	('C2', 'North Indian'),
	('C3', 'Italian '),
	('C4', 'Chinese'),
	('C5', 'Sea Food');
/*!40000 ALTER TABLE `cuisine` ENABLE KEYS */;

-- Dumping structure for table martian.customer
CREATE TABLE IF NOT EXISTS `customer` (
  `customerId` varchar(50) NOT NULL,
  `customerName` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pincode` int(11) NOT NULL DEFAULT 0,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.customer: ~5 rows (approximately)
DELETE FROM `customer`;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` (`customerId`, `customerName`, `address`, `city`, `state`, `pincode`, `password`) VALUES
	('8375959268', 'Som', 'D-88 Mandakni Appartment', 'Delhi', 'New Delhi', 74, 'Somu'),
	('8826664740', 'vartika', 'ATS', 'Ghaziabad', 'U.P', 201014, 'var@123'),
	('9711172821', 'Karan', 'B01 Rajori garden', 'Delhi', 'New Delhi', 110021, 'karu'),
	('9876543215', 'jatin', 'abc,xyz', 'Delhi', 'Delhi', 110015, 'abc'),
	('9899577398', 'Akshay', '14091', 'ghaziabad', 'up', 201014, 'Ak0307');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;

-- Dumping structure for table martian.food
CREATE TABLE IF NOT EXISTS `food` (
  `foodId` varchar(50) NOT NULL DEFAULT 'AUTO_INCREMENT',
  `cuisineId` varchar(50) NOT NULL,
  `foodName` varchar(50) NOT NULL,
  PRIMARY KEY (`foodId`,`cuisineId`),
  KEY `FK_food_cuisine` (`cuisineId`),
  CONSTRAINT `FK_food_cuisine` FOREIGN KEY (`cuisineId`) REFERENCES `cuisine` (`cuisineId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.food: ~14 rows (approximately)
DELETE FROM `food`;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` (`foodId`, `cuisineId`, `foodName`) VALUES
	('F1', 'C1', 'Dosa'),
	('F10', 'C5', 'Fried Fish'),
	('F11', 'C4', 'Noodles'),
	('F12', 'C4', 'Spring Roll'),
	('F13', 'C5', 'Fish Curry'),
	('F14', 'C2', 'Butter Naan'),
	('F2', 'C1', 'Idli'),
	('F3', 'C1', 'Vada'),
	('F4', 'C1', 'Uttapam'),
	('F5', 'C1', 'Sambar'),
	('F6', 'C3', 'Pizza'),
	('F7', 'C3', 'Pasta'),
	('F8', 'C2', 'Chole Bhature'),
	('F9', 'C2', 'Dal Makhni');
/*!40000 ALTER TABLE `food` ENABLE KEYS */;

-- Dumping structure for table martian.foodmenu
CREATE TABLE IF NOT EXISTS `foodmenu` (
  `foodMenuId` varchar(50) NOT NULL,
  `foodPrice` decimal(7,2) NOT NULL DEFAULT 0.00,
  `foodId` varchar(50) NOT NULL DEFAULT '0',
  `restaurantId` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`foodId`,`restaurantId`),
  UNIQUE KEY `foodMenuId` (`foodMenuId`),
  KEY `FoodMenuToRestaurant` (`restaurantId`),
  KEY `footTofoodMenu` (`foodId`),
  CONSTRAINT `FoodMenuToRestaurant` FOREIGN KEY (`restaurantId`) REFERENCES `restaurant` (`RestaurantId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `footTofoodMenu` FOREIGN KEY (`foodId`) REFERENCES `food` (`foodId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.foodmenu: ~3 rows (approximately)
DELETE FROM `foodmenu`;
/*!40000 ALTER TABLE `foodmenu` DISABLE KEYS */;
INSERT INTO `foodmenu` (`foodMenuId`, `foodPrice`, `foodId`, `restaurantId`) VALUES
	('FM1', 100.00, 'F1', '9560773578'),
	('FM2', 200.00, 'F14', '9711172821'),
	('FM4', 200.00, 'F7', '9818911991');
/*!40000 ALTER TABLE `foodmenu` ENABLE KEYS */;

-- Dumping structure for table martian.foodorder
CREATE TABLE IF NOT EXISTS `foodorder` (
  `orderId` varchar(50) NOT NULL,
  `totalPrice` decimal(7,2) NOT NULL DEFAULT 0.00,
  `cancelStatus` enum('Y','N') NOT NULL DEFAULT 'N',
  `foodQuantity` int(11) NOT NULL,
  `customerId` varchar(50) NOT NULL,
  `foodMenuId` varchar(50) NOT NULL,
  PRIMARY KEY (`orderId`,`foodMenuId`),
  KEY `CustomerToOrder` (`customerId`),
  KEY `foodMenuId` (`foodMenuId`),
  CONSTRAINT `CustomerToOrder` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `foodMenuIdToOrder` FOREIGN KEY (`foodMenuId`) REFERENCES `foodmenu` (`foodMenuId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.foodorder: ~3 rows (approximately)
DELETE FROM `foodorder`;
/*!40000 ALTER TABLE `foodorder` DISABLE KEYS */;
INSERT INTO `foodorder` (`orderId`, `totalPrice`, `cancelStatus`, `foodQuantity`, `customerId`, `foodMenuId`) VALUES
	('O0', 600.00, 'N', 6, '8826664740', 'FM1'),
	('O1', 200.00, 'N', 2, '8826664740', 'FM1'),
	('O1', 200.00, 'N', 2, '8826664740', 'FM4');
/*!40000 ALTER TABLE `foodorder` ENABLE KEYS */;

-- Dumping structure for table martian.foodorder_test
CREATE TABLE IF NOT EXISTS `foodorder_test` (
  `orderId` varchar(50) NOT NULL,
  `totalPrice` decimal(7,2) NOT NULL DEFAULT 0.00,
  `cancelStatus` enum('Y','N') NOT NULL DEFAULT 'N',
  `foodQuantity` int(11) NOT NULL,
  `customerId` varchar(50) NOT NULL,
  `foodmenu` varchar(50) NOT NULL,
  PRIMARY KEY (`orderId`,`foodmenu`),
  KEY `CustomerToOrder` (`customerId`),
  KEY `foodmenutoordertest` (`foodmenu`),
  CONSTRAINT `customerIdtoOrder` FOREIGN KEY (`customerId`) REFERENCES `customer` (`customerId`),
  CONSTRAINT `foodmenutoordertest` FOREIGN KEY (`foodmenu`) REFERENCES `foodmenu` (`foodMenuId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.foodorder_test: ~1 rows (approximately)
DELETE FROM `foodorder_test`;
/*!40000 ALTER TABLE `foodorder_test` DISABLE KEYS */;
INSERT INTO `foodorder_test` (`orderId`, `totalPrice`, `cancelStatus`, `foodQuantity`, `customerId`, `foodmenu`) VALUES
	('O1', 100.00, 'N', 10, '8375959268', 'FM4');
/*!40000 ALTER TABLE `foodorder_test` ENABLE KEYS */;

-- Dumping structure for table martian.payment
CREATE TABLE IF NOT EXISTS `payment` (
  `paymentId` int(11) NOT NULL,
  `orderId` varchar(50) NOT NULL DEFAULT '',
  `paymentStatus` enum('Y','N') NOT NULL DEFAULT 'N',
  PRIMARY KEY (`paymentId`),
  KEY `FK_payment_foodorder` (`orderId`),
  CONSTRAINT `FK_payment_foodorder` FOREIGN KEY (`orderId`) REFERENCES `foodorder` (`orderId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.payment: ~0 rows (approximately)
DELETE FROM `payment`;
/*!40000 ALTER TABLE `payment` DISABLE KEYS */;
/*!40000 ALTER TABLE `payment` ENABLE KEYS */;

-- Dumping structure for table martian.restaurant
CREATE TABLE IF NOT EXISTS `restaurant` (
  `restaurantId` varchar(50) NOT NULL,
  `restaurantName` varchar(50) NOT NULL,
  `restaurantAddress` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `pincode` int(11) NOT NULL DEFAULT 0,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`restaurantId`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- Dumping data for table martian.restaurant: ~5 rows (approximately)
DELETE FROM `restaurant`;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` (`restaurantId`, `restaurantName`, `restaurantAddress`, `city`, `state`, `pincode`, `password`) VALUES
	('9560773578', 'Sagar Ratna', 'D-7/47 Sec-15,Rohini', 'Delhi', 'Delhi', 110089, 'sana'),
	('9711172821', 'Karu Baker\'s', 'B-1110,Ramesh Nagar', 'Delhi', 'Delhi', 110015, 'kaer'),
	('9810049886', 'Raj Bhog', 'SF 146 sec-18,Noida ', 'Noida', 'U.P.', 201516, 'raog'),
	('9818911991', 'Time Out Cafe', 'FF-08,Orange County,Indirapuram', 'Ghaziabad', 'U.P.', 201025, 'tife'),
	('9899577398', 'Haldiram', 'D-144/145 Sec-11,Rohini', 'Delhi', 'Delhi', 110085, 'haam');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;

-- Dumping structure for procedure martian.addFood
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addFood`(
	IN `CuisineName` VARCHAR(50),
	IN `fId` VARCHAR(50),
	IN `itemName` VARCHAR(50)
)
    COMMENT '//Adding the food items'
BEGIN
Declare cuisineCategory varchar(50);
select cuisineId into cuisineCategory from martian.cuisine where  cuisine.cuisineName=cuisineName;
insert into food(foodId,cuisineId,foodName) values(concat("Food",fId),cuisineCategory,itemName);
END//
DELIMITER ;

-- Dumping structure for procedure martian.addFoodMenu
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addFoodMenu`(
	IN `FoodMenuId` VARCHAR(50),
	IN `foodName` VARCHAR(50),
	IN `restName` VARCHAR(50),
	IN `price` DECIMAL(7,2)
)
    COMMENT '//Register the food through Restaurant'
BEGIN
Declare foodIdentity,Restaurantidentity varchar(50);
select food.foodId into FoodIdentity from martian.food where Food.foodName=foodName;
select restaurant.restaurantId into Restaurantidentity from martian.restaurant where restaurant.restaurantName=restName;


insert into foodmenu(foodmenu.foodMenuId ,foodmenu.foodId,foodmenu.restaurantId,foodmenu.foodPrice)
values
(concat("FM",FoodMenuId),FoodIdentity,Restaurantidentity,price);

END//
DELIMITER ;

-- Dumping structure for procedure martian.addOrder
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `addOrder`(
	IN `foodName` VARCHAR(50),
	IN `customerPhone` VARCHAR(50),
	IN `quantity` INT,
	IN `total` DECIMAL(7,2),
	IN `orderId` VARCHAR(50)







,
	IN `restName` VARCHAR(50)


)
BEGIN
SELECT RestaurantId INTO @restId FROM restaurant WHERE restaurant.restaurantName=restName;

SELECT foodId INTO @foodId FROM food WHERE food.foodName=foodName;

SELECT customerId INTO @customerId FROM customer WHERE customer.customerId=customerPhone;

SELECT foodMenuId INTO @foodmenuId FROM foodmenu WHERE foodId=@foodId AND restaurantId=@restId;

INSERT INTO foodorder(foodorder.orderId,foodorder.customerId
,foodorder.foodMenuId,
foodorder.foodQuantity,foodorder.totalPrice,
foodorder.cancelStatus)

VALUES(CONCAT("O",orderId),@customerId,@foodmenuId,quantity,total,'N');

END//
DELIMITER ;

-- Dumping structure for procedure martian.cuisineMenu
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `cuisineMenu`()
    COMMENT '//Display all Cuisine items'
BEGIN
select * from cuisine;
END//
DELIMITER ;

-- Dumping structure for procedure martian.restaurantMenu
DELIMITER //
CREATE DEFINER=`root`@`localhost` PROCEDURE `restaurantMenu`(
	IN `restaurantName` VARCHAR(50)
)
    COMMENT '//fetching the restaurant food Menu'
BEGIN
SELECT RestaurantId INTO @restId FROM restaurant WHERE restaurant.restaurantName=restaurantName;
SELECT * FROM cuisine,food,foodMenu WHERE food.foodId=foodMenu.foodId AND food.cuisineId=cuisine.cuisineId AND 
foodMenu.restaurantId=@restId;

END//
DELIMITER ;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
