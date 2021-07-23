package com.lmaye.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * -- 任务信息
 *
 * @author lmay.Zhou
 * @date 2021/6/15 17:43
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "TaskTurnDTO", description = "任务信息")
public class TaskTurnDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @NotBlank
    @ApiModelProperty(value = "任务ID", required = true)
    private String taskId;

    /**
     * 用户ID
     */
    @NotBlank
    @ApiModelProperty(value = "用户ID", required = true)
    private String userId;
}
