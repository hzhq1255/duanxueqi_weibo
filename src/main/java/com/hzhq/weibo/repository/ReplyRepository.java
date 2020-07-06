package com.hzhq.weibo.repository;

import com.hzhq.weibo.entity.Reply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:34
 * @desc:
 */
@Repository
public interface ReplyRepository extends JpaRepository<Reply,Integer> {

    /**
     * 所有评论
     * @param commentId 评论id
     * @param pageable 分页
     * @return 分页评论
     */
    Page<Reply> findAllByCommentId(Integer commentId,Pageable pageable);

    /**
     * 删除
     * @param id id
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    void deleteReplyById(Integer id);

}
