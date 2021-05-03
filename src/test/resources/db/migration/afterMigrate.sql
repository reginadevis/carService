INSERT INTO `manufacturer` (`manufacturer_id`, `manufacturer`) VALUES ('1', 'Renault');
INSERT INTO `manufacturer` (`manufacturer_id`, `manufacturer`) VALUES ('2', 'Nissan');

INSERT INTO `model` (`model_id`, `model`, `manufacturer_id`) VALUES ('1', 'Tribber', '1');
INSERT INTO `model` (`model_id`, `model`, `manufacturer_id`) VALUES ('2', 'Micra', '2');
INSERT INTO `model` (`model_id`, `model`, `manufacturer_id`) VALUES ('3', 'Duster', '1');

INSERT INTO `car` (`id`, `model_id`, `year`, `color`, `vin`, `miles`) VALUES ('1', '1', '2012', 'grey', '1111', '123.67');
INSERT INTO `car` (`id`, `model_id`, `year`, `color`, `vin`, `miles`) VALUES ('2', '2', '2015', 'green', '1112', '147.01');