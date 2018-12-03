drop table user_stories;

create table user_stories(
user_story_id int NOT NULL AUTO_INCREMENT primary key,
project_id int NOT NULL, 
user_story_name varchar(100) NOT NULL,
status varchar(15), 
user_story_type varchar(15), 
feature_id int, 
dependency_description varchar(150),
created_by int, 
assigned_to int, 
watch_list int, 
estimation double,
updated_timestamp timestamp,
location varchar(50),
comments varchar(300),
priority int);

alter table user_stories add column complexity varchar(20);

ALTER TABLE user_stories
MODIFY COLUMN assigned_to varchar(100);
