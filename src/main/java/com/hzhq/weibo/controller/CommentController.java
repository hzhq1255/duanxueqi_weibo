package com.hzhq.weibo.controller;

import com.hzhq.weibo.entity.Comment;
import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.repository.CommentRepository;
import com.hzhq.weibo.service.CommentService;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/7 23:27
 * @desc:
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {

    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/getAllCommentByWeiboId",method = {RequestMethod.GET})
    public Result getAllCommentByWeiboId(@RequestParam("weiboId") @NotNull Integer weiboId,
                                         @RequestParam("currentPage") @NotNull  Integer currentPage,
                                         @RequestParam("pageSize") @NotNull  Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0){
            return Result.error("非法参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return commentService.getAllCommentByWeiboId(weiboId, PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/getAllCommentByUserId", method = {RequestMethod.GET})
    public Result getAllCommentByUserId(@RequestParam("userId") @NotNull Integer userId,
                                        @RequestParam("currentPage") @NotNull  Integer currentPage,
                                        @RequestParam("pageSize") @NotNull  Integer pageSize){
        if (currentPage <= 0 || pageSize <= 0){
            return Result.error("非法参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return commentService.getAllCommentByUserId(userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/sendComment", method = {RequestMethod.POST})
    public Result sendComment(@RequestParam("weiboId") @NotNull Integer weiboId,
                              @RequestParam("userId") @NotNull Integer userId,
                              @RequestParam("content") @NotNull String content){
        Comment comment = new Comment();
        comment.setId(null);
        User user = new User();
        user.setId(userId);
        comment.setUser(user);
        comment.setWeiboId(weiboId);
        comment.setContent(content);
        comment.setSendTime(new Date());
        return commentService.sendComment(comment);
    }

    @RequestMapping(value = "/delComment", method = {RequestMethod.POST})
    public Result deleteCommentById(@RequestParam("commentId") @NotNull Integer commentId){
        return commentService.deleteCommentById(commentId);
    }
}
