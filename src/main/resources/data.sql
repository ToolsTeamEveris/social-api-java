insert into Person (id, name, surname, username, password) values (1000, 'Ivan', 'Galan', 'ivan', 'ivan');
insert into Person (id, name, surname, username, password) values (1002, 'Irene', 'Guijarro', 'irene', 'irene');
insert into Person (id, name, surname, username, password) values (1003, 'Jose', 'Amores', 'jose', 'jose');

insert into Event (id, ending_date, name, starting_date, type, creator) values (1000, '2018-03-21', 'Beber agua','2018-03-22', 0, 1002);


insert into Post (id, creator, text, creation_date ) values (1000, 1002, 'aaaa', '2018-03-21');
insert into Post (id, creator, text, creation_date ) values (1001, 1002, 'eeee', '2018-03-21');
insert into Post (id, creator, text, creation_date ) values (1002, 1003, 'rrrr', '2018-03-21');

