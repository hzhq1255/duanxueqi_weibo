package com.hzhq.weibo.dto;

import com.hzhq.weibo.entity.WeiboInfo;
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
    private Integer userId;
    private String name;
    private String content;
    private Date sendTime;
    private WeiboDTO weibo;

}
