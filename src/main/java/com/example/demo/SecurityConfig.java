package com.example.demo;

import com.example.demo.auth.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: SpringSecurity 配置类
 * @date:2019-3-13
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService(){
        return new CustomUserDetailsService();
    }


//    @Bean
//    public static NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }


    /**
     * 具体的权限控制规则配置
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/","/page/index","/page/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/page/index")
                .failureUrl("/page/index")
                .defaultSuccessUrl("/page/myHome")
                .permitAll()
                .and()
//                .rememberMe().rememberMeParameter("remember-me") //其实默认就是remember-me，这里可以指定更换
//                .and()
                .logout()
                .logoutSuccessUrl("/login?logout")  //退出登录
                .permitAll()
                .and()
                .csrf().disable();

    }

}
