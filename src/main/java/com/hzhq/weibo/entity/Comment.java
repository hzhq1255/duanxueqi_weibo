package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 18:48
 * @desc:
 */
@Data
@Entity
@Table(name = "comment")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private Integer id;
    @OneToOne(targetEntity = Weibo.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "w_id",referencedColumnName = "w_id")
    private Weibo weibo;
//    @Column(name = "u_id")
//    private Integer userId;
    @OneToOne(targetEntity = User.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "u_id",referencedColumnName = "u_id")
    private User user;
    @Column(name = "c_content")
    private String content;
    @Column(name = "c_time")
    private Date sendTime;
    @OneToMany(targetEntity = Reply.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "c_id",referencedColumnName = "c_id")
    private List<Reply> replyList;

}
