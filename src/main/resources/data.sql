insert into member (email, password, name, activated) values ('admin@a.b', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 1);
insert into member (email, password, name, activated) values ('user@a.b', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 1);

insert into authority (authority_name) values ('ROLE_USER');
insert into authority (authority_name) values ('ROLE_ADMIN');

insert into member_authority (member_id, authority_name) values (1, 'ROLE_USER');
insert into member_authority (member_id, authority_name) values (1, 'ROLE_ADMIN');
insert into member_authority (member_id, authority_name) values (2, 'ROLE_USER');

insert into cheese (cheese_id, cheese_name) values (1, 'american');
insert into cheese (cheese_id, cheese_name) values (2, 'mozzarella');
insert into cheese (cheese_id, cheese_name) values (3, 'shred');

insert into sauce (sauce_id, sauce_name) values (1, 'bbq');
insert into sauce (sauce_id, sauce_name) values (2, 'chipotle');
insert into sauce (sauce_id, sauce_name) values (3, 'honeyMustard');
insert into sauce (sauce_id, sauce_name) values (4, 'horseradish');
insert into sauce (sauce_id, sauce_name) values (5, 'hotChilli');
insert into sauce (sauce_id, sauce_name) values (6, 'mayo');
insert into sauce (sauce_id, sauce_name) values (7, 'olive');
insert into sauce (sauce_id, sauce_name) values (8, 'onion');
insert into sauce (sauce_id, sauce_name) values (9, 'pepper');
insert into sauce (sauce_id, sauce_name) values (10, 'ranch');
insert into sauce (sauce_id, sauce_name) values (11, 'redWine');
insert into sauce (sauce_id, sauce_name) values (12, 'salt');
insert into sauce (sauce_id, sauce_name) values (13, 'sweetChilli');
insert into sauce (sauce_id, sauce_name) values (14, 'yellowMustard');

insert into vegetable (vegetable_id, vegetable_name) values (1, 'cucumber');
insert into vegetable (vegetable_id, vegetable_name) values (2, 'jalapeno');
insert into vegetable (vegetable_id, vegetable_name) values (3, 'lettuce');
insert into vegetable (vegetable_id, vegetable_name) values (4, 'olive');
insert into vegetable (vegetable_id, vegetable_name) values (5, 'onion');
insert into vegetable (vegetable_id, vegetable_name) values (6, 'pepper');
insert into vegetable (vegetable_id, vegetable_name) values (7, 'pickle');
insert into vegetable (vegetable_id, vegetable_name) values (8, 'tomato');


