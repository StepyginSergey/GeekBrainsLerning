#Приступил к конфигурированию мастер сервера

#В настройках виртуальной машины обновил мак адрес, для изменения ip адреса
#Запустил виртуальную машину и после загрузки выполнил в терминале команду

ip a

#получил следующий адрес

inet 192.168.43.237/24 brd 192.168.43.255 scope global dynamic enp0s3

#(Так же заметил один важный момент.Компьютер должен быть подключен к сети.
#так как я делал настройку в дороге, то не сразу сообразил почему команда ip a 
#не выводит адрес для доступа по сети)

#на виртуальной машине в каталоге /etc/mysql/conf.d
#сделал конфигурационный файл с названием master.cnf и заполнил его следующими строками:

[mysqld]

server-id = 1

log_bin = /var/log/mysql/bin.log

#binlog_ignore_db = mysql, phpmyadmin, perfomance_schema, information_schema, sys 

binlog_do_db = employees

#после изменения конфигурации перезапустить сервер командой(на всякий случай выполнил команду пару раз):
service mysql restart

#Затем подключился к mysql и выполнид следующие команды:
root@MySQL8:~# mysql -u root -p
Enter password: ******

mysql> GRANT REPLICATION SLAVE ON *.* TO 'root'@'%';
Query OK, 0 rows affected (0,00 sec)

mysql> FLUSH PRIVILEGES;
Query OK, 0 rows affected (0,00 sec)

mysql> SHOW MASTER STATUS;
+------------+----------+--------------+------------------+-------------------+
| File       | Position | Binlog_Do_DB | Binlog_Ignore_DB | Executed_Gtid_Set |
+------------+----------+--------------+------------------+-------------------+
| bin.000028 |      538 | employees    |                  |                   |
+------------+----------+--------------+------------------+-------------------+
1 row in set (0,00 sec)


#Затем сделал дамп
root@MySQL8:~# mysqldump -u root -p123123 employees --routines > /var/20190311.sql


#Сделал конфигурацю подчиненного сервера
[mysqld]

# id сервера в рамках одного кластера строго уникален
server-id = 2

# bin log (имя файла как на мастере)
log_bin = bin.log 

# базы для репликации (точно такие же как на мастере)
binlog_do_db = lesson3

# relay log (имя файла произвольное)
relay-log = /var/log/mysql/relay.log


#Выполнил команду на загрузку дампа на mysql  подчиненного сервера
#получил следующий текст

root@MySQL8:~# mysqldump -u root -p123123 employees --routines < /var/20190311.sql
mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- MySQL dump 10.13  Distrib 8.0.13, for Linux (x86_64)
--
-- Host: localhost    Database: employees
-- ------------------------------------------------------
-- Server version       8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
mysqldump: Got error: 1049: Unknown database 'employees' when selecting the database
root@MySQL8:~# mysqldump -u root -p123123 employees --routines < /var/20190311.sql
mysqldump: [Warning] Using a password on the command line interface can be insecure.
-- MySQL dump 10.13  Distrib 8.0.13, for Linux (x86_64)
--
-- Host: localhost    Database: employees
-- ------------------------------------------------------
-- Server version       8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping routines for database 'employees'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-03-11 21:03:42
root@MySQL8:~#

#написано что дамп выполнился, но данные не загрузились.
#Поэтому сделал дамп через workbench

#На подчиненном сервере выполнил следующие комманды
mysql> STOP SLAVE;
Query OK, 0 rows affected, 1 warning (0,00 sec)

mysql> RESET SLAVE;
Query OK, 0 rows affected (0,00 sec)

mysql> CHANGE MASTER TO MASTER_HOST='192.168.43.237', MASTER_USER='root', MASTER_PASSWORD='123123', MASTER_LOG_FILE='bin.000028', MASTER_LOG_POS=538;
Query OK, 0 rows affected (0,00 sec)

# запускаем сервер как подчиненный сервер
mysql> START SLAVE;
Query OK, 0 rows affected (0,05 sec)

