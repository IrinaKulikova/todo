
drop database if exists azuretodo;

create database if not exists azuretodo character set utf8;

use azuretodo;

create table tasks(
id int auto_increment primary key,
title varchar(255) not null,
description text not null,
done boolean default false
);

insert into tasks(title,description) values('homework','deploy php web app');
insert into tasks(title,description) values('homework','deploy java web app');