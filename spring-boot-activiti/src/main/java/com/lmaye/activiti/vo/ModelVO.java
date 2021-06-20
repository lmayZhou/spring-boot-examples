package com.lmaye.activiti.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * -- 流程模型
 *
 * @author lmay.Zhou
 * @date 2021/6/16 10:12
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ModelVO", description = "流程模型")
public class ModelVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * Key
     */
    @ApiModelProperty("Key")
    private String key;

    /**
     * 名称
     */
    @ApiModelProperty("名称")
    private String name;

    /**
     * 元信息
     */
    @ApiModelProperty("元信息")
    private String metaInfo;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private Date createTime;
}
