package com.lmaye.activiti.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

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
@ApiModel(value = "TaskQueryDTO", description = "任务信息")
public class TaskQueryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 任务名称
     */
    @ApiModelProperty("任务名称")
    private String taskName;

    /**
     * 任务代理人
     */
    @ApiModelProperty("任务代理人")
    private String assignee;

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
