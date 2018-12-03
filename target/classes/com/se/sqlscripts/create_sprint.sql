
drop table sprint;

create table sprint(
sprint_id int NOT NULL AUTO_INCREMENT primary key,
project_id int NOT NULL,
release_id int NOT NULL,
sprint_number int NOT NULL,
start_date date,
end_date date,
description varchar(300),
notes varchar(300),
status varchar(30)
);

ALTER TABLE sprint
Add COLUMN release_id int NOT NULL;