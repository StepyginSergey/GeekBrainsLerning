USE `countries_and_cities`;
/*
INSERT INTO `countreis` (`title`) VALUES ('Россия'), ('Япония'), ('Китай'), ('Аргентина'), ('Бразилия'), ('Германия'), ('Франция'), ('Италия');

SELECT * from `countreis` ORDER BY `id`;

INSERT INTO `regions` (`country_id`, `title`) 
VALUES 	(1 , 'Московская область'), (1, 'Тульская область'), (1, 'Калужская область'), 
        (2, 'Хакайдо'),(2, 'Окинава'),
        (6, 'Восточная Германия'),(6, 'Запдная Германия');
        
 SELECT * FROM `regions`;
 
 SELECT * FROM `countreis` c LEFT JOIN `regions` r ON r.`country_id` = c.`id`;
 
INSERT INTO `cities` (`country_id`,`important`,`regoins_id`, `title`) 
VALUES 	(1, 2, 1, 'Срепухов'), (1, 1, 1, 'Чехов'), (1, 1, 1, 'Подольск'), 
        (1, 1, 2, 'Таруса'), (1, 1, 2, 'Тула'), (1, 1, 2, 'Малахово'),
        (1, 1, 3, 'Тарутино'),(1, 1, 3, 'Обнинск'),(1, 1, 3, 'Белоусово');
        
        */
        
-- Запрос выводящий информацию по городам
SELECT 
    city.`title` AS city_name,
    r.`title` AS region_name,
    c.`title` AS country_name
FROM
    `cities` city
        INNER JOIN
    `regions` r ON r.`id` = city.`regoins_id`
        INNER JOIN
    `countreis` c ON c.`id` = city.`country_id`
;

-- Запрос выводящий все города по региону
SELECT 
	r.`title` AS region_name,
    city.`title` AS city_name
FROM
    `cities` city
        INNER JOIN
    `regions` r ON r.`id` = city.`regoins_id`
WHERE
    r.`title` = 'Московская область'
;
