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


-- добавление внешних ключей в таблицу продуктов
ALTER TABLE `shop`.`product` 
ADD INDEX `fk_brand_id_idx` (`brand_id` ASC),
ADD INDEX `fk_product_type_id_idx` (`product_type_id` ASC);
ALTER TABLE `shop`.`product` 
ADD CONSTRAINT `fk_brand_id`
  FOREIGN KEY (`brand_id`)
  REFERENCES `shop`.`brand` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_product_type_id`
  FOREIGN KEY (`product_type_id`)
  REFERENCES `shop`.`product_type` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
-- Добавление внешнего ключа к таблице продуктов из таблицы категорий  
ALTER TABLE `shop`.`product` 
ADD INDEX `fk_category_id_idx` (`category_id` ASC);
ALTER TABLE `shop`.`product` 
ADD CONSTRAINT `fk_category_id`
  FOREIGN KEY (`category_id`)
  REFERENCES `shop`.`category` (`id`)
  ON DELETE CASCADE
  ON UPDATE NO ACTION;
  
-- обавление таблицы заказов
CREATE TABLE `shop`.`order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_name` VARCHAR(128) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `datetime` DATETIME NULL,
  PRIMARY KEY (`id`));

-- скрипт добавления заказа
INSERT INTO `shop`.`order` (`user_name`, `phone`, `datetime`) VALUES ('Василий', '555-55-55', '2016-05-09 14:20');

-- добавление таблицы корзина
CREATE TABLE `shop`.`order_products` (
  `order_id` INT NOT NULL,
  `product_id` INT NULL,
  `count` INT NULL,
  PRIMARY KEY (`order_id`));

-- создание составного ключа
ALTER TABLE `shop`.`order_products` 
CHANGE COLUMN `product_id` `product_id` INT(11) NOT NULL ,
DROP PRIMARY KEY,
ADD PRIMARY KEY (`order_id`, `product_id`);

-- добавление заказа в корзину
INSERT INTO `shop`.`order_products` (`order_id`, `product_id`, `count`) VALUES ('1', '1', '2');
INSERT INTO `shop`.`order_products` (`order_id`, `product_id`, `count`) VALUES ('1', '2', '4');

--
ALTER TABLE `shop`.`order_products` 
ADD INDEX `fk_order_products_products_idx` (`product_id` ASC);
ALTER TABLE `shop`.`order_products` 
ADD CONSTRAINT `fk_order_products_order`
  FOREIGN KEY (`order_id`)
  REFERENCES `shop`.`order` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_order_products_products`
  FOREIGN KEY (`product_id`)
  REFERENCES `shop`.`product` (`id`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
--
ALTER TABLE `shop`.`order_products` 
CHANGE COLUMN `count` `count` INT(11) NOT NULL ;

--
ALTER TABLE `shop`.`product` 
ADD INDEX `price_index` (`price` ASC);
