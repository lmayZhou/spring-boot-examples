package com.lmaye.activiti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * --
 *
 * @author Lmay Zhou
 * @date 2021/4/25 12:04
 * @email lmay@lmaye.com
 */
@Order(-1)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.csrf().disable();
    }
}