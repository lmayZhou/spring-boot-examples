package com.lmaye.cloud.example.rabbitmq.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

/**
 * -- 应用客户端ID
 *
 * @author lmay.Zhou
 * @date 2021/6/23 17:15
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Getter
@AllArgsConstructor
public enum AppId {
    /**
     * 枚举对象
     */
    T1(1000, "T1S1001", "test1-service"),
    T2(1001, "T2S1001", "test2-service");

    /**
     * 枚举编码
     */
    private final Integer code;

    /**
     * 应用ID
     */
    private final String appId;

    /**
     * 描述
     */
    private final String desc;

    /**
     * 获取枚举对象
     *
     * @param code 枚举编码
     * @return 枚举对象
     */
    public static AppId valueOf(Integer code) {
        Objects.requireNonNull(code, "The matching value cannot be empty");
        for (AppId obj : values()) {
            if (code.equals(obj.getCode())) {
                return obj;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + code + "]");
    }
}
