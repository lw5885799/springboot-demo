package com.example.demo.auth;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import com.example.demo.domain.UserLoginEvent;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description:
 * @date:2019-3-19
 */
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RocketMQTemplate rocketMQTemplate;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.getUserByUsername(username);
        if (null == user){
            throw new UsernameNotFoundException("用户名不存在");
        }
        log.info("username:"+user.getUsername()+"  password:"+user.getPassword());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        try {
            //用户登录行为记录事件
            rocketMQTemplate.convertAndSend("user-topic-1",
                    UserLoginEvent.init(user));
        } catch (Exception e) {
            log.error(e.getMessage(), e.getStackTrace());
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);

    }
}
