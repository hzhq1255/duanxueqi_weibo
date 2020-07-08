package com.hzhq.weibo.controller;

import com.hzhq.weibo.entity.Like;
import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.service.LikeService;
import com.hzhq.weibo.util.Result;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/7 21:58
 * @desc:
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class LikeController {

    @Autowired
    LikeService likeService;

    @RequestMapping(value = "/addLike",method = RequestMethod.POST)
    Result addLike(@RequestParam("userId") @NotNull Integer userId,
                @RequestParam(value = "weiboId",required = false) Integer weiboId,
                @RequestParam(value = "commentId",required = false) Integer commentId,
                @RequestParam(value = "replyId",required = false) Integer replyId){
        User user = new User();
        user.setId(userId);
        Like like = new Like();
        like.setUser(user);
        like.setWeiboId(weiboId);
        like.setCommentId(commentId);
        like.setReplyId(replyId);
        like.setStatus(1);
        return likeService.addLike(like);
    }

    @RequestMapping(value = "/cancelLike",method = RequestMethod.POST)
    Result cancelLike(@RequestParam("userId") @NotNull Integer userId,
                   @RequestParam(value = "weiboId",required = false) Integer weiboId,
                   @RequestParam(value = "commentId",required = false) Integer commentId,
                   @RequestParam(value = "replyId",required = false) Integer replyId) {
        Like like = new Like();
        User user = new User();
        user.setId(userId);
        like.setUser(user);
        like.setWeiboId(weiboId);
        like.setCommentId(commentId);
        like.setReplyId(replyId);
        return likeService.cancelLike(like);
    }
}
