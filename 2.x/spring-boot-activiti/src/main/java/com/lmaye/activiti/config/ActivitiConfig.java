package com.lmaye.activiti.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * -- Activiti Config
 *
 * @author lmay.Zhou
 * @date 2021/6/10 12:23
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
@Configuration
public class ActivitiConfig {
    /**
     * User Details Service
     *
     * @return UserDetailsService
     */
    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }
}
