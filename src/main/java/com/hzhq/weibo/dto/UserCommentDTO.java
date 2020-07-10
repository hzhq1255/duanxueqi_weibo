package com.hzhq.weibo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/10 10:46
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCommentDTO {

    private Integer commentId;
    private WeiboDTO weibo;
    private Integer userId;
    private String name;
    private String content;
    private Date sendTime;

    public UserCommentDTO(Integer commentId, WeiboDTO weibo, Integer userId, String name, String content, Date sendTime) {
        this.commentId = commentId;
        this.weibo = weibo;
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.sendTime = sendTime;
    }
}
