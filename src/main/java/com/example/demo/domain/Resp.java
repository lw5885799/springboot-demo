package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author: create by LiWeichen
 * @version: v1.0
 * @description: response 返回信息封装
 * @date:2019-3-20
 */
@Data
@AllArgsConstructor
public class Resp {

    private String code;

    private String msg;

    public static Resp success(String msg){
        return new Resp("1",msg);
    }

    public static Resp error(String msg){
        return new Resp("0",msg);
    }
}
