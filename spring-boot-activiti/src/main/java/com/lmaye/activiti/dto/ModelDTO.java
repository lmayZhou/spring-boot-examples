package com.lmaye.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * -- 流程模型
 *
 * @author lmay.Zhou
 * @date 2021/6/15 11:32
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@ApiModel(value = "ModelDTO", description = "流程模型")
public class ModelDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @NotBlank
    @ApiModelProperty(value = "模型名称", required = true)
    private String name;

    /**
     * 模型描述
     */
    @NotBlank
    @ApiModelProperty(value = "模型描述", required = true)
    private String description;
}
