create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

create table fauna (
    id serial primary key,
    name text,
    avg_age int,
    discovery_date date
);

insert into fauna (id, name, avg_age, discovery_date) values (1, 'Jackal, indian', 6361, '1812-03-01 20:52:46');
insert into fauna (id, name, avg_age, discovery_date) values (2, 'Grouse, sage', 10327, '1830-05-30 08:23:12');
insert into fauna (id, name, avg_age, discovery_date) values (3, 'Otter, oriental short-clawed', 33403, '1884-01-29 23:36:45');
insert into fauna (id, name, avg_age, discovery_date) values (4, 'Eagle, african fish', 35903, '1878-03-25 18:22:14');
insert into fauna (id, name, avg_age, discovery_date) values (5, 'Malleefowl', 14942, '1784-08-25 18:30:45');
insert into fauna (id, name, avg_age, discovery_date) values (6, 'South American sea lion', 28928, '1808-04-09 04:40:18');
insert into fauna (id, name, avg_age, discovery_date) values (7, 'Cat, cape wild', 49438, '1853-04-30 19:33:26');
insert into fauna (id, name, avg_age, discovery_date) values (8, 'Puma, south american', 7314, '1763-11-27 18:59:28');
insert into fauna (id, name, avg_age, discovery_date) values (9, 'Sheep, american bighorn', 44038, '1894-03-21 12:08:52');
insert into fauna (id, name, avg_age, discovery_date) values (10, 'Swallow (unidentified)', 17191, '1857-09-16 11:21:10');
insert into fauna (id, name, avg_age, discovery_date) values (11, 'Common zorro', 5108, '1883-04-19 07:32:50');
insert into fauna (id, name, avg_age, discovery_date) values (12, 'Crested bunting', 44888, '1737-03-20 11:43:59');
insert into fauna (id, name, avg_age, discovery_date) values (13, 'South African hedgehog', 23894, '1718-01-19 16:13:38');
insert into fauna (id, name, avg_age, discovery_date) values (14, 'Dragon, western bearded', 16610, '1873-10-10 20:26:22');
insert into fauna (id, name, avg_age, discovery_date) values (15, 'Bulbul, black-eyed', 17479, '1771-07-24 03:08:10');
insert into fauna (id, name, avg_age, discovery_date) values (16, 'White-fronted bee-eater', 31359, '1814-08-10 08:12:40');
insert into fauna (id, name, avg_age, discovery_date) values (17, 'Slender loris', 26676, '1749-02-03 17:22:15');
insert into fauna (id, name, avg_age, discovery_date) values (18, 'Tern, white-winged', 49839, '1809-08-13 23:03:09');
insert into fauna (id, name, avg_age, discovery_date) values (19, 'Canadian tiger swallowtail butterfly', 32104, '1731-10-01 14:23:28');
insert into fauna (id, name, avg_age, discovery_date) values (20, 'Crow, house', 47846, '1843-03-08 04:45:43');23');

select * from fauna where name = 'fish';
select * from fauna where avg_age > 10000 and avg_age < 21000;
select * from fauna where discovery_date is null;
select * from fauna where discovery_date < '01.01.1950';