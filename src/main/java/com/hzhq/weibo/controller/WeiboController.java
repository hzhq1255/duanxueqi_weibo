package com.hzhq.weibo.controller;

import com.hzhq.weibo.service.WeiboService;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 22:53
 * @desc:
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class WeiboController {

    @Autowired
    WeiboService weiboService;

    @RequestMapping(value = "/getAllWeibo",method = RequestMethod.GET)
    Result getAllWeibo(){
        return Result.success();
    }
}
