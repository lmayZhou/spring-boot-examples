package com.lmaye.cloud.example.drools;

import com.lmaye.cloud.core.utils.GsonUtils;
import com.lmaye.cloud.example.drools.entity.Order;
import com.lmaye.cloud.example.drools.entity.Person;
import org.junit.jupiter.api.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExampleDroolsApplicationTests {
    /**
     * Kie Session
     */
    @Autowired
    private KieSession kieSession;

    @Test
    void contextLoads() {
    }

    @Test
    void orderRuleTest() {
        // 指定KieSession方式, 需配置文件(kmodule.xml)
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        KieSession orderRuleKS = kc.newKieSession("orderRuleKS");
        Order order = Order.builder().originalPrice(123.00).build();
        orderRuleKS.insert(order);
        orderRuleKS.fireAllRules();
        orderRuleKS.dispose();
        System.out.println(GsonUtils.toJson(order));
    }

    @Test
    void personRuleTest() {
        // 无需配置文件(kmodule.xml)
        Person person = Person.builder().name("Tom").age(10).build();
        kieSession.insert(person);
        kieSession.fireAllRules();
        kieSession.dispose();
    }
}
