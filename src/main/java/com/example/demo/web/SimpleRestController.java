package com.example.demo.web;

import com.example.demo.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


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


    /**
     * 第一种风格mapping写法
     * @param id
     * @return
     */
    @RequestMapping(value = "/getTest1/{id}", method = RequestMethod.GET)
    public String testGet(@PathVariable Integer id){
        System.out.println("读取id为：" + id);
        return "success";
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
