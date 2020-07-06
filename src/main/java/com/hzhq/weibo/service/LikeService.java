package com.hzhq.weibo.service;

import com.hzhq.weibo.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 21:00
 * @desc:
 */
@Service
public class LikeService {

    @Autowired
    LikeRepository likeRepository;


}
