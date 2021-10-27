package com.lmaye.cloud.example.drools.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lmaye.cloud.example.drools.entity.Order;
import com.lmaye.cloud.example.drools.entity.RuleDrl;
import com.lmaye.cloud.example.drools.mapper.RuleDrlMapper;
import com.lmaye.cloud.example.drools.service.IRuleDrlService;
import org.kie.api.KieBase;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lmayZhou
 * @since 2021-10-25
 */
@Service
public class RuleDrlServiceImpl extends ServiceImpl<RuleDrlMapper, RuleDrl> implements IRuleDrlService {
    /**
     * 执行规则
     *
     * @param ruleId        规则ID
     * @param originalPrice 原价
     * @return Order
     */
    @Override
    public Order execOrder(Integer ruleId, Double originalPrice) {
        KieHelper kieHelper = new KieHelper();
        kieHelper.addContent(super.getById(ruleId).getRuleDrl(), ResourceType.DRL);
        KieBase kieBase = kieHelper.build();
        KieSession kieSession = kieBase.newKieSession();
        Order order = Order.builder().originalPrice(originalPrice).build();
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }
}
