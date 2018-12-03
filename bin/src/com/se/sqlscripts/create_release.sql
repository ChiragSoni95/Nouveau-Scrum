drop table Releases;

create table Releases (
release_id int NOT NULL AUTO_INCREMENT primary key,
project_id int,
start_date date,
end_date date,
status varchar(50),
goals varchar(100),
comments varchar(300)
)