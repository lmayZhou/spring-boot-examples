package com.lmaye.spring.boot.cache.guava.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/**
 * -- 用户 Entity
 * - 响应数据
 *
 * @author lmay.Zhou
 * @date 2020/5/8 18:56 星期五
 * @email lmay_zlm@meten.com
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@ApiModel(value = "UserEntity", description = "用户信息 - 响应数据")
public class UserEntity implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String name;

    /**
     * 邮件
     */
    @ApiModelProperty("邮件")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty("手机号")
    private Long mobile;
}
