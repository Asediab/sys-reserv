insert into public.time_table (id, fridayamend, fridayamstart, fridaypmend, fridaypmstart, mondayamend, mondayamstart,
                               mondaypmend, mondaypmstart, saturdayamend, saturdayamstart, saturdaypmend,
                               saturdaypmstart, sundayamend, sundayamstart, sundaypmend, sundaypmstart, thursdayamend,
                               thursdayamstart, thursdaypmend, thursdaypmstart, tuesdayamend, tuesdayamstart,
                               tuesdaypmend, tuesdaypmstart, wednesdayamend, wednesdayamstart, wednesdaypmend,
                               wednesdaypmstart)
values (1, '12:00:00', '08:00:00', '20:00:00', '14:00:00', '12:00:00', '08:00:00', '20:00:00', '14:00:00', '12:00:00',
        '08:00:00', '18:00:00', '14:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '12:00:00', '08:00:00',
        '20:00:00', '14:00:00', '12:00:00', '08:00:00', '20:00:00', '14:00:00', '12:00:00', '08:00:00', '20:00:00',
        '14:00:00');
insert into public.time_table (id, fridayamend, fridayamstart, fridaypmend, fridaypmstart, mondayamend, mondayamstart,
                               mondaypmend, mondaypmstart, saturdayamend, saturdayamstart, saturdaypmend,
                               saturdaypmstart, sundayamend, sundayamstart, sundaypmend, sundaypmstart, thursdayamend,
                               thursdayamstart, thursdaypmend, thursdaypmstart, tuesdayamend, tuesdayamstart,
                               tuesdaypmend, tuesdaypmstart, wednesdayamend, wednesdayamstart, wednesdaypmend,
                               wednesdaypmstart)
values (2, '12:00:00', '08:00:00', '20:00:00', '14:00:00', '12:00:00', '08:00:00', '20:00:00', '14:00:00', '12:00:00',
        '08:00:00', '18:00:00', '14:00:00', '00:00:00', '00:00:00', '00:00:00', '00:00:00', '12:00:00', '08:00:00',
        '20:00:00', '14:00:00', '12:00:00', '08:00:00', '20:00:00', '14:00:00', '12:00:00', '08:00:00', '20:00:00',
        '14:00:00');

SELECT pg_catalog.setval('time_table_id_seq', 3, true);


insert into public.etablishment (id, address, clients_limit, date_created, description, last_modified_date, name,
                                 picture, type_of_establishment, time_table_id)
values (1, 'Forum des Halles 75001 PARIS ', 5, '2020-09-14 15:51:12.000000', 'Pool', null, 'Piscine Suzanne Berlioux (Les Halles)', 'pool.jpg',
        'POOL', 1);
insert into public.etablishment (id, address, clients_limit, date_created, description, last_modified_date, name,
                                 picture, type_of_establishment, time_table_id)
values (2, 'quai François Mauriac - Quai de la Gare 75013 PARIS ', 5, '2020-09-14 15:54:34.000000', 'Pool', null, 'Piscine Joséphine-Baker', 'pool.jpg', 'POOL', 2);

SELECT pg_catalog.setval('etablishment_id_seq', 3, true);



insert into public.comment (id, author, date_created, last_modified_date, text, user_id, establishment_id)
values (1, 'User', '2020-09-14 15:55:53.000000', null, 'Super!', 1, 1);
insert into public.comment (id, author, date_created, last_modified_date, text, user_id, establishment_id)
values (2, 'User', '2020-09-14 15:55:54.000000', null, 'Parfait!!', 1, 1);
insert into public.comment (id, author, date_created, last_modified_date, text, user_id, establishment_id)
values (3, 'User', '2020-09-14 15:55:55.000000', null, 'Nul!!', 1, 2);


SELECT pg_catalog.setval('comment_id_seq', 4, true);