# смотрим статус слейва
mysql> show slave status \G
*************************** 1. row ***************************
               Slave_IO_State: Waiting for master to send event
                  Master_Host: 192.168.43.237
                  Master_User: root
                  Master_Port: 3306
                Connect_Retry: 60
              Master_Log_File: bin.000028
          Read_Master_Log_Pos: 538
               Relay_Log_File: relay.000003
                Relay_Log_Pos: 316
        Relay_Master_Log_File: bin.000028
             Slave_IO_Running: Yes
            Slave_SQL_Running: Yes
              Replicate_Do_DB:
          Replicate_Ignore_DB:
           Replicate_Do_Table:
       Replicate_Ignore_Table:
      Replicate_Wild_Do_Table:
  Replicate_Wild_Ignore_Table:
                   Last_Errno: 0
                   Last_Error:
                 Skip_Counter: 0
          Exec_Master_Log_Pos: 538
              Relay_Log_Space: 514
              Until_Condition: None
               Until_Log_File:
                Until_Log_Pos: 0
           Master_SSL_Allowed: No
           Master_SSL_CA_File:
           Master_SSL_CA_Path:
              Master_SSL_Cert:
            Master_SSL_Cipher:
               Master_SSL_Key:
        Seconds_Behind_Master: 0
Master_SSL_Verify_Server_Cert: No
                Last_IO_Errno: 0
                Last_IO_Error:
               Last_SQL_Errno: 0
               Last_SQL_Error:
  Replicate_Ignore_Server_Ids:
             Master_Server_Id: 1
                  Master_UUID: 3a90a3b6-f1ab-11e8-8365-080027ad0e39
             Master_Info_File: mysql.slave_master_info
                    SQL_Delay: 0
          SQL_Remaining_Delay: NULL
      Slave_SQL_Running_State: Slave has read all relay log; waiting for more updates
           Master_Retry_Count: 86400
                  Master_Bind:
      Last_IO_Error_Timestamp:
     Last_SQL_Error_Timestamp:
               Master_SSL_Crl:
           Master_SSL_Crlpath:
           Retrieved_Gtid_Set:
            Executed_Gtid_Set:
                Auto_Position: 0
         Replicate_Rewrite_DB:
                 Channel_Name:
           Master_TLS_Version:
       Master_public_key_path:
        Get_master_public_key: 0
1 row in set (0,00 sec)

mysql>




#запросы на slave сервере
mysql> use employees;

Database changed
mysql> select * from `shtat`;
+----+--------------------+------------------------+------+----------+--------+
| id | name               | lastname               | dept | position | salary |
+----+--------------------+------------------------+------+----------+--------+
|  1 | Тарас              | Голомозый              |    1 | NULL     |  25000 |
|  2 | Антон              | Полонников             |    1 | NULL     |  35000 |
|  3 | Алена              | Звягинцева             |    1 | NULL     |  45000 |
|  4 | Владимир           | Питерский              |    1 | NULL     |  40000 |
|  5 | Анатолий           | Башлыков               |    3 | NULL     |  30000 |
|  6 | Ирина              | Кенина                 |    3 | NULL     |  50000 |
|  7 | Сергей             | Котельников            |    6 | NULL     |  45000 |
|  8 | Александр          | Розембаум              |    6 | NULL     |  60000 |
|  9 | Михаил             | Круг                   |    6 | NULL     |  28000 |
| 10 | Ирина              | Алегрова               |    4 | NULL     |  50000 |
| 15 | Антон              | Логинов                |    5 | NULL     |  35000 |
| 18 | Марина             | Влади                  |    1 | NULL     |  45000 |
+----+--------------------+------------------------+------+----------+--------+
12 rows in set (0,00 sec)

Аналогичный запрос выполнил на мастер сервере

потом на мастер сервере выполнил удаление пользователя с id = 15
mysql> DELETE FROM `shtat` WHERE `id` = 15;
Query OK, 1 row affected (0,13 sec)

данные реплицировались и запрос по таблице штат, что на мастере, что на слейве 
вывел таблицу без пользователя с id =15

