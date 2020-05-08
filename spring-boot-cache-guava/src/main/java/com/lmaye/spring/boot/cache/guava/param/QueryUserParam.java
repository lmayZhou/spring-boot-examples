package com.lmaye.spring.boot.cache.guava.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.properties.IntegerProperty;
import io.swagger.models.properties.LongProperty;
import io.swagger.models.properties.StringProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * -- 查询用户 Param
 * - 请求参数
 *
 * @author lmay.Zhou
 * @date 2020/5/8 19:00 星期五
 * @email lmay_zlm@meten.com
 */
@Data
@ToString(callSuper = true)
@ApiModel(value = "QueryUserParam", description = "查询用户 - 请求参数")
public class QueryUserParam implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
//    @NotNull
//    @ApiModelProperty(value = "主键ID", required = true, dataType = IntegerProperty.TYPE, example = "10000")
    @ApiModelProperty(value = "主键ID", dataType = IntegerProperty.TYPE, example = "10000")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", dataType = StringProperty.TYPE)
    private String name;

    /**
     * 邮件
     */
    @ApiModelProperty(value = "邮件", dataType = StringProperty.TYPE)
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号", dataType = LongProperty.TYPE)
    private Long mobile;
}
