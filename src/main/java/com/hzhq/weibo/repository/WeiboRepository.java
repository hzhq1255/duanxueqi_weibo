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
     * DTO 获取微博数据
     * @param userId
     * @param pageable
     * @return
     */
    @Query(value = "SELECT " +
            "w.w_id as weiboId," +
            "w.u_id as userId," +
            "u.u_name AS `name`," +
            "tag, " +
            "w_content as content ," +
            "w_source as source, " +
            "isLiked, " +
            "COUNT(l.id) AS likeCount, " +
            "COUNT(c.c_id) as commentCount " +
            "FROM weibo w " +
            "LEFT JOIN `likes` l ON w.w_id = l.w_id " +
            "LEFT JOIN `comment` c ON w.w_id = c.w_id " +
            "LEFT JOIN ( " +
            "SELECT w_id,u_id,1 as isLiked " +
            "FROM likes " +
            "GROUP BY w_id,u_id " +
            "HAVING u_id =:userId) " +
            "as tmp ON w.w_id = tmp.w_id " +
            "LEFT JOIN `user` as u ON w.u_id = u.u_id " +
            "GROUP BY w.w_id,tmp.isLiked,u.u_name ",nativeQuery = true)
    Page<Object[]> selectALLWeiboDTO(@Param("userId") Integer userId, Pageable pageable);

    /**
     * 返回用户发过的微博
     * @param userId 用户
     * @param pageable 分页
     * @return page weibo
     */
    @Query(value = "SELECT " +
            "w.w_id as weiboId," +
            "w.u_id as userId," +
            "u.u_name AS `name`," +
            "tag, " +
            "w_content as content ," +
            "w_source as source, " +
            "isLiked, " +
            "COUNT(l.id) AS likeCount, " +
            "COUNT(c.c_id) as commentCount " +
            "FROM weibo w " +
            "LEFT JOIN `likes` l ON w.w_id = l.w_id " +
            "LEFT JOIN `comment` c ON w.w_id = c.w_id " +
            "LEFT JOIN ( " +
            "SELECT w_id,u_id,1 as isLiked " +
            "FROM likes " +
            "GROUP BY w_id,u_id " +
            "HAVING u_id =:userId) " +
            "as tmp ON w.w_id = tmp.w_id " +
            "LEFT JOIN `user` as u ON w.u_id = u.u_id " +
            "GROUP BY w.w_id,tmp.isLiked,u.u_name " +
            "HAVING w.u_id =:userId",nativeQuery = true)
    Page<Object[]> selectAllByUser(Integer userId,Pageable pageable);


    /**
     * select all
     * @param userId
     * @param pageable
     * @return
     */
    @Query("select w from Weibo w left join Like l on w.id = l.weiboId and l.user.id =:userId ")
    Page<Weibo> selectALl(Integer userId,Pageable pageable);

    /**
     * 删除
     * @param id weiboId
     */
    @Modifying
    @Transactional(rollbackFor=Exception.class)
    void deleteWeiboById(Integer id);
}
