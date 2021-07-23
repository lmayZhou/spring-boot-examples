package com.lmaye.activiti.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * -- 流程实例
 *
 * @author lmay.Zhou
 * @date 2021/6/15 14:55
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ProcessVO", description = "流程实例")
public class ProcessVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 流程ID
     */
    @ApiModelProperty("流程ID")
    private String processId;

    /**
     * 启动用户
     */
    @ApiModelProperty("启动用户")
    private String startUser;

    /**
     * 开始时间
     */
    @ApiModelProperty("开始时间")
    private Date startTime;

    /**
     * 流程定义Key
     */
    @ApiModelProperty("流程定义Key")
    private String processDefinitionKey;
}
