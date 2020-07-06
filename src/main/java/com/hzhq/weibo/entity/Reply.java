package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 18:48
 * @desc:
 */
@Data
@Entity
@Table(name = "reply")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "r_id")
    private Integer id;
    @Column(name = "u_id")
    private Integer fromUserId;
    @Column(name = "c_id")
    private Integer commentId;
    @Column(name = "to_u_id")
    private Integer toUserId;
    @Column(name = "r_content")
    private String content;
    @Column(name = "r_time")
    private Timestamp sendTime;
    @Column(name = "r_type")
    private Integer replyType;


}
