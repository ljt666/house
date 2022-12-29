package com.ljt.house.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:测试用户登录
 * @Author：jintao.li
 * @Classname：loginControllerTest
 * @Date：2022/12/20 10:52
 */
@Controller
@RequestMapping("/logintest")
public class loginControllerTest{

    @RequestMapping("/login")
    public String login(){
        return "login";
    }


}
