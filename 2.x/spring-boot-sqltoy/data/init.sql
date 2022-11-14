/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
    id                   bigint(20) not null comment '主键ID',
    dept_id              bigint(20) comment '部门ID',
    client_id            varchar(256) comment '客户端ID',
    user_name            varchar(30) not null comment '用户名',
    password             varchar(100) not null comment '密码',
    user_type            varchar(2) not null default '01' comment '用户类型(00. 系统用户; 01. 注册用户;)',
    email                varchar(50) comment '用户邮箱',
    phone_number          varchar(11) comment '手机号',
    sex                  char(1) not null default '2' comment '性别(0. 男; 1. 女; 2. 未知;)',
    avatar               varchar(100) comment '头像路径',
    qq                   varchar(50) comment 'QQ',
    wechat               varchar(50) comment '微信',
    weibo                varchar(50) comment '微博',
    status               tinyint(1) not null default 0 comment '状态(0. 正常; 1. 停用; 2. 删除;)',
    create_at            datetime not null default CURRENT_TIMESTAMP comment '创建时间',
    create_by            varchar(30) not null default 0 comment '创建人',
    update_at            datetime comment '更新时间',
    update_by            varchar(30) comment '更新人',
    remark               varchar(500) comment '备注',
    ext                  varchar(500) comment '拓展字段',
    version              int(10) not null default 1 comment '版本号',
    primary key (id)
);

alter table sys_user comment '用户信息';