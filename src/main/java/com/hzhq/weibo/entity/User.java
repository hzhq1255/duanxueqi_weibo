package com.hzhq.weibo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 18:48
 * @desc:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date regTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "u_birth")
    private Date birth;


}
