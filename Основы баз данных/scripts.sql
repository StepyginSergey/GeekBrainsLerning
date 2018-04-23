USE shop;

-- Скрипт создания таблицы продуктов
CREATE TABLE `shop`.`product` (
  `id` INT NOT NULL,
  `brand_id` INT NOT NULL,
  `product_type_id` INT NOT NULL,
  `category_id` INT NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`id`));


ALTER TABLE `shop`.`product` 
CHANGE COLUMN `id` `id` INT(11) NOT NULL AUTO_INCREMENT ;

-- Добавление новых данных в таблицу продуктов

INSERT INTO product (brand_id, product_type_id, category_id, price) VALUES (1, 1, 2, 12500);

INSERT INTO product (brand_id, product_type_id, category_id, price) VALUES (3, 5, 4, 1500.20);
INSERT INTO product (brand_id, product_type_id, category_id, price) VALUES (5, 6, 3, 2350);