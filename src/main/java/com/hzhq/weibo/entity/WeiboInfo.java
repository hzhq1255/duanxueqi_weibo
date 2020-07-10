package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hzhq.weibo.dto.WeiboDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/7 18:27
 * @desc:
 * CREATE VIEW weibo_info AS
 * SELECT w1.*,
 * w2.u_id as s_u_id,
 * w2.u_name as s_u_name,
 * w2.w_content as s_content,
 * w2.tag as s_tag,
 * w2.like_count as s_like_count,
 * w2.comment_count as s_comment_count,
 * w2.w_time as s_w_time
 * FROM weibo_sample w1
 * LEFT JOIN weibo_sample w2 ON w1.w_source = w2.w_id
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weibo_sample")
public class WeiboInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "w_id")
    private Integer weiboId;
    @Column(name = "u_id")
    private Integer userId;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_pic")
    private String pic;
    @Column(name = "w_content")
    private String content;
    @Column(name = "tag")
    private Integer tag;
    @Column(name = "like_count")
    private Integer likeCount;
    @Column(name = "comment_count")
    private Integer commentCount;
    @Column(name = "w_time")
    private Date sendTime;
    @Column(name = "w_source")
    private Integer source;
//    @OneToOne(targetEntity = Weibo.class,cascade = CascadeType.MERGE)
//    @JoinColumn(name = "w_source",referencedColumnName = "w_id")
//    private Weibo source;


    public WeiboInfo(Integer weiboId) {
        this.weiboId = weiboId;
    }
}
