-- Social network
CREATE DATABASE IF NOT EXISTS social_network;
-- переключаемся на созданную базу
USE social_network;

-- создаем таблицу тип контента
create table if not EXISTS `content_type` (
id int not null PRIMARY key AUTO_INCREMENT,
`name` VARCHAR(250) NOT NULL,
INDEX `name_idx` (`name` ASC) VISIBLE
);

-- создаем таблицу пользователи
CREATE TABLE if not EXISTS `users` (
id int not null PRIMARY KEY AUTO_INCREMENT,
`name` VARCHAR(100) not null,
`lastname` VARCHAR(100) NOT null,
INDEX `fullname` (`name`,`lastname` ASC) VISIBLE
); 

-- создаем таблицу контент, для хранения контента пользователя
CREATE TABLE IF NOT EXISTS `content` (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`user_id` INT NOT NULL,
`content_type_id` INT NOT NULL,
`content_data` VARCHAR(250) NOT NULL,
CONSTRAINT `content_user_id` FOREIGN KEY (`user_id`)
    REFERENCES `users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
CONSTRAINT `content_type_id` FOREIGN KEY (`content_type_id`)
    REFERENCES `content_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);

-- создаем таблицу лайков
CREATE TABLE IF NOT EXISTS `likes` (
id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
`user_id_put` INT NOT NULL,
`user_id_to` INT NOT NULL,
`content_id` INT NOT NULL,
`like_received` BOOL DEFAULT 0,
`recall` BOOL DEFAULT 0,
CONSTRAINT `likes_content_id` FOREIGN KEY (`content_id`)
    REFERENCES `content` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
CONSTRAINT `likes_user_id_put` FOREIGN KEY (`user_id_put`)
    REFERENCES `users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
CONSTRAINT `likes_user_id_to` FOREIGN KEY (`user_id_to`)
    REFERENCES `users` (`id`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE
);


INSERT INTO `content_type` (`name`) VALUES ('Пользователь'),('Комментарий'), ('Фотография'), ('Видео');
SELECT * from `content_type`;

INSERT INTO `users` (`name`, `lastname`) VALUES ('A','A'),('B','B'),('C','C'),('D','D'),('E','E'),('F','F');
SELECT * FROM `users`;

INSERT INTO `content` (`user_id`, `content_type_id`, `content_data`) 
VALUES 
(1, 1,'page'), (1, 2,'coment'), (1, 3,'image1'), (1, 3,'image2'), (1, 4,'video1'), (1, 4,'video2'),
(2, 1,'page'), (2, 2,'coment'), (2, 3,'image1'), (2, 3,'image2'), (2, 4,'video1'), (2, 4,'video2'),
(3, 1,'page'), (3, 2,'coment'), (3, 3,'image1'), (3, 3,'image2'), (3, 4,'video1'), (3, 4,'video2'),
(4, 1,'page'), (4, 2,'coment'), (4, 3,'image1'), (4, 3,'image2'), (4, 4,'video1'), (4, 4,'video2'),
(5, 1,'page'), (5, 2,'coment'), (5, 3,'image1'), (5, 3,'image2'), (5, 4,'video1'), (5, 4,'video2'),
(6, 1,'page'), (6, 2,'coment'), (6, 3,'image1'), (6, 3,'image2'), (6, 4,'video1'), (6, 4,'video2')
;
select * from `content`;
INSERT INTO `likes` (`user_id_put`, `user_id_to`, `content_id`, `like_received`, `recall`) 
VALUES 
(1,2,7,1,0),(1,2,8,1,0),(1,2,9,1,0),(1,2,10,1,0),(1,2,11,1,0),(1,2,12,1,0),
(2,1,1,1,0),(2,1,2,1,0),(2,1,3,1,0),(2,1,4,1,0),(2,1,5,1,0),(2,1,6,1,0),
(3,1,1,1,0),(3,2,7,1,0),(3,2,9,1,0),(3,2,10,1,0),(3,2,11,1,0),(3,2,12,1,1),
(4,1,1,1,0),(4,2,8,1,0),(4,2,9,1,0),(4,2,10,1,0),(4,2,11,1,0),(4,2,12,1,0);
select * from likes;

/*
-- запрос на извечение взаимных лайков
select DISTINCT l.user_id_put, l.user_id_to, s.mutual_likes from likes l 
left join (select DISTINCT l2.user_id_put, l2.user_id_to, concat(l2.user_id_put, '<->',  l2.user_id_to) as mutual_likes from likes l2) s 
on s.user_id_put = l.user_id_to and s.user_id_to = l.user_id_put;
*/
# ● id пользователя;
# ● имя;
# ● лайков получено;
# ● лайков поставлено;
# ● взаимные лайки.
select u.id , u.name, count(received.id), s.num, mutual.mutual_likes from users u
left join likes received on received.user_id_to = u.id
left join (select count(l.id) as num, l.user_id_put from likes l GROUP BY l.user_id_put) s on s.user_id_put = u.id
left join (select DISTINCT l.user_id_put, l.user_id_to, s.mutual_likes from likes l 
			left join (select DISTINCT l2.user_id_put, l2.user_id_to, concat(l2.user_id_put, '<->',  l2.user_id_to) as mutual_likes from likes l2) s 
				on s.user_id_put = l.user_id_to and s.user_id_to = l.user_id_put) mutual on mutual.user_id_put = u.id
group by u.id, u.name
;




-- Задача 2. Для структуры из задачи 1 выведите список всех пользователей, которые поставили лайк
-- пользователям A и B (id задайте произвольно), но при этом не поставили лайк пользователю C.
select DISTINCT u.id, u.name , l.user_id_to
from users u 
join likes l on l.user_id_put = u.id and l.user_id_to in (select u2.id from users u2 where u2.name in ('A','B') and u2.name not in ('C'))
;