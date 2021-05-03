CREATE TABLE IF NOT EXISTS `manufacturer` (
    `manufacturer_id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `manufacturer` varchar(50)
);

CREATE TABLE IF NOT EXISTS `model` (
    `model_id` int NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `model` varchar(50),
    `manufacturer_id` int not null
);

ALTER TABLE model ADD CONSTRAINT fk_makemodel FOREIGN KEY (manufacturer_id) REFERENCES manufacturer(manufacturer_id);

alter table car add column model_id int not null after model;

alter table car drop column model;

ALTER TABLE car ADD CONSTRAINT fk_model FOREIGN KEY (model_id) REFERENCES model(model_id);
