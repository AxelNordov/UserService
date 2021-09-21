create table user_schema.user
(
    id         varchar(255) not null
        primary key,
    email      varchar(255) null,
    first_name varchar(255) null,
    last_name  varchar(255) null
);

create table user_schema.user_group
(
    id   varchar(255) not null
        primary key,
    name varchar(255) null
);

create table user_schema.user_user_groups
(
    user_id        varchar(255) not null,
    user_groups_id varchar(255) not null,
    constraint UK_daxbxlub4igv58juiafo2tt5a
        unique (user_groups_id),
    constraint FKcyk8vtwqqmtm3xv1onm1r81ke
        foreign key (user_groups_id) references user_schema.user_group (id),
    constraint FKddywflvu1fntus1ch9v9opcew
        foreign key (user_id) references user_schema.user (id)
);

