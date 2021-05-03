CREATE TABLE IF NOT EXISTS `car` (

    `id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `manufacturer` varchar(50),
    `model` varchar(50),
    `year` int,
    `color` varchar(50),
    `vin` int UNIQUE,
    `miles` double

)ENGINE=InnoDB DEFAULT CHARSET=UTF8;