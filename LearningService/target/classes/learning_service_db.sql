drop database if exists learning_service;
create database learning_service;

drop user if exists 'learning_srv_usr'@'%';
create user 'learning_srv_usr'@'%' identified by 'learning_srv_usr';
grant select, insert, update, delete on learning_service.* to 'learning_srv_usr'@'%';

use learning_service;

create table students (
    id int not null auto_increment primary key,
    registered datetime not null default now(),
    first_name varchar(100) not null,
    surname varchar(100) not null,
    email varchar(100) not null,
    email_notification bit not null default 0
);

create table courses (
    id int not null auto_increment primary key,
    title varchar(100) not null,
    description varchar(1000) not null,
    study_time decimal(6,2) not null
);

create table student_courses (
    student_id int not null,
    course_id int not null,
    registered datetime not null default now(),
    completed datetime,
    message_sent bit not null default 0,
    primary key (student_id, course_id),
    foreign key (student_id) references students (id),
    foreign key (course_id) references courses (id)
);







