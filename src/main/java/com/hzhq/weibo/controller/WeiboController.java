package com.hzhq.weibo.controller;

import com.hzhq.weibo.service.WeiboService;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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
    Result getAllWeibo(@RequestParam("currentPage") Integer currentPage,
                       @RequestParam("pageSize") Integer pageSize){
        if (currentPage == 0  || pageSize == 0 ){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getAllWeibo(PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/getLikeWeibo",method = RequestMethod.GET)
    Result getLikeWeibo(@RequestParam("userId") Integer userId,
                        @RequestParam("currentPage") Integer currentPage,
                        @RequestParam("pageSize") Integer pageSize){
        if (currentPage == 0  || pageSize == 0 ){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getLikeWeibo(userId,PageRequest.of(currentPage-1,pageSize));
    }

}
