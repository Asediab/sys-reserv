create table user_role
(
    user_id int8 not null,
    roles   varchar(255)
);

create table usr
(
    id               bigserial    not null,
    active           boolean default true,
    email            varchar(255) not null,
    establishment_id int8 default -1,
    first_name       varchar(255) not null,
    last_name        varchar(255) not null,
    password         varchar(255) not null,
    primary key (id)
);

alter table usr
    add constraint UK_g9l96r670qkidthshajdtxrqf unique (email);

alter table user_role
    add constraint FKfpm8swft53ulq2hl11yplpr5 foreign key (user_id) references usr;
