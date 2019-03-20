package com.example.demo.auth;

import com.example.demo.dao.UserMapper;
import com.example.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
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


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.getUserByUsername(username);
        if (null == user){
            throw new UsernameNotFoundException("用户名不存在");
        }
        log.info("username:"+user.getUsername()+"  password:"+user.getPassword());

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (Role role:user.getRoles()) {
//            authorities.add(new SimpleGrantedAuthority(role.getName()));
//        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);

    }
}
