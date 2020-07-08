package com.hzhq.weibo.dto;

import com.hzhq.weibo.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:51
 * @desc:
 */
@Data
public class CommentDTO {

    private Integer commentId;
    private Integer weiboId;
    private Integer userId;
    private String name;
    private String content;
    private Date sendTime;

    public CommentDTO(){

    }
    public CommentDTO(Integer commentId, Integer weiboId, Integer userId, String name, String content, Date sendTime) {
        this.commentId = commentId;
        this.weiboId = weiboId;
        this.userId = userId;
        this.name = name;
        this.content = content;
        this.sendTime = sendTime;
    }

}
