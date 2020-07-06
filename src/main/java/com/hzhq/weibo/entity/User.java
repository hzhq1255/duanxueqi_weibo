package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 18:48
 * @desc:
 */
@Data
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class User {

    @Id
    @Column(name = "u_id")
    private Integer id;
    @Column(name = "u_name")
    private String name;
    @Column(name = "u_pwd")
    @JsonIgnore
    private String pwd;
    @Column(name = "u_pic")
    private String pic;
    @Column(name = "u_gender")
    private String gender;
    @Column(name = "u_des")
    private String des;
    @Column(name = "u_reg_time")
    private Timestamp regTime;
    @Column(name = "u_birth")
    private Timestamp birth;
}
