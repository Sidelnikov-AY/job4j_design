// one to many
create table country(
    id serial primary key,
    name varchar(255)
);

create table cities(
    id serial primary key,
    name varchar(255),
    position_id int references country(id)
);

insert into country(name) values ('Russia');
insert into cities(name, position_id) VALUES ('Moscow', 1);
insert into cities(name, position_id) VALUES ('Perm', 1);

select * from cities;

select * from country where id in (select id from cities);

// many to many
 create table childrens(
     id serial primary key,
     name varchar(255)
 );

 create table books(
     id serial primary key,
     name varchar(255)
 );

 create table childrens_books(
     id serial primary key,
     children_id int references childrens(id),
     book_id int references books(id)
 );

insert into childrens(name) values ('Ivan');
insert into childrens(name) values ('Kirill');
insert into childrens(name) values ('Roman');

insert into books(name) values ('The Adventures of Pinocchio, or the Golden Key');
insert into books(name) values ('Lord of The Rings, part I');
insert into books(name) values ('Harry Potter');

insert into childrens_books(children_id, book_id) values (1, 1);
insert into childrens_books(children_id, book_id) values (1, 2);
insert into childrens_books(children_id, book_id) values (2, 1);
insert into childrens_books(children_id, book_id) values (2, 2);
insert into childrens_books(children_id, book_id) values (2, 3);
insert into childrens_books(children_id, book_id) values (3, 3);

//one-to-one
create table car(
    id serial primary key,
    vin varchar(255),
    number varchar(255)
);

create table people(
    id serial primary key,
    drive_license varchar(255)
);

create table registration_car(
    id serial primary key,
    car_id int references car(id) unique,
    people_id int references people(id) unique
);

insert into people(drive_license) values ('123 123456');
insert into car(vin, number) values ('CZXCC12424', 'X123XX00');
