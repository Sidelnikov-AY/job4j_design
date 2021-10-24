--Даны две сущности, представленные в таблицах departments и emploers. Отношение one-to-many.
--В таблицах только поле name.
--1. Создать таблицы и заполнить их начальными данными
create table departments(
    id serial primary key,
    name varchar(255)
);

create table emploers(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('department1');
insert into departments(name) values ('department2');
insert into departments(name) values ('department3');

insert into emploers(name, department_id) values ('emploee1', 1);
insert into emploers(name, department_id) values ('emploee2', 1);
insert into emploers(name, department_id) values ('emploee3', 2);
insert into emploers(name, department_id) values ('emploee4', 2);
insert into emploers(name, department_id) values ('emploee5', null);
insert into emploers(name, department_id) values ('emploee6', null);

--2. Выполнить запросы с left, rigth, full, cross соединениями
select * from emploers e left join departments d on e.department_id = d.id;
select * from emploers e right join departments d on e.department_id = d.id;
select * from emploers e full join departments d on e.department_id = d.id;
select * from emploers e cross join departments d;

--3. Используя left join найти департаменты, у которых нет работников
select * from departments d left join emploers e on e.department_id = d.id where e.department_id is null;

--4. Используя left и right join написать запросы, которые давали бы одинаковый результат.
select * from emploers e left join departments d on e.department_id = d.id;
select * from departments d right join emploers e on e.department_id = d.id;

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. Используя cross join
--составить все возможные разнополые пары
create table teens(
    name varchar(255),
    gender varchar(255)
);

insert into teens(name, gender) values ('Max', 'male');
insert into teens(name, gender) values ('Oleg', 'male');
insert into teens(name, gender) values ('Lena', 'female');
insert into teens(name, gender) values ('Olga', 'female');

select t1.name, t2.gender from teens t1 cross join teens t2;