package com.hzhq.weibo.repository;

import com.hzhq.weibo.entity.Like;
import com.hzhq.weibo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:37
 * @desc:
 */
@Repository
public interface LikeRepository extends JpaRepository<Like,Integer> {

    /**
     * 微博点赞
     * @param user user
     * @param weiboId weiboId
     * @return 微博点赞树
     */
    Integer countDistinctByUserAndWeiboId(User user,Integer weiboId);

    /**
     * 评论点赞
     * @param user user
     * @param commentId commentId
     * @return 评论点赞数
     */
    Integer countDistinctByUserAndCommentId(User user,Integer commentId);

    /**
     * 回复点赞
     * @param user user
     * @param replyId replyId
     * @return 回复点赞数
     */
    Integer countDistinctByUserAndReplyId(User user,Integer replyId);

    /**
     * 是否存在
     * @param user user
     * @param weiboId weiboId
     * @return true
     */
    Boolean existsByUserAndWeiboId(User user,Integer weiboId);

    /**
     * 是否存在
     * @param user user
     * @param commentId commentId
     * @return true
     */
    Boolean existsByUserAndCommentId(User user,Integer commentId);

    /**
     * 是否存在
     * @param user user
     * @param replyId replyId
     * @return true
     */
    Boolean existsByUserAndReplyId(User user,Integer replyId);

    /**
     * 删除
     * @param user user
     * @param commentId commentId
     * @return 删除行数
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Integer deleteCommentByUserAndCommentId(User user,Integer commentId);

    /**
     * 删除
     * @param user user
     * @param weiboId weiboId
     * @return 删除行数
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Integer deleteCommentByUserAndWeiboId(User user,Integer weiboId);

    /**
     * 删除
     * @param user user
     * @param replyId replyId
     * @return 删除行数
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Integer deleteCommentByUserAndReplyId(User user,Integer replyId);

}
