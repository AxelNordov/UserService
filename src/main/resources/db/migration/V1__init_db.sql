create table user_schema.user
(
    uuid       varchar(255) not null
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table user_schema.user_group
(
    uuid varchar(255) not null
        primary key,
    name varchar(255) null
);

create table user_schema.user_user_groups
(
    user_uuid        varchar(255) not null,
    user_groups_uuid varchar(255) not null,
    primary key (user_uuid, user_groups_uuid),
    constraint UK_2sv00ert6liqf17a6y6e9ffd0
        unique (user_groups_uuid),
    constraint FKdhf56j1xo789byelt7gooctpu
        foreign key (user_uuid) references user_schema.user (uuid),
    constraint FKwa4h07r29qqjslumsipgahdw
        foreign key (user_groups_uuid) references user_schema.user_group (uuid)
);

