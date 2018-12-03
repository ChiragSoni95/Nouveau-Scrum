drop table project;

create table project (
id int primary key NOT NULL AUTO_INCREMENT,
name varchar(50) NOT NULL,  
created_by varchar(50), 
created_date timestamp, 
owner varchar(50), 
user_list varchar(100),
description varchar(500),
project_type varchar(50),
start_date date, 
end_date date,
sprint_duration double);