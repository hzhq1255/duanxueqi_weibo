package com.hzhq.weibo.service;

import com.hzhq.weibo.dto.CommentDTO;
import com.hzhq.weibo.dto.UserCommentDTO;
import com.hzhq.weibo.dto.WeiboDTO;
import com.hzhq.weibo.entity.Comment;
import com.hzhq.weibo.entity.Weibo;
import com.hzhq.weibo.entity.WeiboInfo;
import com.hzhq.weibo.repository.CommentRepository;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:57
 * @desc:
 */
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public Result sendComment(Comment comment){
        try {
            commentRepository.save(comment);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("发送失败");
        }
        return Result.success("评论成功");
    }

    public Result getAllCommentByUserId(Integer userId, Pageable pageable){
        Page<Object[]> objectPage = commentRepository.selectAllCommentByUserId(userId,pageable);
        List<UserCommentDTO> userCommentList = new ArrayList<>();
        for (Object[] objects: objectPage.getContent()){
            Comment comment = (Comment) objects[0];
            WeiboInfo source = objects[1] == null ? null : (WeiboInfo) objects[1];
            WeiboDTO weiboDTO = new WeiboDTO(comment.getWeibo(),source);
            UserCommentDTO commentDTO = new UserCommentDTO(
                    comment.getId(),
                    comment.getUser().getId(),
                    comment.getUser().getName(),
                    comment.getContent(),
                    comment.getSendTime(),
                    weiboDTO);
            userCommentList.add(commentDTO);
        }
//        Object data = PageUtil.getPageData(commentPage);
        Object data = PageUtil.getPageData(PageUtil.listConvertToPage(userCommentList,pageable));
        return Result.success(data);
    }

    public Result getAllCommentByWeiboId(Integer weiboId, Pageable pageable){
        Page<CommentDTO> commentPage = commentRepository.selectAllCommentByWeiboId(weiboId, pageable);
        Object data = PageUtil.getPageData(commentPage);
        return Result.success(data);
    }

    public Result deleteCommentById(Integer id){
        int count = 0;
        try {
            count = commentRepository.deleteCommentById(id);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("删除失败");
        }
        if (count == 0){
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
}
