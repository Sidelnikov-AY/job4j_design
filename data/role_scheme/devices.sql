create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices(name, price) values ('phone', 100), ('notebook', 1000),
('watch', 49.99), ('headphones',39.99);

insert into people(name) values ('Anton'), ('Artem'), ('Andrey');

insert into devices_people(device_id, people_id) values (1, 1), (2, 1), (3, 1), (4, 1);
insert into devices_people(device_id, people_id) values (1, 2), (2, 2);
insert into devices_people(device_id, people_id) values (1, 3);

select avg(price) from devices;