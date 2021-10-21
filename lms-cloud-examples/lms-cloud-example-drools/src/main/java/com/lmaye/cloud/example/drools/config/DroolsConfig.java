package com.lmaye.cloud.example.drools.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;

/**
 * -- Drools Config
 *
 * @author Lmay Zhou
 * @date 2021/10/21 16:53
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Configuration
public class DroolsConfig {
    /**
     * 规则文件目录
     */
    private static final String RULES_PATH = "rules/";

    /**
     * Kie Services
     */
    private final KieServices kieServices = KieServices.Factory.get();

    @Bean
    KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }

    @Bean
    KieSession kieSession() throws IOException {
        return kieContainer().newKieSession();
    }

    @Bean
    KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        for (Resource file : getRuleFiles()) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(),
                    "UTF-8"));
        }
        return kieFileSystem;
    }

    @Bean
    KieContainer kieContainer() throws IOException {
        KieRepository kieRepository = kieServices.getRepository();
        kieRepository.addKieModule(kieRepository::getDefaultReleaseId);
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }

    private Resource[] getRuleFiles() throws IOException {
        return new PathMatchingResourcePatternResolver().getResources("classpath*:" + RULES_PATH + "**/*.*");
    }
}
