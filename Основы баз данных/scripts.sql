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
