package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: com.example.demo.web
 * @date:2019-3-13
 */
@Controller
@RequestMapping("/page")
public class SimpleController {

    @Autowired
    private UserService userService;


    @GetMapping("/index")
    public String getIndexPage(){

        return "index";
    }

    @PostMapping("/login")
    public String login(User user){
        userService.simpleLogin(user);
        return "myHome";
    }
}
