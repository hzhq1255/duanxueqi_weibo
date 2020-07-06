package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 18:48
 * @desc:
 */
@Data
@Entity
@Table(name = "weibo")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Weibo {

    @Id
    @Column(name = "w_id")
    private Integer id;
    @Column(name = "u_id")
    @OneToOne
    private User user;
    @Column(name = "w_content")
    private String content;
    @Column(name = "w_source")
    private Integer sourceWeibo;
    @Column(name = "w_time")
    private Timestamp sendTime;
    @Column(name = "tag")
    private String tag;
    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "w_id",referencedColumnName = "w_id")
    private List<Comment> commentList;

}
