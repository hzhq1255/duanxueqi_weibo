package com.hzhq.weibo.repository;

import com.hzhq.weibo.dto.WeiboDTO;
import com.hzhq.weibo.entity.WeiboInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/7 18:45
 * @desc:
 *     private Integer weiboId;
 *     private Integer userId;
 *     private String name;
 *     private String tag;
 *     private String content;
 *     private Integer isLiked;
 *     private Integer likeCount;
 *     private Integer commentCount;
 *     private WeiboInfo source;
 *         public WeiboDTO(Integer weiboId, Integer userId, String name, String tag, String content, Integer isLiked, Integer likeCount, Integer commentCount,
 *                     Integer source, Integer sourceUserId,String sourceName,String sourceTag,String sourceContent,Integer sourceLikeCount,Integer sourceCommentCount) {
 */
public interface WeiboInfoRepository extends JpaRepository<WeiboInfo,Integer> {


    /**
     * 获取处理过的微博信息
     * @param userId
     * @param pageable
     * @return
     */
    @Query(" select new com.hzhq.weibo.dto.WeiboDTO( " +
            "w.weiboId," +
            "w.userId," +
            "w.name," +
            "w.pic," +
            "w.tag," +
            "w.content," +
            "l.status," +
            "w.likeCount," +
            "w.commentCount," +
            "w.sendTime," +
            "s.weiboId," +
            "s.userId," +
            "s.name," +
            "s.pic," +
            "s.tag," +
            "s.content," +
            "s.likeCount," +
            "s.commentCount," +
            "s.sendTime" +
            ") " +
            "from WeiboInfo w " +
            "left join WeiboInfo s ON w.source = s.weiboId " +
            "left join Like l on w.weiboId = l.weiboId and l.user.id=:userId " +
            "order by w.sendTime desc ")
    Page<WeiboDTO> selectAllWeibo(@Param("userId") Integer userId, Pageable pageable);


    /**
     * 获取处理过的微博信息
     * @param userId
     * @param pageable
     * @return
     */
    @Query(" select new com.hzhq.weibo.dto.WeiboDTO( " +
            "w.weiboId," +
            "w.userId," +
            "w.name," +
            "w.pic," +
            "w.tag," +
            "w.content," +
            "l.status," +
            "w.likeCount," +
            "w.commentCount," +
            "w.sendTime," +
            "s.weiboId," +
            "s.userId," +
            "s.name," +
            "s.pic," +
            "s.tag," +
            "s.content," +
            "s.likeCount," +
            "s.commentCount," +
            "s.sendTime" +
            ") " +
            "from WeiboInfo w " +
            "left join WeiboInfo s ON w.source = s.weiboId " +
            "left join Like l on w.weiboId = l.weiboId and l.user.id=:userId " +
            "where w.userId=:userId " +
            "order by w.sendTime desc ")
    Page<WeiboDTO> selectAllWeiboByUser(@Param("userId") Integer userId, Pageable pageable);

    /**
     * 获取处理过的微博信息
     * @param userId
     * @param pageable
     * @param loginId
     * @return
     */
    @Query(" select new com.hzhq.weibo.dto.WeiboDTO( " +
            "w.weiboId," +
            "w.userId," +
            "w.name," +
            "w.pic," +
            "w.tag," +
            "w.content," +
            "l.status," +
            "w.likeCount," +
            "w.commentCount," +
            "w.sendTime," +
            "s.weiboId," +
            "s.userId," +
            "s.name," +
            "s.pic," +
            "s.tag," +
            "s.content," +
            "s.likeCount," +
            "s.commentCount," +
            "s.sendTime" +
            ") " +
            "from WeiboInfo w " +
            "left join WeiboInfo s ON w.source = s.weiboId " +
            "left join Like l on w.weiboId = l.weiboId and l.user.id=:loginId " +
            "where w.userId=:userId " +
            "order by w.sendTime desc ")
    Page<WeiboDTO> selectAllWeiboByUserAndLoginUser(@Param("loginId") Integer loginId,@Param("userId") Integer userId, Pageable pageable);

    /**
     * 用户点赞的微博
     * @param userId
     * @param pageable
     * @return
     */
    @Query(" select new com.hzhq.weibo.dto.WeiboDTO( " +
            "w.weiboId," +
            "w.userId," +
            "w.name," +
            "w.pic," +
            "w.tag," +
            "w.content," +
            "l.status," +
            "w.likeCount," +
            "w.commentCount," +
            "w.sendTime," +
            "s.weiboId," +
            "s.userId," +
            "s.name," +
            "s.pic," +
            "s.tag," +
            "s.content," +
            "s.likeCount," +
            "s.commentCount," +
            "s.sendTime" +
            ") " +
            "from WeiboInfo w " +
            "left join WeiboInfo s ON w.source = s.weiboId " +
            "left join Like l on w.weiboId = l.weiboId and l.user.id=:userId " +
            "where l.user.id=:userId and l.status = 1" +
            "order by w.sendTime desc ")
    Page<WeiboDTO> selectAllLikedWeiboByUser(@Param("userId") Integer userId, Pageable pageable);

}
