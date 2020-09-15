create table comment
(
    id                 bigserial    not null,
    author             varchar(255) not null,
    date_created       timestamp,
    last_modified_date timestamp,
    text               varchar(500),
    user_id            int8,
    establishment_id   int8         not null,
    primary key (id)
);

create table etablishment
(
    id                    bigserial     not null,
    address               varchar(500)  not null,
    clients_limit         int4          not null,
    date_created          timestamp,
    description           varchar(2000) not null,
    last_modified_date    timestamp,
    name                  varchar(255)  not null,
    picture               varchar(255)  not null,
    type_of_establishment varchar(255),
    time_table_id         int8,
    primary key (id)
);

create table time_table
(
    id               bigserial not null,
    fridayamend      time      not null,
    fridayamstart    time      not null,
    fridaypmend      time      not null,
    fridaypmstart    time      not null,
    mondayamend      time      not null,
    mondayamstart    time      not null,
    mondaypmend      time      not null,
    mondaypmstart    time      not null,
    saturdayamend    time      not null,
    saturdayamstart  time      not null,
    saturdaypmend    time      not null,
    saturdaypmstart  time      not null,
    sundayamend      time      not null,
    sundayamstart    time      not null,
    sundaypmend      time      not null,
    sundaypmstart    time      not null,
    thursdayamend    time      not null,
    thursdayamstart  time      not null,
    thursdaypmend    time      not null,
    thursdaypmstart  time      not null,
    tuesdayamend     time      not null,
    tuesdayamstart   time      not null,
    tuesdaypmend     time      not null,
    tuesdaypmstart   time      not null,
    wednesdayamend   time      not null,
    wednesdayamstart time      not null,
    wednesdaypmend   time      not null,
    wednesdaypmstart time      not null,
    primary key (id)
);

alter table etablishment
    add constraint UK_6fhxpb08387iji9cok7x6s355 unique (name);

alter table comment
    add constraint FKn9hdf18ucpcam3t34dsacxm8q foreign key (establishment_id) references etablishment on update cascade on delete cascade;

alter table etablishment
    add constraint FK8vxawbjbmu55fi3yxdej1ein3 foreign key (time_table_id) references time_table;
