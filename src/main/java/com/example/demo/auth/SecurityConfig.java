package com.example.demo.auth;

import com.example.demo.auth.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.security.SpringSessionBackedSessionRegistry;

import javax.annotation.Resource;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: SpringSecurity 配置类
 * @date:2019-3-13
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private FindByIndexNameSessionRepository<Session> sessionRepository;
    @Resource
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Resource
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;


    @Bean
    CustomUserDetailsService customUserService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public SpringSessionBackedSessionRegistry sessionRegistry(){
        return new SpringSessionBackedSessionRegistry(sessionRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

    /**
     * 具体的权限控制规则配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                //设置允许匿名登录路径
                    .antMatchers("/").permitAll()
                    .anyRequest().authenticated()
                .and()
                // 登录相关设置
                .formLogin()
                    .loginPage("/page/index")
                    .successHandler(customAuthenticationSuccessHandler)
                    .failureHandler(customAuthenticationFailureHandler)
                    .permitAll()
                .and()
                //session管理，注入redis
                .sessionManagement()
                    .invalidSessionUrl("/page/invalid")
                    .maximumSessions(1)
                    .sessionRegistry(sessionRegistry())
                    // 当达到最大值时，是否保留已经登录的用户
                    .maxSessionsPreventsLogin(false)
                    // 当达到最大值时，旧用户被踢出后的操作
                    .expiredSessionStrategy(new CustomExpiredSessionStrategy());

                http.csrf().disable();
    }









}
