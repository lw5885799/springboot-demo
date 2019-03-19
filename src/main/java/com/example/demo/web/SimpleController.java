package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("/myHome")
    public String getMyHomePage(){
        return "myHome";
    }

    @PostMapping("/login")
    @ResponseBody
    public String login(User user){

        if (null == user.getPassword() || null == user.getPassword()){
            return "error";
        }

        try {
//            userService.simpleLogin(user);
        } catch (Exception e) {
            return "error";
        }
        return "success";
    }
}
