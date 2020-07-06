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
     * @param weiboId weiboId
     * @return 微博点赞树
     */
    Integer countDistinctByWeiboId(Integer weiboId);

    /**
     * 评论点赞
     * @param commentId commentId
     * @return 评论点赞数
     */
    Integer countDistinctByCommentId(Integer commentId);

    /**
     * 回复点赞
     * @param replyId replyId
     * @return 回复点赞数
     */
    Integer countDistinctByReplyId(Integer replyId);

    /**
     * 删除
     * @param user user
     * @param commentId commentId
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    void deleteCommentByUserAndCommentId(User user,Integer commentId);

    /**
     * 删除
     * @param user user
     * @param weiboId weiboId
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    void deleteCommentByUserAndWeiboId(User user,Integer weiboId);

    /**
     * 删除
     * @param user user
     * @param replyId replyId
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    void deleteCommentByUserAndReplyId(User user,Integer replyId);

}
