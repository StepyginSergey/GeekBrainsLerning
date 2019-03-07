USE `employees`;

SELECT * FROM `depart`;
SELECT * FROM `shtat`;

# 1. Выбрать среднюю зарплату по отделам.
SELECT d.`name`, AVG(s.`salary`) AS avg_salary FROM `shtat` s LEFT JOIN `depart` d ON d.`id` = s.`dept` GROUP BY d.`name`;
# 2. Выбрать максимальную зарплату у сотрудника.
SELECT MAX(s.`salary`) AS max_salary FROM `shtat` s ;
# 2.1 Вывел всех сотрудников у которых максимальная зарплата
SELECT CONCAT(s.`name`, ' ', s.`lastname`) AS `name`, s.`salary` FROM `shtat` s WHERE s.`salary` IN ( SELECT MAX(s.`salary`) FROM `shtat` s);
# 3. Удалить одного сотрудника, у которого максимальная зарплата.
SET @employee_id := (SELECT GROUP_CONCAT(s.`id` SEPARATOR ', ') AS id FROM `shtat` s WHERE s.`salary` IN ( SELECT MAX(s.`salary`) FROM `shtat` s));
#select @employee_id;
DELETE FROM `shtat` WHERE `shtat`.`id` IN (@employee_id);
# 4. Посчитать количество сотрудников во всех отделах.
SELECT d.`name`, COUNT(s.`id`) AS `number`  FROM `depart` d LEFT JOIN `shtat` s ON s.`dept` = d.id GROUP BY d.`name`;
# 5. Найти количество сотрудников в отделах и посмотреть, сколько всего денег получает отдел.
SELECT d.`name`, COUNT(s.`id`) AS `number`, SUM(s.`salary`)  FROM `depart` d LEFT JOIN `shtat` s ON s.`dept` = d.id GROUP BY d.`name`;