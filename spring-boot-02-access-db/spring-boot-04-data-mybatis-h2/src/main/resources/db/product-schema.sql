CREATE TABLE `product` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pro_name` varchar(200) DEFAULT NULL,
  `pro_type` varchar(50) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `create_time` timestamp NOT NULL,
  PRIMARY KEY (`pid`)
)ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;