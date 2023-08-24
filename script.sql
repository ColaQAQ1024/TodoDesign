create table if not exists todo_group
(
    group_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    group_name varchar(32) default '新建小组' not null,
    created_at timestamp   default (now())    not null
)
    auto_increment = 105;

create table if not exists todo_quest
(
    name_thing varchar(255)                         not null,
    start_time timestamp  default CURRENT_TIMESTAMP not null,
    finish     tinyint(1) default 0                 not null,
    star       tinyint(1)                           null,
    dead_time  datetime                             null,
    quest_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    group_id   int                                  not null,
    constraint todo_quest_todo_group_group_id_fk
        foreign key (group_id) references todo_group (group_id)
)
    auto_increment = 45;

create table if not exists todo_roles
(
    role_id   int auto_increment
        constraint `PRIMARY`
        primary key,
    role_name varchar(32)  not null,
    introduce varchar(255) null,
    constraint todo_role_pk
        unique (role_name)
)
    auto_increment = 3;

create table if not exists todo_user
(
    user_id    int auto_increment
        constraint `PRIMARY`
        primary key,
    username   varchar(32)                         not null,
    password   varchar(255)                        not null,
    created_at timestamp default CURRENT_TIMESTAMP null,
    nickname   varchar(32)                         not null,
    constraint todo_user_pk
        unique (username)
)
    auto_increment = 52;

create table if not exists table_check
(
    check_id     int auto_increment
        constraint `PRIMARY`
        primary key,
    user_id      int                       not null,
    check_status int                       not null,
    create_time  timestamp default (now()) not null,
    amount       float                     not null,
    money_type   varchar(255)              not null,
    constraint table_check_todo_user_user_id_fk
        foreign key (user_id) references todo_user (user_id)
);

create table if not exists todo_relation
(
    column_id int auto_increment
        constraint `PRIMARY`
        primary key,
    role_id   int not null,
    user_id   int not null,
    constraint `authority relation_todo_roles_column_id_fk`
        foreign key (role_id) references todo_roles (role_id),
    constraint `authority relation_todo_user_user_id_fk`
        foreign key (user_id) references todo_user (user_id)
)
    auto_increment = 3;

create table if not exists todo_teammate
(
    group_id  int not null,
    user_id   int not null,
    column_id int auto_increment
        constraint `PRIMARY`
        primary key,
    constraint todo_teammate_todo_group_group_id_fk
        foreign key (group_id) references todo_group (group_id),
    constraint todo_teammate_todo_user_user_id_fk
        foreign key (user_id) references todo_user (user_id)
)
    auto_increment = 4;


