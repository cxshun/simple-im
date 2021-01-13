create table `user` (
    id bigint not null AUTO_INCREMENT comment 'user ID',
    login_id varchar(64) not null comment 'login id',
    session_id varchar(64) not null comment '',
    password varchar(64) not null comment 'password not encrypted',
    create_time datetime not null default current_timestamp comment 'created time',
    update_time datetime not null default current_timestamp on update current_timestamp comment 'modify time',
    is_deleted tinyint not null default 0 comment 'is delete or not，1-yes，0-no',
    primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `group` (
    id bigint not null AUTO_INCREMENT comment 'group ID',
    name varchar(64) not null comment 'group name',
    creator_uid bigint not null comment 'creator uid',
    member_count int not null default 0 comment 'member count of the current group',
    create_time datetime not null default current_timestamp comment 'current time',
    update_time datetime not null default current_timestamp on update current_timestamp comment 'modify time',
    is_deleted tinyint not null default 0 comment 'is delete or not，1-yes，0-no',
    primary key(id)
) engine=InnoDB AUTO_INCREMENT=1 default charset=utf8mb4 COLLATE=utf8mb4_bin;

create table `group_member_rel` (
    id bigint not null AUTO_INCREMENT comment 'primary ID',
    group_id bigint not null comment 'groupId',
    uid bigint not null comment 'userId ',
    create_time datetime not null default current_timestamp comment 'current time',
    update_time datetime not null default current_timestamp on update current_timestamp comment 'modify time',
    is_deleted tinyint not null default 0 comment 'is delete or not，1-yes，0-no',
    primary key(id),
    index idx_group_user(group_id, uid)
) engine=InnoDB AUTO_INCREMENT=1 default charset=utf8mb4 COLLATE=utf8mb4_bin;