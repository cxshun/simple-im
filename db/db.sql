create table `user` (
    id bigint not null AUTO_INCREMENT comment '主键ID',
    login_id varchar(64) not null comment '登录id',
    password varchar(64) not null comment '密码',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    is_deleted tinyint not null default 0 comment '是否删除，1-是，0-否'
    primary key(id)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

create table `group` (
    id bigint not null AUTO_INCREMENT comment '群ID',
    name varchar(64) not null comment '群名称',
    member_count int not null default 0 comment '群人数',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '修改时间',
    is_deleted tinyint not null default 0 comment '是否删除，1-是，0-否'
    primary key(id)
) engine=InnoDB AUTO_INCREMENT=1 default charset=utf8mb4 COLLATE=utf8mb4_bin;

create table `group_member_rel` (
    id bigint not null AUTO_INCREMENT comment '主键ID',
    group_id bigint not null comment '群ID',
    uid bigint not null comment '用户ID',
    create_time datetime not null comment '创建时间',
    update_time datetime not null comment '创建时间',
    is_deleted tinyint not null default 0 comment '是否删除，1-是，0-否'
    primary key(id)
) engine=InnoDB AUTO_INCREMENT=1 default charset=utf8mb4 COLLATE=utf8mb4_bin;

