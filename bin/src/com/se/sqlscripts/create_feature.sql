drop table feature;

create table feature(
feature_id int AUTO_INCREMENT primary key,
name varchar(50) NOT NULL,
comments varchar(300),
created_by int,
create_time timestamp,
priority int NOT NULL,
effort double,
project_id int
);