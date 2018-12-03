drop table product_backlog;

create table product_backlog(
product_backlog_id int AUTO_INCREMENT primary key,
proj_id int NOT NULL, 
user_stories_list varchar(100)
);