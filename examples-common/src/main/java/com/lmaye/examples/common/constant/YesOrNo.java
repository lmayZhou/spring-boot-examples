package com.lmaye.examples.common.constant;

import lombok.Getter;

import java.util.Objects;

/**
 * -- 枚举对象
 *
 * @author lmay.Zhou
 * @date 2018/12/5 15:25 星期三
 * @qq 379839355
 * @email lmay@lmaye.com
 */
@Getter
public enum YesOrNo {
    /**
     * 枚举
     */
    NO(0, "否"),
    YES(1, "是");

    /**
     * 枚举值
     */
    private Integer value;

    /**
     * 枚举描述
     */
    private String desc;

    YesOrNo(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    /**
     * 获取枚举对象
     *
     * @param value 具体值
     * @return 枚举对象
     */
    public static YesOrNo valueOf(Integer value) {
        Objects.requireNonNull(value, "The matching value cannot be empty");
        for (YesOrNo object : values()) {
            if (value.equals(object.getValue())) {
                return object;
            }
        }
        throw new IllegalArgumentException("No matching constant for [" + value + "]");
    }
}
