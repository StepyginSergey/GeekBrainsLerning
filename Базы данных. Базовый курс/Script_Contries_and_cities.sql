-- Создание Базы Данных
CREATE SCHEMA IF NOT EXISTS `contries_and_cities_of_the_world_1` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

-- Создание таблиц
USE contries_and_cities_of_the_world_1;
-- Создание таблицы Страны
CREATE TABLE IF NOT EXISTS `contry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `description` MEDIUMTEXT NULL,
  PRIMARY KEY (`id`));
  
-- Создание таблиццы Область
CREATE TABLE IF NOT EXISTS `region` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nane` TEXT(1000) NOT NULL,
  `contry_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `contry_id_idx` (`contry_id` ASC) VISIBLE,
  FOREIGN KEY (`contry_id`)
    REFERENCES `contry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
-- Создание таблицы Район
CREATE TABLE IF NOT EXISTS `area` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(250) NOT NULL,
  `region_id` INT NULL,
  `conrty_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `region_id_idx` (`region_id` ASC) VISIBLE,
  INDEX `contry_id_idx` (`conrty_id` ASC) INVISIBLE,
  FOREIGN KEY (`region_id`)
    REFERENCES `region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`conrty_id`)
    REFERENCES `contry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
	
-- Созданиетаблицы Город
CREATE TABLE `city` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `area_id` INT NULL,
  `name` VARCHAR(250) NOT NULL,
  `description` MEDIUMTEXT NULL,
  `region_id` INT NULL,
  `contry_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `contry_id_idx` (`contry_id` ASC) VISIBLE,
  INDEX `region_id_idx` (`region_id` ASC) VISIBLE,
  INDEX `area_id_idx` (`area_id` ASC) VISIBLE,
  FOREIGN KEY (`region_id`)
    REFERENCES `region` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`contry_id`)
    REFERENCES `contry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  FOREIGN KEY (`area_id`)
    REFERENCES `contry` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- Добавление данных в таблицы

-- добавление данных в таблицу Страны
INSERT INTO `contry` (`name`, `description`) VALUES ('Россия', 'Хоть тут пусть будет на первом месте');
INSERT INTO `contry` (`name`) VALUES ('Австралия');
INSERT INTO `contry` (`name`, `description`) VALUES ('Япония', 'Люблю их культуру');
INSERT INTO `contry` (`name`) VALUES ('Китай');

-- Добавление данных в таблицу Область
INSERT INTO `region` (`nane`, `contry_id`) VALUES ('Московская область', '1');
INSERT INTO `region` (`nane`, `contry_id`) VALUES ('Краснодарский край', '1');
INSERT INTO `region` (`nane`, `contry_id`) VALUES ('Калужская область', '1');

-- Добавление данных в таблицу Район
INSERT INTO `area` (`name`, `region_id`, `conrty_id`) VALUES ('Серпуховский район', '1', '1');
INSERT INTO `area` (`name`, `region_id`, `conrty_id`) VALUES ('Туапсинский район', '2', '1');

-- Добавление данных в таблицу Город
INSERT INTO `city` (`area_id`, `name`, `description`, `region_id`, `contry_id`) VALUES ('3', 'Серпухов', 'Моя родина', '1', '1');
INSERT INTO `city` (`area_id`, `name`, `region_id`, `contry_id`) VALUES ('4', 'Джубга', '2', '1');

