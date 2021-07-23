package com.lmaye.activiti.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * -- User Details Service Impl
 *
 * @author lmay.Zhou
 * @date 2021/6/17 13:53
 * @qq 379839355
 * @email lmay@lmaye.com
 * @since JDK1.8
 */
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 获取用户
     * - 根据用户名
     *
     * @param username 用户名
     * @return UserDetails
     * @throws UsernameNotFoundException 异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new User(username, "123456", new ArrayList<SimpleGrantedAuthority>() {{
            add(new SimpleGrantedAuthority("ROLE_ACTIVITI_USER"));
            add(new SimpleGrantedAuthority("GROUP_activitiTeam"));
        }});
    }
}