mysql> select * from `shtat`;
+----+--------------------+------------------------+------+----------+--------+
| id | name               | lastname               | dept | position | salary |
+----+--------------------+------------------------+------+----------+--------+
|  1 | Тарас              | Голомозый              |    1 | NULL     |  25000 |
|  2 | Антон              | Полонников             |    1 | NULL     |  35000 |
|  3 | Алена              | Звягинцева             |    1 | NULL     |  45000 |
|  4 | Владимир           | Питерский              |    1 | NULL     |  40000 |
|  5 | Анатолий           | Башлыков               |    3 | NULL     |  30000 |
|  6 | Ирина              | Кенина                 |    3 | NULL     |  50000 |
|  7 | Сергей             | Котельников            |    6 | NULL     |  45000 |
|  8 | Александр          | Розембаум              |    6 | NULL     |  60000 |
|  9 | Михаил             | Круг                   |    6 | NULL     |  28000 |
| 10 | Ирина              | Алегрова               |    4 | NULL     |  50000 |
| 18 | Марина             | Влади                  |    1 | NULL     |  45000 |
+----+--------------------+------------------------+------+----------+--------+
11 rows in set (0,00 sec)

УРА!!! Победа!


//********************************************************

MongoDB

//********************************************************

-- Переключился на базу сотрудники
> use employees
switched to db employees

-- Добавил несколько пользователей в коллекцию штат
> db.shtat.insert({name:"Alex"})
WriteResult({ "nInserted" : 1 })
> db.shtat.insert({name:"Alex", lastname:"Flex", dept:1, salary:30000})
WriteResult({ "nInserted" : 1 })
> db.shtat.insert({name:"Andrey", lastname:"Ivanov", dept:1, salary:35000})
WriteResult({ "nInserted" : 1 })
> db.shtat.insert({name:"Sasha", lastname:"Lavrenov", dept:2, salary:45000})
WriteResult({ "nInserted" : 1 })
> db.shtat.insert({name:"Slava", lastname:"Rystcov", dept:2, salary:40000})
WriteResult({ "nInserted" : 1 })
> db.shtat.insert({name:"Slava", lastname:"Boyko", dept:3, salary:50000})
WriteResult({ "nInserted" : 1 })

-- Сделал запрос по коллекции штат
> db.shtat.find()
{ "_id" : ObjectId("5c8a6bf079a60c736eafc0b4"), "name" : "Alex" }
{ "_id" : ObjectId("5c8a6d2579a60c736eafc0b5"), "name" : "Alex", "lastname" : "Flex", "dept" : 1, "salary" : 30000 }
{ "_id" : ObjectId("5c8a6d5479a60c736eafc0b6"), "name" : "Andrey", "lastname" : "Ivanov", "dept" : 1, "salary" : 35000 }
{ "_id" : ObjectId("5c8a6dcd79a60c736eafc0b7"), "name" : "Sasha", "lastname" : "Lavrenov", "dept" : 2, "salary" : 45000 }
{ "_id" : ObjectId("5c8a6dfc79a60c736eafc0b8"), "name" : "Slava", "lastname" : "Rystcov", "dept" : 2, "salary" : 40000 }
{ "_id" : ObjectId("5c8a6e6279a60c736eafc0b9"), "name" : "Slava", "lastname" : "Boyko", "dept" : 3, "salary" : 50000 }

-- Сделал запрос по имени Слава - получил две записи
> db.shtat.find({"name":"Slava"})
{ "_id" : ObjectId("5c8a6dfc79a60c736eafc0b8"), "name" : "Slava", "lastname" : "Rystcov", "dept" : 2, "salary" : 40000 }
{ "_id" : ObjectId("5c8a6e6279a60c736eafc0b9"), "name" : "Slava", "lastname" : "Boyko", "dept" : 3, "salary" : 50000 }


-- Сделал запрос на вывод сотрудников с зарплатой больше 40000
> db.shtat.find({"salary":{$gt:40000}})
{ "_id" : ObjectId("5c8a6dcd79a60c736eafc0b7"), "name" : "Sasha", "lastname" : "Lavrenov", "dept" : 2, "salary" : 45000 }
{ "_id" : ObjectId("5c8a6e6279a60c736eafc0b9"), "name" : "Slava", "lastname" : "Boyko", "dept" : 3, "salary" : 50000 }


