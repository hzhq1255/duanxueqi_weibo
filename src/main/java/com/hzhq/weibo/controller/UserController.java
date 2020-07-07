package com.hzhq.weibo.controller;

import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.service.UserService;
import com.hzhq.weibo.util.Result;
import com.sun.istack.internal.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 21:11
 * @desc:
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Result login(@RequestParam(value = "name") @NotNull String username,
                        @RequestParam(value = "pwd") @NotNull String password){
        if (username.isEmpty()  ){
            return Result.build(400,"用户名为空");
        }
        if (password.isEmpty() ){
            return Result.build(400,"密码为空");
        }
        Result result = userService.login(username,password);
        if (result.getCode().equals(Result.error().getCode())){
            return Result.error("用户名或密码错误");
        }
        return result;
    }

    @RequestMapping(value = "/reg",method = RequestMethod.POST)
    public Result reg(@RequestParam("name") @NotNull String name,
                      @RequestParam("pwd1") @NotNull String pwd1,
                      @RequestParam("pwd2") @NotNull String pwd2,
                      @RequestParam(value = "pic",required = false) @NotNull String pic,
                      @RequestParam(value = "gender",required = false) @NotNull String gender,
                      @RequestParam(value = "des",required = false) @NotNull String des,
                      @RequestParam(value = "birth",required = false) @NotNull Timestamp birth){
        if ("".equals(name) || "".equals(pwd1) || "".equals(pwd2)){
            return Result.error("关键字段名字,密码1，密码2为空");
        }
        if (!pwd1.equals(pwd2)){
            return Result.error("两次输入密码不一致");
        }
        Timestamp regTime = new Timestamp((new Date()).getTime());
        User user = new User(0,name,pwd1,pic,gender,des,regTime,birth);
        return userService.reg(user);
    }

    @RequestMapping(value = "/getUserInfo",method = {RequestMethod.GET})
    public Result getUserInfo(@RequestParam("userId") Integer userId){
        return userService.getUserInfo(userId);
    }

}
