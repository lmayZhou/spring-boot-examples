package com.lmaye.cloud.example.drools.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * -- Order
 *
 * @author Lmay Zhou
 * @date 2021/10/21 15:58
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Data
@Builder
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 原价
     */
    private Double originalPrice;

    /**
     * 实价
     */
    private Double realPrice;
}
