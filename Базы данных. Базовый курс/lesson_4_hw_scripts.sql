USE `employees`;

# Для начала проверим данные в таблица, что бы знать с чем будем работать
SELECT * FROM `depart`;
SELECT * FROM `shtat`;

# создаем таблицу с датами
CREATE TABLE IF NOT EXISTS `dates`(
`id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
`sotr_id` INT NOT NULL,
`start_date` DATE,
`end_date` DATE
);
# Заполняем таблицу дат сотрудниками, которые уже существуют и присваиваем им текущую дату
INSERT INTO `dates` (`start_date`, `sotr_id`)  SELECT CURDATE(), `id` FROM `shtat`;
# Проверяем что получилось
SELECT * FROM `dates`;

# 1. Создать VIEW на основе запросов, которые вы сделали в ДЗ к уроку 3.
CREATE OR REPLACE VIEW `shtat_info` AS
SELECT 
	s.`lastname`, s.`name`, d.`name` AS department, 
    dates.start_date, CASE WHEN dates.`end_date` IS NULL THEN 'Работает на сегодняшний день' ELSE dates.`end_date` END AS end_date
FROM `shtat` s 
LEFT JOIN `depart` d ON d.`id` = s.`dept` 
LEFT JOIN `dates` dates ON dates.sotr_id = s.id;

# Проверяем как работает представление
SELECT * FROM `shtat_info`;


# 2. Создать функцию, которая найдет менеджера по имени и фамилии.
# немного изменил условие задания: написал функцию, которая выводит идентификатор сотрудника по имени и фамилии
CREATE FUNCTION `sotr` (sotr_name VARCHAR(40), sotr_lastname VARCHAR(40))
RETURNS INT DETERMINISTIC
READS SQL DATA
RETURN (SELECT s.`id` FROM `shtat`s WHERE s.`name` = sotr_name AND s.`lastname` = sotr_lastname);

# Проверяем работу функции с существующим сотрудником
SELECT SOTR('Александр', 'Розембаум');
# и с не существующим
SELECT SOTR('Александр', 'Розембаум!!');

# сделал еще пример процедуры для вывода информации о сотруднике 
DELIMITER //
CREATE PROCEDURE `pr_sotr` (sotr_id INT)
BEGIN
	SELECT s.`lastname`, s.`name`, s.`salary`, d.`name` AS department, `dates`.`start_date` 
    FROM `shtat` s 
    LEFT JOIN depart d ON d.`id` = s.`dept`
    LEFT JOIN `dates` dates ON dates.sotr_id = s.id
    WHERE s.`id` = sotr_id;
END//
DELIMITER ;
#  проверяю как работает созданная процедура
CALL pr_sotr(8);

#3. Создать триггер, который при добавлении нового сотрудника будет выплачивать ему
# вступительный бонус, занося запись в таблицу salary.
-- Создал таблицу зарплат
CREATE TABLE IF NOT EXISTS `salary`(
`id` INT NOT NULL AUTO_INCREMENT,
`sotr_id`INT NOT NULL,
`salary` INT,
`bonus` INT,
PRIMARY KEY (`id`),
CONSTRAINT `sotr_id` FOREIGN KEY (`sotr_id`)
REFERENCES `shtat`(`id`)
ON UPDATE CASCADE
ON DELETE RESTRICT
)DEFAULT CHARSET UTF8MB4 COLLATE UTF8MB4_UNICODE_CI;

-- создал тригер для заполнения таблиц dates и salary 
delimiter //
CREATE TRIGGER `add_new_sotr`
AFTER INSERT ON `shtat` 
FOR EACH ROW
BEGIN
	INSERT INTO `dates` (`sotr_id`, `start_date`) VALUES (NEW.id, CURDATE());
    SET @salary = (SELECT s.`salary` FROM (SELECT id, salary FROM `shtat` ORDER BY id DESC LIMIT 1) s);
    -- select @salary;
	INSERT INTO `salary` (`sotr_id`,`salary`,`bonus`) VALUES (NEW.id, @salary, @salary * 0.5);
END;
//
delimiter ;

-- проверяю работу своего трига на примере добавления одной записи
INSERT INTO `shtat` 
(`name`,`lastname`,`dept`,`salary`) VALUES 
('Марина','Влади',1,45000);