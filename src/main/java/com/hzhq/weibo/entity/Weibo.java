package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 18:48
 * @desc:
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "weibo")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Weibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "w_id")
    private Integer id;
    @OneToOne(targetEntity = User.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "u_id",referencedColumnName = "u_id")
    private User user;
    @Column(name = "w_content")
    private String content;
    @Column(name = "w_source")
    private Integer source;
    @Column(name = "w_time")
    private Date sendTime;
    @Column(name = "tag")
    private Integer tag;
//    @OneToMany(targetEntity = Comment.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "w_id",referencedColumnName = "w_id")
//    private List<Comment> commentList;
//    @OneToMany(targetEntity = Like.class,cascade = CascadeType.ALL)
//    @JoinColumn(name = "w_id",referencedColumnName = "w_id")
//    private List<Like> likeList;

}
