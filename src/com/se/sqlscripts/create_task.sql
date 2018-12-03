drop table task;

create table task(
task_id int AUTO_INCREMENT primary key,
project_id int,
task_name varchar(50),
description varchar(100),
user_story_id int,
status varchar(15),
estimate double,
created_time timestamp,
update_time timestamp,
assigned_to int,
comments varchar(300),
priority int
);

ALTER TABLE task
MODIFY COLUMN assigned_to varchar(100);