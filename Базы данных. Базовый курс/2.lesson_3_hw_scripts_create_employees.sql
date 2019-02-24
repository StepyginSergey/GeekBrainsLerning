# создаем схему lesson2 с указанной кодировкой
CREATE SCHEMA IF NOT EXISTS `employees` CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;

# выбираем схему employees
USE `employees`;

# создаем таблицу depart в выбранной схеме
CREATE TABLE `depart` (
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`name` VARCHAR(40) NOT NULL,
`count` INT DEFAULT 0,
`head_id` INT
);

# создаем таблицу shtat в выбранной схеме с указанной кодировкой
CREATE TABLE IF NOT EXISTS `shtat` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(25) NOT NULL,
`lastname` VARCHAR(25) NOT NULL,
`dept` INT,
`position` VARCHAR(30),
`salary` INT,
PRIMARY KEY (`id`)
)DEFAULT CHARSET utf8mb4 COLLATE utf8mb4_unicode_ci;

---

# вставляем в таблицу depart строки полностью
INSERT INTO `depart` (`id`,`name`,`count`,`head_id`) VALUES (1, 'IT', 0, NULL);

# вставляем в таблицу depart только нужные столбцы
-- INSERT INTO `depart` (`name`) VALUES ('Бухгалтерия');

# вставляем в таблицу depart строки полностью (можно не перечислять столбцы)
INSERT INTO `depart` VALUES (3, 'Маркетинг', 0, NULL);

# вставляем в таблицу depart сразу несколько строк
INSERT INTO `depart` 
(`name`) VALUES 
('Бухгалтерия'),
('Охрана'),
('Контроль качества'),
('Реклама');


# вставляем в таблицу shtat сразу несколько строк
INSERT INTO `shtat` 
(`name`,`lastname`,`dept`,`salary`) VALUES 
('Тарас','Голомозый',1,25000),
('Антон','Полонников',1,35000),
('Алена','Звягинцева',1,45000),
('Владимир','Питерский',1,40000),
('Анатолий','Башлыков',3,30000),
('Ирина','Кенина',3,50000),
('Сергей','Котельников',6,45000),
('Александр','Розембаум',6,60000),
('Михаил','Круг',6,28000),
('Ирина','Алегрова',4,50000);

# отключаем проверку внешних ключей
SET foreign_key_checks = 0;

# изменяем таблицу shtat (добавляем внешний ключ)
ALTER TABLE `shtat`
ADD CONSTRAINT `depdep` FOREIGN KEY (`dept`)
REFERENCES `depart`(`id`)
ON UPDATE CASCADE
ON DELETE RESTRICT;

# включаем проверку внешних ключей
SET foreign_key_checks = 1;