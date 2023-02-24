-- bookstore2.review definition
DELETE TABLE `review`;
CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) DEFAULT NULL,
  `review` varchar(300) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  `rate` int(11) DEFAULT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_un` (`book_id`,`user_id`),
  KEY `reviewb_idx` (`book_id`),
  KEY `reviewu_idx` (`user_id`),
  CONSTRAINT `reviewb` FOREIGN KEY (`book_id`) REFERENCES `book` (`book_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `reviewu` FOREIGN KEY (`user_id`) REFERENCES `user` (`id_user`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;