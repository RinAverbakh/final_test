#создание базы данных на подключённом сервере
create database if not exists friends_of_people;

#настройка созданной базы данных
alter database friends_of_people character set utf8mb4 collate utf8mb4_unicode_ci;

use friends_of_people;

#создание основной таблицы 
CREATE TABLE friends (
	id INT PRIMARY KEY AUTO_INCREMENT,
    group_of_animals VARCHAR(50) NOT NULL,
    animal_species VARCHAR(50) UNIQUE
    );

#наполнение основной таблицы
insert into friends (group_of_animals, animal_species)
values 
	('домашние животные', 'собака'),
	('домашние животные', 'кошка'),
	('домашние животные', 'хомяк'),
	('вьючные животные', 'лошадь'),
	('вьючные животные', 'осёл'),
	('вьючные животные', 'верблюд');

#создание таблицы собаки
CREATE TABLE dogs (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    commands TEXT
    );

#наполнение табоицы собаки
insert into dogs (name, date_of_birth, commands)
values 
	('Бобик', '2023-10-16', 'сидеть, стоять, голос, место'),
	('Тузик', '2020-05-10', 'сидеть, стоять, лежать'),
	('Лора', '2018-12-30', 'зайчик, стыдно, крест'),
	('Джек', '2025-01-24', '');

#Создание таблицы кошки
CREATE TABLE cats (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    skills TEXT
    );

#Наполнение таблицы кошки
insert into cats (name, date_of_birth, skills)
values 
	('Барсик', '2022-08-27', 'ходить в латок, просить кушать'),
	('Пушок', '2024-11-29', '');

#Создание таблицы хомяки
CREATE TABLE hamsters (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    runs_in_a_wheel ENUM('TRUE', 'FALSE') not null default 'FALSE'
    );

#Наполнение таблицы хомяки
insert into hamsters (name, date_of_birth, runs_in_a_wheel)
values 
	('Пушок', '2024-04-19', 'TRUE'),
	('Хома', '2025-01-02', 'FALSE');

#Создание таблицы лошади
CREATE TABLE horses (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    commands TEXT
    );

#Наполнение таблицы лошади
insert into horses (name, date_of_birth, commands)
values 
	('Буран', '2015-07-14', 'вперёд, стой, рысь, хоп, шагом, тише'),
	('Гамлет', '2019-03-07', '');

#Создание таблицы ослы
CREATE TABLE donkeys (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    carrying_things ENUM('TRUE', 'FALSE') not null default 'FALSE',
    carrying_people ENUM('TRUE', 'FALSE') not null default 'FALSE'
    );

#Наполнение таблицы ослы
insert into donkeys (name, date_of_birth, carrying_things, carrying_people)
values 
	('Маргаритка', '2020-02-26', 'TRUE', 'FALSE'),
	('Чоко', '2022-09-13', 'FALSE', 'FALSE');

#Создание таблицы верблюды
CREATE TABLE camels (
	id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    date_of_birth DATE,
    gives_milk ENUM('TRUE', 'FALSE') not null default 'FALSE',
    gives_wool ENUM('TRUE', 'FALSE') not null default 'FALSE'
    );

#Наполнение таблицы верблюды
insert into camels (name, date_of_birth, gives_milk, gives_wool)
values 
	('Шпулька', '2016-07-18', 'TRUE', 'TRUE');

#удаление таблицы верблюды
drop table camels;

#Удаление строки с верблюдами из основной таблицы
delete from friends
where animal_species = 'верблюд';

#Создание таблицы молодые животные
create table young_animals (
	id INT PRIMARY KEY AUTO_INCREMENT,
	species ENUM('dog', 'cat', 'hamster', 'horse', 'donkey'),
	name VARCHAR(50) not null,
	age_month INT not null 
	);

#Заполнение таблицы молодые животные
insert into young_animals (species, name, age_month)
select 
	'dog',
	name,
	TIMESTAMPDIFF(month, date_of_birth, CURDATE())
from 
	dogs
where 
	TIMESTAMPDIFF(month, date_of_birth, CURDATE()) between 12 and 36
union all 
select 
	'cat',
	name,
	TIMESTAMPDIFF(month, date_of_birth, CURDATE())
from 
	cats
where 
	TIMESTAMPDIFF(month, date_of_birth, CURDATE()) between 12 and 36
union all 
select 
	'hamster',
	name,
	TIMESTAMPDIFF(month, date_of_birth, CURDATE())
from 
	hamsters
where 
	TIMESTAMPDIFF(month, date_of_birth, CURDATE()) between 12 and 36
union all 
select 
	'horse',
	name,
	TIMESTAMPDIFF(month, date_of_birth, CURDATE())
from 
	horses
where 
	TIMESTAMPDIFF(month, date_of_birth, CURDATE()) between 12 and 36
union all 
select 
	'donkey',
	name,
	TIMESTAMPDIFF(month, date_of_birth, CURDATE())
from 
	donkeys
where 
	TIMESTAMPDIFF(month, date_of_birth, CURDATE()) between 12 and 36;

#Объединение всех таблиц в запросе
select 
	f.group_of_animals,
	f.animal_species,
	coalesce(d.name, c.name, ham.name, hor.name, don.name) as name,
	coalesce(d.date_of_birth, c.date_of_birth, ham.date_of_birth, hor.date_of_birth, don.date_of_birth) as date_of_birth
from
	friends f
left join 
	dogs d on f.animal_species = 'собака'
left join 
	cats c on f.animal_species = 'кошка'
left join 
	hamsters ham on f.animal_species = 'хомяк'
left join 
	horses hor on f.animal_species = 'лошадь'
left join 
	donkeys don on f.animal_species = 'осёл';