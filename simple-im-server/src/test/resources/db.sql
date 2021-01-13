create table `user` (
    id bigint not null AUTO_INCREMENT,
    login_id varchar(64) not null,
    session_id varchar(64) not null,
    password varchar(64) not null,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    is_deleted tinyint not null default 0
);

create table `group` (
    id bigint not null AUTO_INCREMENT,
    name varchar(64) not null,
    creator_uid bigint not null,
    member_count int not null default 0,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    is_deleted tinyint not null default 0
);

create table `group_member_rel` (
    id bigint not null AUTO_INCREMENT,
    group_id bigint not null,
    uid bigint not null,
    create_time datetime not null default current_timestamp,
    update_time datetime not null default current_timestamp on update current_timestamp,
    is_deleted tinyint not null default 0
);