package com.lmaye.sqltoy.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author lmayZhou
 * @version 1.0.0
 * @project sqltoy
 */
@Schema(name = "SysUserDTO", description = "用户信息")
@Data
@Accessors(chain = true)
public class SysUserDTO implements Serializable {
    private static final long serialVersionUID = 1379979882159336297L;

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID")
    private Long id;

    /**
     * 部门ID
     */
    @Schema(name = "deptId", description = "部门ID", nullable = true)
    private Long deptId;

    /**
     * 客户端ID
     */
    @Schema(name = "clientId", description = "客户端ID", nullable = true)
    private String clientId;

    /**
     * 用户名
     */
    @Schema(name = "userName", description = "用户名")
    private String userName;

    /**
     * 密码
     */
    @Schema(name = "password", description = "密码")
    private String password;

    /**
     * 用户类型(00. 系统用户; 01. 注册用户;)
     */
    @Schema(name = "userType", description = "用户类型(00. 系统用户; 01. 注册用户;)")
    private String userType;

    /**
     * 用户邮箱
     */
    @Schema(name = "email", description = "用户邮箱", nullable = true)
    private String email;

    /**
     * 手机号
     */
    @Schema(name = "phoneNumber", description = "手机号", nullable = true)
    private String phoneNumber;

    /**
     * 性别(0. 男; 1. 女; 2. 未知;)
     */
    @Schema(name = "sex", description = "性别(0. 男; 1. 女; 2. 未知;)")
    private String sex;

    /**
     * 头像路径
     */
    @Schema(name = "avatar", description = "头像路径", nullable = true)
    private String avatar;

    /**
     * QQ
     */
    @Schema(name = "qq", description = "QQ", nullable = true)
    private String qq;

    /**
     * 微信
     */
    @Schema(name = "wechat", description = "微信", nullable = true)
    private String wechat;

    /**
     * 微博
     */
    @Schema(name = "weibo", description = "微博", nullable = true)
    private String weibo;
}