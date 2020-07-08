package com.hzhq.weibo.repository;

import com.hzhq.weibo.dto.WeiboDTO;
import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.entity.Weibo;
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
 * @date: 2020/7/6 20:12
 * @desc:
 */
@Repository
public interface WeiboRepository extends JpaRepository<Weibo,Integer> {

    /**
     * 返回所有数据
     * @param pageable 分页
     * @return page weibo
     */
    @Query("select w from Weibo w order by w.sendTime desc ")
    Page<Weibo> selectAll(Pageable pageable);



    /**
     * 删除
     * @param id weiboId
     * @return 删除行数
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    Integer deleteWeiboById(Integer id);
}
