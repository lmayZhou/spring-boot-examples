package com.lmaye.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * -- 流程模型
 *
 * @author lmay.Zhou
 * @date 2021/6/15 17:43
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ModelQueryDTO", description = "流程模型")
public class ModelQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @ApiModelProperty("模型名称")
    private String name;

    /**
     * 当前页码
     */
    @ApiModelProperty(value = "当前页码", example = "1", required = true)
    private Long pageIndex = 1L;

    /**
     * 每页显示页数
     */
    @ApiModelProperty(value = "每页显示页数", example = "10", required = true)
    private Long pageSize = 10L;
}
