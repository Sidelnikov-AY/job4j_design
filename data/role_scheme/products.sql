create table customer(
    id serial primary key,
    name varchar(255)
);

create table product(
    id serial primary key,
    name varchar(255),
    price float8,
    customer_id int references customer(id)
);

insert into customer(name) values ('Anton');
insert into customer(name) values ('Artem');

insert into product(name, price, customer_id) values ('milk', '2', 1);
insert into product(name, price, customer_id) values ('bread', '0.5', 1);
insert into product(name, price, customer_id) values ('chocolate', '1.5', 2);
insert into product(name, price) values ('apple', '1.5')

select * from customer inner join product as p on p.price >= 1.5;
select * from product as p inner join customer as c on p.customer_id = c.id;
select * from product as p inner join customer as c on p.id is null;

