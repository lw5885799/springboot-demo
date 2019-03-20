package com.example.demo.web;

import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/error")
    public String getErrorPage(){
        return "error";
    }

    @GetMapping("/myHome")
    public String getMyHomePage(){
        return "myHome";
    }

    @GetMapping("/login")
    public String login(){
        System.out.println("a");
        return "myHome";
    }
    @GetMapping("/user")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
    public String userTest(){
        return "如果你看见这句话，说明你有ROLE_USER角色";
    }

}
