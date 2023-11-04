/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
    id           bigint(20) not null comment '主键ID',
    dept_id      bigint(20) comment '部门ID',
    client_id    varchar(256) comment '客户端ID',
    user_name    varchar(30)  not null comment '用户名',
    password     varchar(100) not null comment '密码',
    user_type    varchar(2)   not null default '01' comment '用户类型(00. 系统用户; 01. 注册用户;)',
    email        varchar(50) comment '用户邮箱',
    phone_number varchar(11) comment '手机号',
    sex          char(1)      not null default '2' comment '性别(0. 男; 1. 女; 2. 未知;)',
    avatar       varchar(100) comment '头像路径',
    qq           varchar(50) comment 'QQ',
    wechat       varchar(50) comment '微信',
    weibo        varchar(50) comment '微博',
    status       tinyint(1) not null default 0 comment '状态(0. 正常; 1. 停用; 2. 删除;)',
    create_at    datetime     not null default CURRENT_TIMESTAMP comment '创建时间',
    create_by    varchar(30)  not null default 0 comment '创建人',
    update_at    datetime comment '更新时间',
    update_by    varchar(30) comment '更新人',
    remark       varchar(500) comment '备注',
    ext          varchar(500) comment '拓展字段',
    version      int(10) not null default 1 comment '版本号',
    primary key (id)
);

alter table sys_user comment '用户信息';

CREATE TABLE `user_role`
(
    `id`        bigint(20) NOT NULL COMMENT '主键ID',
    `user_id`   bigint(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
    `role_id`   bigint(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
    `status`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '状态(0. 正常; 1. 停用; 2. 删除;)',
    `create_at` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '创建人',
    `update_at` datetime                                                              DEFAULT NULL COMMENT '更新时间',
    `update_by` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci          DEFAULT NULL COMMENT '更新人',
    `remark`    varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '备注',
    `ext`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci         DEFAULT NULL COMMENT '拓展字段',
    `version`   int(10) NOT NULL DEFAULT '1' COMMENT '版本号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色';