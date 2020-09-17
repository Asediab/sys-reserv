insert into public.usr (id, active, email, establishment_id, first_name, last_name, password, roles)
values (1, 'true', 'user@user.com', -1, 'User', 'User',
        '$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q', 'USER');
insert into public.usr (id, active, email, establishment_id, first_name, last_name, password, roles)
values (2, 'true', 'admin@admin.com', 0, 'ADMIN', 'ADMIN',
        '$2a$10$a3w89n.b/aRcUYPwdPlO8.89WhanqcUYmDssnr0BgIQ98cK9bxN0q', 'ADMIN');


SELECT pg_catalog.setval('usr_id_seq', 3, true);



-- insert into public.user_role (user_id, roles)
-- values (1, 'USER');
-- insert into public.user_role (user_id, roles)
-- values (2, 'ADMIN');
