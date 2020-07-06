package com.hzhq.weibo.service;

import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.repository.UserRepository;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:58
 * @desc:
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Result login(String name,String pwd){
        User user = userRepository.findUserByName(name);
        if (user.getPwd().equals(pwd)){
            return Result.success("登录成功");
        }
        return Result.error("登录失败");
    }

    public Result reg(User user){
        if (userRepository.findUserByName(user.getName()) != null){
            return Result.error("该用户名已存在");
        }
        try {
            userRepository.save(user);
        }catch (Exception e){
            System.out.println(e);
            return Result.error("注册失败");
        }
        return Result.success("注册成功");
    }

    public Result getUserInfo(Integer userId){
        User user = userRepository.findUserById(userId);
        if (user == null){
            return Result.error("查无此人");
        }
        return Result.success(user);
    }
}