insert into recipe (bread, main_stuff) values ('wheat', 'egg');
insert into recipe_cheese (recipe_id, cheese_id) values (1, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (1, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (1, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (1, 1);
insert into recipe_vegetable (recipe_id, vegetable_id) values (1, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (1, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (1, 7);

insert into recipe (bread, main_stuff) values ('wheat', 'ham');
insert into recipe_cheese (recipe_id, cheese_id) values (2, 2);
insert into recipe_sauce (recipe_id, sauce_id) values (2, 2);
insert into recipe_sauce (recipe_id, sauce_id) values (2, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (2, 1);
insert into recipe_vegetable (recipe_id, vegetable_id) values (2, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (2, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (2, 8);

insert into recipe (bread, main_stuff) values ('oat', 'bmt');
insert into recipe_cheese (recipe_id, cheese_id) values (3, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (3, 2);
insert into recipe_sauce (recipe_id, sauce_id) values (3, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (3, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (3, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (3, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (3, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (3, 8);

insert into recipe (bread, main_stuff) values ('oat', 'blt');
insert into recipe_cheese (recipe_id, cheese_id) values (4, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (4, 2);
insert into recipe_sauce (recipe_id, sauce_id) values (4, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (4, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (4, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (4, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (4, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (4, 8);

insert into recipe (bread, main_stuff) values ('oat', 'bmt');
insert into recipe_cheese (recipe_id, cheese_id) values (5, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (5, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (5, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (5, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (5, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (5, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (5, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (5, 8);

insert into recipe (bread, main_stuff) values ('hearty', 'bmt');
insert into recipe_cheese (recipe_id, cheese_id) values (6, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (6, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (6, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (6, 1);
insert into recipe_vegetable (recipe_id, vegetable_id) values (6, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (6, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (6, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (6, 8);

insert into recipe (bread, main_stuff) values ('oat', 'club');
insert into recipe_cheese (recipe_id, cheese_id) values (7, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (7, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (7, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (7, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (7, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (7, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (7, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (7, 8);

insert into recipe (bread, main_stuff) values ('white', 'club');
insert into recipe_cheese (recipe_id, cheese_id) values (8, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (8, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (8, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (8, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (8, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (8, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (8, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (8, 7);

insert into recipe (bread, main_stuff) values ('white', 'rotisserie');
insert into recipe_cheese (recipe_id, cheese_id) values (9, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (9, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (9, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (9, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (9, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (9, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (9, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (9, 7);

insert into recipe (bread, main_stuff) values ('parmasan', 'rotisserie');
insert into recipe_cheese (recipe_id, cheese_id) values (10, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (10, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (10, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (10, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (10, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (10, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (10, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (10, 7);

insert into recipe (bread, main_stuff) values ('parmasan', 'chicken slice');
insert into recipe_cheese (recipe_id, cheese_id) values (11, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (11, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (11, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (11, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (11, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (11, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (11, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (11, 7);

insert into recipe (bread, main_stuff) values ('parmasan', 'teriyaki');
insert into recipe_cheese (recipe_id, cheese_id) values (12, 3);
insert into recipe_sauce (recipe_id, sauce_id) values (12, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (12, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (12, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (12, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (12, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (12, 7);
insert into recipe_vegetable (recipe_id, vegetable_id) values (12, 8);

insert into recipe (bread, main_stuff) values ('parmasan', 'steak');
insert into recipe_cheese (recipe_id, cheese_id) values (13, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (13, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (13, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (13, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (13, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (13, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (13, 7);
insert into recipe_vegetable (recipe_id, vegetable_id) values (13, 8);

insert into recipe (bread, main_stuff) values ('parmasan', 'shrimp');
insert into recipe_cheese (recipe_id, cheese_id) values (14, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (14, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (14, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (14, 2);
insert into recipe_vegetable (recipe_id, vegetable_id) values (14, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (14, 6);
insert into recipe_vegetable (recipe_id, vegetable_id) values (14, 7);
insert into recipe_vegetable (recipe_id, vegetable_id) values (14, 8);

insert into recipe (bread, main_stuff) values ('flat', 'steak');
insert into recipe_cheese (recipe_id, cheese_id) values (15, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (15, 1);
insert into recipe_sauce (recipe_id, sauce_id) values (15, 3);
insert into recipe_vegetable (recipe_id, vegetable_id) values (15, 1);
insert into recipe_vegetable (recipe_id, vegetable_id) values (15, 4);
insert into recipe_vegetable (recipe_id, vegetable_id) values (15, 5);
insert into recipe_vegetable (recipe_id, vegetable_id) values (15, 7);
insert into recipe_vegetable (recipe_id, vegetable_id) values (15, 8);

insert into save (member_id, recipe_id) values (1, 1);
insert into save (member_id, recipe_id) values (1, 2);
insert into save (member_id, recipe_id) values (1, 3);
insert into save (member_id, recipe_id) values (1, 4);
insert into save (member_id, recipe_id) values (1, 5);
insert into save (member_id, recipe_id) values (1, 6);
insert into save (member_id, recipe_id) values (1, 7);
insert into save (member_id, recipe_id) values (1, 8);
insert into save (member_id, recipe_id) values (1, 9);
insert into save (member_id, recipe_id) values (1, 10);
insert into save (member_id, recipe_id) values (1, 11);
insert into save (member_id, recipe_id) values (1, 12);
insert into save (member_id, recipe_id) values (1, 13);
insert into save (member_id, recipe_id) values (1, 14);
insert into save (member_id, recipe_id) values (1, 15);

insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 1, 5.0, "훌륭합니다.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 2, 4.0, "훌륭.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 3, 3.5, "괜찮다.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 4, 2.0, "흠...", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 5, 5.0, "훌륭합니다.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 6, 0.5, "개판.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 7, 5.0, "훌륭함.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 8, 2.5, "그럭저럭.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 9, 0.0, "아...", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 10, 5.0, "맛있음.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 11, 4.5, "좋다.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 12, 2.0, "아쉽다.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 13, 1.5, "싫어요.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 14, 5.0, "환상.", 0, '2024-03-05');
insert into review (member_id, recipe_id, score, comment, heart_counts, date) values (1, 15, 0.5, "상상하기 싫다.", 0, '2024-03-05');