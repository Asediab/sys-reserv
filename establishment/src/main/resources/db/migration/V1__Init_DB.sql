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
    fridayamend      timestamp      not null,
    fridayamstart    timestamp      not null,
    fridaypmend      timestamp      not null,
    fridaypmstart    timestamp      not null,
    mondayamend      timestamp      not null,
    mondayamstart    timestamp      not null,
    mondaypmend      timestamp      not null,
    mondaypmstart    timestamp      not null,
    saturdayamend    timestamp      not null,
    saturdayamstart  timestamp      not null,
    saturdaypmend    timestamp      not null,
    saturdaypmstart  timestamp      not null,
    sundayamend      timestamp      not null,
    sundayamstart    timestamp      not null,
    sundaypmend      timestamp      not null,
    sundaypmstart    timestamp      not null,
    thursdayamend    timestamp      not null,
    thursdayamstart  timestamp      not null,
    thursdaypmend    timestamp      not null,
    thursdaypmstart  timestamp      not null,
    tuesdayamend     timestamp      not null,
    tuesdayamstart   timestamp      not null,
    tuesdaypmend     timestamp      not null,
    tuesdaypmstart   timestamp      not null,
    wednesdayamend   timestamp      not null,
    wednesdayamstart timestamp      not null,
    wednesdaypmend   timestamp      not null,
    wednesdaypmstart timestamp      not null,
    primary key (id)
);

alter table etablishment
    add constraint UK_6fhxpb08387iji9cok7x6s355 unique (name);

alter table comment
    add constraint FKn9hdf18ucpcam3t34dsacxm8q foreign key (establishment_id) references etablishment on update cascade on delete cascade;

alter table etablishment
    add constraint FK8vxawbjbmu55fi3yxdej1ein3 foreign key (time_table_id) references time_table;
