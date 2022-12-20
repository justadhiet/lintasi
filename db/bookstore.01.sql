-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 20, 2022 at 01:10 PM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bookstore`
--

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE `book` (
  `book_id` int(11) NOT NULL,
  `picture` varchar(255) DEFAULT NULL,
  `title` varchar(85) DEFAULT NULL,
  `isbn` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  `publisher` varchar(45) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `publication_date` datetime DEFAULT NULL,
  `genre_id` int(11) DEFAULT NULL,
  `print_length` varchar(45) DEFAULT NULL,
  `dimension` varchar(45) DEFAULT NULL,
  `language` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`book_id`, `picture`, `title`, `isbn`, `description`, `publisher`, `author`, `publication_date`, `genre_id`, `print_length`, `dimension`, `language`) VALUES
(1, NULL, 'Blink', '008877665', 'Blink changes the way you\'ll understand every decision you make. Never again will you think about thinking the same way.', 'Gramedia', 'Malcolm Gladwell', '2022-11-25 00:00:00', 1, '100', '50x50', 'English');

-- --------------------------------------------------------

--
-- Table structure for table `favorite`
--
-- Error reading structure for table bookstore.favorite: #1932 - Table 'bookstore.favorite' doesn't exist in engine
-- Error reading data for table bookstore.favorite: #1064 - You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near 'FROM `bookstore`.`favorite`' at line 1

CREATE TABLE `favorite` (
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `datefavorite` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------
-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `message_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `message` varchar(450) DEFAULT NULL,
  `message_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `pricing`
--

CREATE TABLE `pricing` (
  `book_id` int(11) NOT NULL,
  `price` double NOT NULL,
  `status` int(1) DEFAULT NULL,
  `time_range` int(11) DEFAULT NULL,
  `discount` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `pricing`
--

INSERT INTO `pricing` (`book_id`, `price`, `status`, `time_range`, `discount`) VALUES
(1, 10000, 0, 2147483647, 10);

-- --------------------------------------------------------

--
-- Table structure for table `recomended`
--

CREATE TABLE `recomended` (
  `book_id` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `recomend_time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Error reading structure for table bookstore.recomended: #1932 - Table 'bookstore.recomended' doesn't exist in engine
-- Error reading data for table bookstore.recomended: #1064 - You have an error in your SQL syntax; check the manual that corresponds to your MariaDB server version for the right syntax to use near 'FROM `bookstore`.`recomended`' at line 1

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `review_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `review` varchar(300) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id_role` int(11) NOT NULL,
  `nama` varchar(45) DEFAULT NULL,
  `akses` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id_role`, `nama`, `akses`) VALUES
(1, 'ROLE_ADMIN', 'ALL'),
(2, 'ROLE_USER', 'ROLE_USER'),
(3, 'ROLE_EDITOR', 'ROLE_EDITOR'),
(4, 'ROLE_MANAGER', 'ROLE_MANAGER');

-- --------------------------------------------------------

--
-- Table structure for table `shipment`
--

CREATE TABLE `shipment` (
  `shipment_id` int(11) NOT NULL,
  `transaction_id` int(11) DEFAULT NULL,
  `status` varchar(45) DEFAULT NULL,
  `expedition` varchar(45) DEFAULT NULL,
  `weight` double DEFAULT NULL,
  `shipment_fee` double DEFAULT NULL,
  `sender_name` varchar(45) DEFAULT NULL,
  `receiver_name` varchar(45) DEFAULT NULL,
  `receiver_address` varchar(100) DEFAULT NULL,
  `receiver_phone` varchar(45) DEFAULT NULL,
  `receiver_email` varchar(45) DEFAULT NULL,
  `sender_phone` varchar(45) DEFAULT NULL,
  `sender_email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `stock_opname`
--

CREATE TABLE `stock_opname` (
  `book_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `first_count` int(11) DEFAULT NULL,
  `last_count` int(11) DEFAULT NULL,
  `first_date` datetime DEFAULT NULL,
  `last_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `transaction_id` int(11) NOT NULL,
  `transaction_detail_id` int(11) DEFAULT NULL,
  `total_items` int(11) DEFAULT NULL,
  `discount` double DEFAULT NULL,
  `status_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `transaction_detail`
--

CREATE TABLE `transaction_detail` (
  `transaction_detail_id` int(11) NOT NULL,
  `book_id` int(11) DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `no_hp` varchar(45) DEFAULT NULL,
  `address` varchar(45) DEFAULT NULL,
  `id_role` int(11) DEFAULT NULL,
  `join_date` datetime DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL DEFAULT 'xxxxx'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `username`, `name`, `email`, `no_hp`, `address`, `id_role`, `join_date`, `profile_picture`, `password`) VALUES
(1, 'admin', 'Administrator', 'admin@bookstore.com', '080989999', 'Jakarta', 1, '2022-11-18 00:00:00', NULL, 'xxxxx'),
(2, 'jane', 'Jane Doe', 'jane@bookstore.com', '080989991', 'Jakarta Barat', 2, '2022-11-18 00:00:00', NULL, 'xxxxx'),
(6, 'jhon', 'Jhon Doe', 'jhon@bookstore.com', '080989992', 'Palembang', 2, '2022-11-20 08:40:17', NULL, 'xxxxx');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`book_id`);

--
-- Indexes for table `favorite`
--
ALTER TABLE `favorite`
  ADD PRIMARY KEY (`book_id`,`user_id`);
  
--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`message_id`),
  ADD KEY `usermsg` (`user_id`);

--
-- Indexes for table `pricing`
--
ALTER TABLE `pricing`
  ADD PRIMARY KEY (`book_id`,`price`) USING BTREE;

--
-- Indexes for table `recomended`
--
ALTER TABLE `recomended`
  ADD PRIMARY KEY (`book_id`,`user_id`);
  
--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`review_id`),
  ADD KEY `reviewb_idx` (`book_id`),
  ADD KEY `reviewu_idx` (`user_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id_role`);

--
-- Indexes for table `shipment`
--
ALTER TABLE `shipment`
  ADD PRIMARY KEY (`shipment_id`);

--
-- Indexes for table `stock_opname`
--
ALTER TABLE `stock_opname`
  ADD PRIMARY KEY (`book_id`),
  ADD KEY `userop` (`user_id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`transaction_id`);

--
-- Indexes for table `transaction_detail`
--
ALTER TABLE `transaction_detail`
  ADD PRIMARY KEY (`transaction_detail_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `userrole` (`id_role`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `book`
--
ALTER TABLE `book`
  MODIFY `book_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `message_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id_role` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `shipment`
--
ALTER TABLE `shipment`
  MODIFY `shipment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `transaction_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `transaction_detail`
--
ALTER TABLE `transaction_detail`
  MODIFY `transaction_detail_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `usermsg` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `pricing`
--
ALTER TABLE `pricing`
  ADD CONSTRAINT `bookpricing` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`);
  
--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `reviewb` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `reviewu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `stock_opname`
--
ALTER TABLE `stock_opname`
  ADD CONSTRAINT `bookop` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `userop` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `userrole` FOREIGN KEY (`id_role`) REFERENCES `role` (`id_role`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
