package com.lmaye.sqltoy.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * -- SysUserVO
 *
 * @author lmayZhou
 * @version 1.0.0
 */
@Schema(name = "SysUserVO", description = "用户信息")
@Data
@Accessors(chain = true)
public class SysUserVO implements Serializable {
    private static final long serialVersionUID = 1379979882159336297L;

    /**
     * 主键ID
     */
    @Schema(name = "id", description = "主键ID")
    private Long id;

    /**
     * 部门ID
     */
    @Schema(name = "deptId", description = "部门ID")
    private Long deptId;

    /**
     * 客户端ID
     */
    @Schema(name = "clientId", description = "客户端ID")
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
    @Schema(name = "email", description = "用户邮箱")
    private String email;

    /**
     * 手机号
     */
    @Schema(name = "phoneNumber", description = "手机号")
    private String phoneNumber;

    /**
     * 性别(0. 男; 1. 女; 2. 未知;)
     */
    @Schema(name = "sex", description = "性别(0. 男; 1. 女; 2. 未知;)")
    private String sex;

    /**
     * 头像路径
     */
    @Schema(name = "avatar", description = "头像路径")
    private String avatar;

    /**
     * QQ
     */
    @Schema(name = "qq", description = "QQ")
    private String qq;

    /**
     * 微信
     */
    @Schema(name = "wechat", description = "微信")
    private String wechat;

    /**
     * 微博
     */
    @Schema(name = "weibo", description = "微博")
    private String weibo;

    /**
     * 状态(0. 正常; 1. 停用; 2. 删除;)
     */
    @Schema(name = "status", description = "状态(0. 正常; 1. 停用; 2. 删除;)")
    private Boolean status;

    /**
     * 创建时间
     */
    @Schema(name = "createAt", description = "创建时间")
    private LocalDateTime createAt;

    /**
     * 创建人
     */
    @Schema(name = "createBy", description = "创建人")
    private String createBy;
}