create table products(
    id serial primary key,
    name text,
	storage integer,
	produced date
);
insert into products(name, storage, produced) values('колбаса', '10', '2021,09,08');
update products set name = 'сыр';
delete from products;
