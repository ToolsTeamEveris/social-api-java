insert into Person (id, name, surname, username, password) values (1000, 'Ivan', 'Galan', 'ivan', 'ivan');
insert into Person (id, name, surname, username, password) values (1002, 'Irene', 'Guijarro', 'irene', 'irene');
insert into Person (id, name, surname, username, password) values (1003, 'Jose', 'Amores', 'jose', 'jose');

insert into Event (id, ending_date, name, starting_date, type, creator) values (1000, '2018-03-21', 'Beber agua','2018-03-22', 0, 1002);

insert into Post (id, creator, text, creation_date, lat, lng, reported) values (1000, 1002, 'aaaa', '2018-03-21', 38.4, -1, true);
insert into Post (id, creator, text, creation_date, lat, lng, reported) values (1001, 1002, 'eeee', '2018-03-21', 38.4, -2, false);
insert into Post (id, creator, text, creation_date, lat, lng, reported) values (1002, 1003, 'rrrr', '2018-03-21', 38.4, -0.5, false);

insert into reaccion (id, creator, creation_date, type, likes) values (1, 1003, '2018-03-12', 1, 1000);
insert into reaccion (id, creator, creation_date, type, likes) values (2, 1000, '2018-03-15', 2, null);

insert into grupo (id, creator, name) values (1000, 1000, 'Group1');
insert into grupo (id, creator, name) values (1001, 1003, 'Group2');
insert into grupo (id, creator, name) values (1002, 1000, 'Group3');
insert into grupo (id, creator, name) values (1003, 1002, 'Group4');