-- Обновил зарплату у сотрудникас фамилией Бойко
> db.shtat.update({"lastname":"Boyko"},{"salary":55000})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })
> db.shtat.find()
{ "_id" : ObjectId("5c8a6bf079a60c736eafc0b4"), "name" : "Alex" }
{ "_id" : ObjectId("5c8a6d2579a60c736eafc0b5"), "name" : "Alex", "lastname" : "Flex", "dept" : 1, "salary" : 30000 }
{ "_id" : ObjectId("5c8a6d5479a60c736eafc0b6"), "name" : "Andrey", "lastname" : "Ivanov", "dept" : 1, "salary" : 35000 }
{ "_id" : ObjectId("5c8a6dcd79a60c736eafc0b7"), "name" : "Sasha", "lastname" : "Lavrenov", "dept" : 2, "salary" : 45000 }
{ "_id" : ObjectId("5c8a6dfc79a60c736eafc0b8"), "name" : "Slava", "lastname" : "Rystcov", "dept" : 2, "salary" : 40000 }
{ "_id" : ObjectId("5c8a6e6279a60c736eafc0b9"), "salary" : 55000 }

-- и только после того как вывел все записи, что бы посмотреть результаты апдейта понял, что повторил ту же ситуацию, что и
занятии была. то есть сделал обновление всей записи сотрудника, а не отдельного атрибута)))

-- Прищлось восстанавливать запись
> db.shtat.insert({name:"Slava", lastname:"Boyko", dept:3, salary:50000})
WriteResult({ "nInserted" : 1 })

-- А заодно отработал команду удаления записейпо идентификатору
> db.shtat.remove({"_id":ObjectId("5c8a6e6279a60c736eafc0b9")})
WriteResult({ "nRemoved" : 1 })
> db.shtat.find()
{ "_id" : ObjectId("5c8a6bf079a60c736eafc0b4"), "name" : "Alex" }
{ "_id" : ObjectId("5c8a6d2579a60c736eafc0b5"), "name" : "Alex", "lastname" : "Flex", "dept" : 1, "salary" : 30000 }
{ "_id" : ObjectId("5c8a6d5479a60c736eafc0b6"), "name" : "Andrey", "lastname" : "Ivanov", "dept" : 1, "salary" : 35000 }
{ "_id" : ObjectId("5c8a6dcd79a60c736eafc0b7"), "name" : "Sasha", "lastname" : "Lavrenov", "dept" : 2, "salary" : 45000 }
{ "_id" : ObjectId("5c8a6dfc79a60c736eafc0b8"), "name" : "Slava", "lastname" : "Rystcov", "dept" : 2, "salary" : 40000 }
{ "_id" : ObjectId("5c8a713479a60c736eafc0ba"), "name" : "Slava", "lastname" : "Boyko", "dept" : 3, "salary" : 50000 }

-- Теперь обновил уже атрибут с зарплатой
> db.shtat.update({"lastname" : "Boyko"},{$set:{"salary":55000}})
WriteResult({ "nMatched" : 1, "nUpserted" : 0, "nModified" : 1 })
> db.shtat.find()
{ "_id" : ObjectId("5c8a6bf079a60c736eafc0b4"), "name" : "Alex" }
{ "_id" : ObjectId("5c8a6d2579a60c736eafc0b5"), "name" : "Alex", "lastname" : "Flex", "dept" : 1, "salary" : 30000 }
{ "_id" : ObjectId("5c8a6d5479a60c736eafc0b6"), "name" : "Andrey", "lastname" : "Ivanov", "dept" : 1, "salary" : 35000 }
{ "_id" : ObjectId("5c8a6dcd79a60c736eafc0b7"), "name" : "Sasha", "lastname" : "Lavrenov", "dept" : 2, "salary" : 45000 }
{ "_id" : ObjectId("5c8a6dfc79a60c736eafc0b8"), "name" : "Slava", "lastname" : "Rystcov", "dept" : 2, "salary" : 40000 }
{ "_id" : ObjectId("5c8a713479a60c736eafc0ba"), "name" : "Slava", "lastname" : "Boyko", "dept" : 3, "salary" : 55000 }






