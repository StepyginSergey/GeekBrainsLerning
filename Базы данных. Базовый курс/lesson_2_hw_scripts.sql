-- Создаем базу данных
CREATE SCHEMA IF NOT EXISTS `countries_and_cities` CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Указываем последующим командам какую БД использовать по умолчанию
-- Это упрощает написание команд запроса
USE `countries_and_cities` ;

-- Создаем таблицу Страны
CREATE TABLE IF NOT EXISTS `countreis` (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
title VARCHAR(150) NOT NULL,
INDEX `title_idx` (`title` ASC) VISIBLE
);

-- Создаем таблицу Регионы
CREATE TABLE IF NOT EXISTS `regions` (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
country_id INT NOT NULL,
title VARCHAR(150) NOT NULL,
INDEX `title_idx` (`title` ASC) VISIBLE,
CONSTRAINT `region_country_id` FOREIGN KEY (`country_id`)
    REFERENCES `countreis` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);
-- создаем таблицу Городов
CREATE TABLE IF NOT EXISTS `cities` (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
country_id INT NOT NULL,
important TINYINT(1),
regoins_id INT NOT NULL,
title VARCHAR(150) NOT NULL,
INDEX `title_idx` (`title` ASC) VISIBLE,
CONSTRAINT `city_country_id` FOREIGN KEY (`country_id`)
    REFERENCES `countreis` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
CONSTRAINT `city_regions_id` FOREIGN KEY (`country_id`)
    REFERENCES `regions` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);
