package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 18:48
 * @desc:
 */
@Data
@Entity
@Table(name = "likes")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne(targetEntity = User.class,cascade = CascadeType.MERGE)
    @JoinColumn(name = "u_id",referencedColumnName = "u_id")
    private User user;
    @Column(name = "w_id")
    private Integer weiboId;
    @Column(name = "c_id")
    private Integer commentId;
    @Column(name = "r_id")
    private Integer replyId;
    private Integer status;


}
