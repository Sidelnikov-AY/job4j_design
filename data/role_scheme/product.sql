create table product(
    id serial primary key,
    name varchar(255),
    type_id int,
    expired_date date,
    price float8
);

create table type(
    id serial primary key,
    name varchar(255)
);


insert into product(name, type_id, expired_date, price) values('сыр плавленый', 1, '2021-10-16', 11);
insert into product(name, type_id, expired_date, price) values('сыр гауда', 1, '2021-10-18', 600);
insert into product(name, type_id, expired_date, price) values('сыр моцарелла', 1, '2021-11-17', 150);

insert into product(name, type_id, expired_date, price) values('молоко жирности 3', 2, '2021-10-25', 55);
insert into product(name, type_id, expired_date, price) values('молоко жирности 2', 2, '2021-10-24', 50);
insert into product(name, type_id, expired_date, price) values('молоко обезжиренное', 2, '2021-09-24', 45);
insert into product(name, type_id, expired_date, price) values('молоко пастеризованное', 2, '2021-12-31', 70);

insert into product(name, type_id, expired_date, price) values('мороженое пломбир в стаканчике', 3, '2021-11-01', 30);
insert into product(name, type_id, expired_date, price) values('мороженое эскимо на палочке', 3, '2021-11-30', 40);
insert into product(name, type_id, expired_date, price) values('мороженое щербет', 3, '2021-10-15', 25);

insert into type(name) values("СЫР");
insert into type(name) values("МОЛОКО");
insert into type(name) values("МОРОЖЕНОЕ");

--1. Написать запрос получение всех продуктов с типом "СЫР"
select * from product where type_id =1;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from product where name like 'мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product where expired_date < '2021-10-17';

--4. Написать запрос, который выводит самый дорогой продукт.
select name, max(price) from product
group by name
order by max(price) desc
limit 1;

--5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select type.name, count(product.type_id) from product join type on type.id = product.type_id
group by type.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select * from product where type_id =1 or type_id =2;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. Под количеством подразумевается количество продуктов определенного типа. Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
select type.name, count(product.type_id) from product join type on type.id = product.type_id
group by type.name
having count(product.type_id) < 10;

--8. Вывести все продукты и их тип.
select * from product join type on product.type_id = type.id;