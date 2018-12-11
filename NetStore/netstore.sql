create database netstore;
use netstore;
create table categorys(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	description varchar(255)
);
create table books(
	id varchar(100) primary key,
	name varchar(100) not null unique,
	author varchar(100),
	price float(8,2),
	path varchar(100),
	filename varchar(100),
	description varchar(255),
	categoryId varchar(100),
	constraint category_id_fk foreign key(categoryId) references categorys(id)
);