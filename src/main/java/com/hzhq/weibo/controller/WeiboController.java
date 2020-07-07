package com.hzhq.weibo.controller;

import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.entity.Weibo;
import com.hzhq.weibo.service.WeiboService;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import com.sun.istack.NotNull;
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
    Result getAllWeibo(@RequestParam("userId") @NotNull Integer userId,
                        @RequestParam("currentPage") @NotNull Integer currentPage,
                       @RequestParam("pageSize") @NotNull Integer pageSize) throws Exception {
        if (currentPage <= 0  || pageSize <= 0){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getAllWeibo(userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/getMyWeibo",method = RequestMethod.GET)
    Result getMyWeibo(@RequestParam("userId") @NotNull Integer userId,
                        @RequestParam("currentPage") @NotNull Integer currentPage,
                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage == 0  || pageSize == 0 ){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getAllWeiboByUser(userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/getLikeWeibo",method = RequestMethod.GET)
    Result getLikeWeibo(@RequestParam("userId") @NotNull Integer userId,
                        @RequestParam("currentPage") @NotNull Integer currentPage,
                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage == 0  || pageSize == 0 ){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getAllLikedWeiboByUser(userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/sendWeibo",method = RequestMethod.POST)
    Result sendWeibo(@RequestParam("userId") @NotNull Integer userId,
                     @RequestParam("content") @NotNull String content,
                     @RequestParam(value = "tag",required = false) Integer tag,
                     @RequestParam(value = "source",required = false) Integer source){
        User user = new User();
        user.setId(userId);
        Weibo weibo = new Weibo();
        weibo.setUser(user);
        weibo.setContent(content);
        if (tag != null || source != null){
            weibo.setTag(tag);
            weibo.setSource(source);
        }
        weiboService.sendWeibo(weibo);
        return Result.success("成功发布微博");

    }
}
