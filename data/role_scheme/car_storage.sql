create table body(
    id serial primary key,
    name varchar(255)
);

create table engine(
    id serial primary key,
    name varchar(255)
);

create table transmission(
    id serial primary key,
    name varchar(255)
);

create table car(
    id serial primary key,
    name varchar(255),
    body_id int references body(id),
    engine_id int references engine(id),
    transmission_id int references transmission(id)
);

insert into body(name) values('vaz_body 2110');
insert into body(name) values('toyota_body camry');

insert into engine(name) values('engine 1.5');
insert into engine(name) values('engine 2.0');
insert into engine(name) values('turbo_engine 1.4');

insert into transmission(name) values('mechanical');
insert into transmission(name) values('DSG');
insert into transmission(name) values('variator');

insert into car(name, body_id, engine_id, transmission_id) values('vaz 2110', 7, 10, 10);
insert into car(name, body_id, engine_id, transmission_id) values('toyota camry', 8, 11, 12);

--1) Вывести список всех машин и все привязанные к ним детали.
select c.name, b.name,  e.name, t.name from car c
join body b on c.body_id = b.id
join engine e on c.engine_id = e.id
join transmission t on c.transmission_id = t.id

--2) Вывести отдельно детали (1 деталь - 1 запрос), которые не используются НИ в одной машине,
--кузова, двигатели, коробки передач.
select b.name from body b left join car c on b.id = c.body_id where c.body_id is null;
select e.name from engine e left join car c on e.id = c.engine_id where c.engine_id is null;
select t.name from transmission t left join car c on t.id = c.engine_id where c.engine_id is null;
