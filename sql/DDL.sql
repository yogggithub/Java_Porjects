CREATE DATABASE stumgnt;
USE stumgnt;

CREATE TABLE t_student (
	stuid INT PRIMARY KEY  AUTO_INCREMENT,
	stuname VARCHAR (20) not null ,
    stuage int not null,
	gender VARCHAR (6) not null,
	phone VARCHAR (20),
	hobby VARCHAR(50),
	comments VARCHAR(200)
);

CREATE TABLE t_user (
	userid INT PRIMARY KEY  AUTO_INCREMENT,
	username VARCHAR (10),
	`password` VARCHAR (10),
	expiredate DATE
);
    
    
