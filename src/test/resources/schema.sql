drop table if exists book;
drop table if exists user;

create table book(
     book_id int(16) AUTO_INCREMENT PRIMARY KEY,
     title        varchar(40) NOT NULL,
     summary       varchar(80) NOT NULL,
     price int(20) Not null,
     dev_course      int NOT NULL,
     junior int     NOT NULL,
     middle int Not Null
--       created_at  datetime(6) NOT NULL DEFAULT CURRENT_TIMESTAMP (6)
)

create table user(
     user_id int(16) AUTO_INCREMENT PRIMARY KEY,
     email        varchar(40) NOT NULL,
     password       varchar(80) NOT NULL,
     user_name varchar(20) Not null,
     phoneNumber      varchar(20) NOT NULL,
     authority varchar(20)     NOT NULL,
)