package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
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
    @Column(name = "w_id")
    private Integer weiboId;
//    @Column(name = "u_id")
//    private Integer userId;
    @OneToOne(targetEntity = User.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "u_id",referencedColumnName = "u_id")
    private User User;
    @Column(name = "c_content")
    private String content;
    @Column(name = "c_time")
    private Date sendTime;
//    @OneToMany(targetEntity = Reply.class,cascade = CascadeType.MERGE)
//    @JoinColumn(name = "c_id",referencedColumnName = "c_id")
//    private List<Reply> replyList;

}
