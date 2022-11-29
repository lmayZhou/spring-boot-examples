package com.lmaye.sqltoy.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.sagacity.sqltoy.config.annotation.Column;
import org.sagacity.sqltoy.config.annotation.Entity;
import org.sagacity.sqltoy.config.annotation.Id;

/**
 * -- SysUser
 *
 * @author lmayZhou
 * @version 1.0.0
 * @project sqltoy
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Entity(tableName = "sys_user", comment = "用户信息", pk_constraint = "PRIMARY")
public class SysUser extends BaseEntity {
    private static final long serialVersionUID = 1379979882159336297L;

    /**
     * 主键ID
     */
    @Id(strategy = "generator", generator = "org.sagacity.sqltoy.plugins.id.impl.DefaultIdGenerator")
    @Column(name = "id", comment = "主键ID", length = 19L, type = java.sql.Types.BIGINT, nullable = false)
    private Long id;

    /**
     * 部门ID
     */
    @Column(name = "dept_id", comment = "部门ID", length = 19L, type = java.sql.Types.BIGINT, nullable = true)
    private Long deptId;

    /**
     * 客户端ID
     */
    @Column(name = "client_id", comment = "客户端ID", length = 256L, type = java.sql.Types.VARCHAR, nullable = true)
    private String clientId;

    /**
     * 用户名
     */
    @Column(name = "user_name", comment = "用户名", length = 30L, type = java.sql.Types.VARCHAR, nullable = false)
    private String userName;

    /**
     * 密码
     */
    @Column(name = "password", comment = "密码", length = 100L, type = java.sql.Types.VARCHAR, nullable = false)
    private String password;

    /**
     * 用户类型(00. 系统用户; 01. 注册用户;)
     */
    @Column(name = "user_type", comment = "用户类型(00. 系统用户; 01. 注册用户;)", length = 2L, defaultValue = "1", type = java.sql.Types.VARCHAR, nullable = false)
    private String userType;

    /**
     * 用户邮箱
     */
    @Column(name = "email", comment = "用户邮箱", length = 50L, type = java.sql.Types.VARCHAR, nullable = true)
    private String email;

    /**
     * 手机号
     */
    @Column(name = "phone_number", comment = "手机号", length = 11L, type = java.sql.Types.VARCHAR, nullable = true)
    private String phoneNumber;

    /**
     * 性别(0. 男; 1. 女; 2. 未知;)
     */
    @Column(name = "sex", comment = "性别(0. 男; 1. 女; 2. 未知;)", length = 1L, defaultValue = "2", type = java.sql.Types.CHAR, nullable = false)
    private String sex;

    /**
     * 头像路径
     */
    @Column(name = "avatar", comment = "头像路径", length = 100L, type = java.sql.Types.VARCHAR, nullable = true)
    private String avatar;

    /**
     * QQ
     */
    @Column(name = "qq", comment = "QQ", length = 50L, type = java.sql.Types.VARCHAR, nullable = true)
    private String qq;

    /**
     * 微信
     */
    @Column(name = "wechat", comment = "微信", length = 50L, type = java.sql.Types.VARCHAR, nullable = true)
    private String wechat;

    /**
     * 微博
     */
    @Column(name = "weibo", comment = "微博", length = 50L, type = java.sql.Types.VARCHAR, nullable = true)
    private String weibo;
}
