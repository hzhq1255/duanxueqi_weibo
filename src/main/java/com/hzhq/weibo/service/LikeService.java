package com.hzhq.weibo.service;

import com.hzhq.weibo.entity.Like;
import com.hzhq.weibo.repository.LikeRepository;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 21:00
 * @desc:
 */
@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;

    Integer getType(Like like){
        int flag = 0;
        if ( like.getUser() != null &&like.getWeiboId() != null && like.getCommentId() == null   && like.getReplyId() == null){
            flag = 1;
        }else if (like.getUser() != null &&like.getWeiboId() == null && like.getCommentId() != null && like.getReplyId() == null){
            flag = 2;
        }else if (like.getUser() != null &&like.getWeiboId() == null && like.getCommentId() == null && like.getReplyId() != null){
            flag = 3;
        }
        return flag;
    }

    Integer isExist(Like like){
        int flag = -1;
        boolean is  = true;
        if ( like.getUser() != null &&like.getWeiboId() != null && like.getCommentId() == null   && like.getReplyId() == null){
            is = likeRepository.existsByUserAndWeiboId(like.getUser(), like.getWeiboId());
            flag = is ? 0:1;
            System.out.println();
        }else if (like.getUser() != null &&like.getWeiboId() == null && like.getCommentId() != null && like.getReplyId() == null){
            is = likeRepository.existsByUserAndCommentId(like.getUser(), like.getCommentId());
            flag = is ? 0:2;
        }else if (like.getUser() != null &&like.getWeiboId() == null && like.getCommentId() == null && like.getReplyId() != null){
            is = likeRepository.existsByUserAndReplyId(like.getUser(), like.getReplyId());
            flag = is ? 0:3;
        }
        return flag;
    }

    public Result addLike(Like like){
        if (isExist(like) == 0){
            return Result.error("请不要重复点赞");
        }
        if (isExist(like) == -1){
            return Result.error("参数错误");
        }
        try {
            likeRepository.save(like);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("插入失败");
        }

        return Result.success("点赞成功");
    }

    public Result cancelLike(Like like){
        int count = 0;
        switch (getType(like)){
            case 1:
                count = likeRepository.deleteCommentByUserAndWeiboId(like.getUser(),like.getWeiboId());
                break;
            case 2:
                count = likeRepository.deleteCommentByUserAndCommentId(like.getUser(),like.getCommentId());
                break;
            case 3:
                count = likeRepository.deleteCommentByUserAndReplyId(like.getUser(),like.getReplyId());
                break;
            case 0:
                return Result.error("参数错误");
            default:break;
        }
        if (count == 0){
            return Result.error("取消点赞失败");
        }else {
            return Result.success("取消点赞成功");
        }
    }

}
