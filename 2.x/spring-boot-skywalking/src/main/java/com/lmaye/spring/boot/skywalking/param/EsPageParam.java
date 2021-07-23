package com.lmaye.spring.boot.skywalking.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.properties.IntegerProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * -- 分页参数
 *
 * @author lmay.Zhou
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since 2019/6/12 22:23 星期三
 */
@Data
@ApiModel("基础分页参数")
@ToString(callSuper = true)
public class EsPageParam implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    @ApiModelProperty(value = "页码", required = true, dataType = IntegerProperty.TYPE, example = "1")
    private Integer pageNumber = 1;

    /**
     * 页数
     */
    @ApiModelProperty(value = "页数", required = true, dataType = IntegerProperty.TYPE, example = "10")
    private Integer pageSize = 10;
}
