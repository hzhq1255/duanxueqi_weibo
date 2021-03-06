package com.hzhq.weibo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzhq.weibo.entity.WeiboInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;


/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/7 9:07
 * @desc:
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WeiboDTO{
    private Integer weiboId;
    private Integer userId;
    private String name;
    private String pic;
    private Integer tag;
    private String content;
    private Integer isLiked;
    private Integer likeCount;
    private Integer commentCount;
    @JsonFormat
    private Date sendTime;
    private WeiboInfo source;


    public WeiboDTO(Integer weiboId, Integer userId, String name, String pic, Integer tag, String content, Integer isLiked, Integer likeCount, Integer commentCount, Date sendTime,
                    Integer source, Integer sourceUserId, String sourceName, String sourcePic, Integer sourceTag, String sourceContent, Integer sourceLikeCount, Integer sourceCommentCount, Date sourceSendTime) {
        this.weiboId = weiboId;
        this.userId = userId;
        this.name = name;
        this.pic = "".equals(pic) ? null : pic;
        this.tag = tag;
        this.content = content;
        this.isLiked = isLiked == null ? 0 : isLiked;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
        this.sendTime =sendTime;
        this.source = source == null ? null : new WeiboInfo(source,sourceUserId,sourceName,sourcePic,sourceContent,sourceTag,sourceLikeCount,sourceCommentCount,sourceSendTime,null);
    }

    public WeiboDTO(WeiboInfo weibo, WeiboInfo source){
        this.weiboId = weibo.getWeiboId();
        this.userId = weibo.getUserId();
        this.name = weibo.getName();
        this.pic = "".equals(weibo.getPic()) ? null : weibo.getPic();
        this.tag = weibo.getTag();
        this.content = weibo.getContent();
        this.isLiked = null;
        this.likeCount = weibo.getLikeCount();
        this.commentCount = weibo.getCommentCount();
        this.sendTime = weibo.getSendTime();
        this.source = source;
    }
}