drop table Releases;

create table Releases (
release_id int NOT NULL AUTO_INCREMENT primary key,
release_number int NOT NULL,
project_id int NOT NULL,
start_date date,
end_date date,
status varchar(50),
goals varchar(100),
comments varchar(300)
)