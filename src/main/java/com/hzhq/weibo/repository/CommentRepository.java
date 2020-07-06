package com.hzhq.weibo.repository;

import com.hzhq.weibo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:24
 * @desc:
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment,Integer> {


    /**
     * 获取用户评论
     * @param userId userId
     * @param pageable 分页
     * @return 分页评论
     */
    @Query("select c.id,c.weiboId,c.User.id,c.sendTime,c.content from Comment c where c.User.id=:userId order by c.sendTime")
    Page<Comment> selectAllByUser(@Param("userId") Integer userId, Pageable pageable);

    /**
     * 所有评论
     * @param pageable 分页
     * @return 分页评论
     */
    @Query("select c from Comment c order by c.sendTime")
    Page<Comment> selectAll(Pageable pageable);

    /**
     * 删除
     * @param id id
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    void deleteCommentById(Integer id);
}
