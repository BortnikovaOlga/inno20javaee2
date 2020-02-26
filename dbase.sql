create table mobile
(
    id           bigserial not null,
    model        varchar(100),
    price        integer,
    manufacturer varchar(100)
);

alter table mobile
    owner to postgres;

create table users
(
    name     varchar(30),
    login    varchar(10)
        constraint users_pk
            unique,
    password varchar(10),
    tel      varchar(16),
    email    varchar(40)
);

alter table users
    owner to postgres;

