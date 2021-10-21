package com.lmaye.cloud.example.drools.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * -- Person
 *
 * @author Lmay Zhou
 * @date 2021/10/21 18:08
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@Builder
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 描述
     */
    private String desc;
}
