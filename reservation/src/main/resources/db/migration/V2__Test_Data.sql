insert into public.reservation (id, date_created, end_of_reservation, establishment_id, establishment_name,
                                last_modified_date, start_of_reservation, user_first_name, user_id, valid,
                                validate_number)
values (1, '2020-09-14 10:00:00.000000', '2020-09-14 15:00:00.000000', 1, 'Pool1', null, '2020-09-14 11:00:00.000000',
        'User', 1, 'false', 'f6D8r7');
insert into public.reservation (id, date_created, end_of_reservation, establishment_id, establishment_name,
                                last_modified_date, start_of_reservation, user_first_name, user_id, valid,
                                validate_number)
values (2, '2020-09-15 11:00:00.000000', '2020-09-14 15:00:00.000000', 1, 'Pool1', null, '2020-09-15 11:00:00.000000',
        'User', 1, 'false', 'H45ns2');

SELECT pg_catalog.setval('reservation_id_seq', 2, true);
