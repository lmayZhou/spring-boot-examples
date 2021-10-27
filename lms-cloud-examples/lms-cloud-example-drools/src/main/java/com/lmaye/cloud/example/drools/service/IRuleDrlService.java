package com.lmaye.cloud.example.drools.service;

import com.lmaye.cloud.example.drools.entity.Order;
import com.lmaye.cloud.example.drools.entity.RuleDrl;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author lmayZhou
 * @since 2021-10-25
 */
public interface IRuleDrlService extends IService<RuleDrl> {
    /**
     * 执行规则
     *
     * @param ruleId        规则ID
     * @param originalPrice 原价
     * @return Order
     */
    Order execOrder(Integer ruleId, Double originalPrice);
}
