drop table Users;

create table Users(
user_id int AUTO_INCREMENT primary key,
user_name varchar(50) NOT NULL,
firstname varchar(25),
lastname varchar(25),
email varchar(50) NOT NULL,
role varchar(50),
password varchar(200) NOT NULL,
security_question1 int,
security_question2 int,
security_answer1 varchar(100),
security_answer2 varchar(100)
);