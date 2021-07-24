package com.lmaye.spring.boot.webrtc.params;

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
@ToString(callSuper = true)
public class MessageParam implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    private String name;

    /**
     * 接收人
     */
    private String receiver;

    /**
     * 扩展
     */
    private String ext;
}