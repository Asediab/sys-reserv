create table reservation
(
    id                   bigserial             not null,
    date_created         timestamp,
    end_of_reservation   timestamp             not null,
    establishment_id     int8                  not null,
    establishment_name   varchar(255)          not null,
    last_modified_date   timestamp,
    start_of_reservation timestamp             not null,
    user_first_name      varchar(255)          not null,
    user_id              int8                  not null,
    valid                boolean default false not null,
    validate_number      varchar(255)          not null,
    primary key (id)
);


alter table reservation
    add constraint UK_g5v0tl2y5vt8rpjrvfecwsxel unique (validate_number);
