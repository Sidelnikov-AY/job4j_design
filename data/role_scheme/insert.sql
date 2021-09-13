insert into role(name) values('admin');
insert into role(name) values('user');

insert into rules(name) values('test_rule');

insert into users(name, role_id) values('Anton', 5);
insert into users(name, role_id) values('Artem', 6);

insert into category(name) values('important');
insert into state(name) values('in work');

insert into item(name, users_id, category_id, state_id) values('test_item', 6, 2, 2);
insert into comments(name, item_id) values('test_comment', 5);
insert into attachs(name, item_id) values('test_file', 5);

insert into role_rules(role_id, rules_id) values(6, 1);