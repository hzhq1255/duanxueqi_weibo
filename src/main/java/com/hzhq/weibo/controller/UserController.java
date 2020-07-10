package com.hzhq.weibo.controller;

import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.service.UserService;
import com.hzhq.weibo.util.Result;

import org.jetbrains.annotations.NotNull;
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
                      @RequestParam(value = "gender",required = false)  String gender,
                      @RequestParam(value = "pic",required = false)  String pic,
                      @RequestParam(value = "des",required = false)  String des,
                      @RequestParam(value = "birth",required = false)  String birth){
        if ("".equals(name) || "".equals(pwd1) || "".equals(pwd2)){
            return Result.error("关键字段名字,密码1，密码2为空");
        }
        if (!pwd1.equals(pwd2)){
            return Result.error("两次输入密码不一致");
        }
        if ("".equals(pic)){
            pic = null;
        }else if ("".equals(gender)){
            gender = null;
        }
        Timestamp regTime = new Timestamp((new Date()).getTime());
        String currentTime = String.valueOf(System.currentTimeMillis());
        int len = birth.length();
        int timeLen = currentTime.length();
        StringBuilder builder = new StringBuilder(birth);
        for (int i = len; i < timeLen;i++){
            builder.append(0);
        }
        Timestamp birthTime = new Timestamp(Long.parseLong(builder.toString()));
        System.out.println(birthTime);
        User user = new User(null,name,pwd1,pic,gender,des,regTime,birthTime);
        return userService.reg(user);
        //return null;
    }

    @RequestMapping(value = "/getUserInfo",method = {RequestMethod.GET})
    public Result getUserInfo(@RequestParam("userId") Integer userId){
        return userService.getUserInfo(userId);
    }

}
