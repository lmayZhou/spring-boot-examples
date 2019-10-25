package com.lmaye.spring.boot.webrtc.params;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.properties.StringProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * -- 消息实体
 *
 * @author lmay.Zhou
 * @date 2019/10/25 15:53 星期五
 * @email lmay_zlm@meten.com
 */
@Data
@ApiModel("消息实体")
@ToString(callSuper = true)
public class MessageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", required = true, dataType = StringProperty.TYPE, example = "lmay")
    private String name;

    /**
     * 接收人
     */
    @ApiModelProperty(value = "接收人", required = true, dataType = StringProperty.TYPE, example = "lmay")
    private String receiver;

    /**
     * 扩展
     */
    @ApiModelProperty(value = "扩展", required = true, dataType = StringProperty.TYPE, example = "ext")
    private String ext;
}