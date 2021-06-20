package com.lmaye.activiti.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * -- 任务信息
 *
 * @author lmay.Zhou
 * @date 2021/6/15 17:25
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "TaskVO", description = "任务信息")
public class TaskVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @ApiModelProperty("任务ID")
    private String taskId;

    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String taskName;

    /**
     * 任务创建时间
     */
    @ApiModelProperty("任务创建时间")
    private Date taskCreateTime;

    /**
     * 任务代理人
     */
    @ApiModelProperty("任务代理人")
    private String taskAssignee;

    /**
     * 进程实例 ID
     */
    @ApiModelProperty("进程实例 ID")
    private String processInstanceId;

    /**
     * 执行 ID
     */
    @ApiModelProperty("执行 ID")
    private String executionId;

    /**
     * 流程定义 ID
     */
    @ApiModelProperty("流程定义 ID")
    private String processDefinitionId;
}
