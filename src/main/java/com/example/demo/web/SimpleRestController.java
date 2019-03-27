package com.example.demo.web;

import com.example.demo.domain.Resp;
import com.example.demo.domain.User;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: 简单restController
 * @date:2019-3-13
 */
@RestController
@RequestMapping("/rest")
@Slf4j
public class SimpleRestController {

    @Resource
    private UserService userService;

    /**
     * 第一种风格mapping写法
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTest1/{id}", method = RequestMethod.GET)
    public Resp testGet(@PathVariable Integer id){
        System.out.println("读取id为：" + id);
        this.userService.printTest();
        return Resp.success("获取成功");
    }

    /**
     * 第二种风格mapping写法
     * @param id
     * @param name
     * @return
     */
    @GetMapping("/getTest2/{id}/{name}")
    public String getUserInfo(@PathVariable(value = "id") Integer id,
                              @PathVariable(value = "name") String name){

        if ( null == id || null == name){
            return "error";
        }
        System.out.println("读取id, name为: " + id + "," + name);
        return "success";
    }

    @PostMapping("/postTest")
    public String postTest(User user){
        System.out.println("读取id, name为: " + user.getUsername() + "," + user.getPassword());
        return "success";
    }


}
