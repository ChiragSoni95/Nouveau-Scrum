drop table sandbox;

create table sandbox(
sandbox_id int AUTO_INCREMENT primary key, 
proj_id int NOT NULL, 
user_stories_list varchar(100)
);