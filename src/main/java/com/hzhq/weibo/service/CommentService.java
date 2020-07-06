package com.hzhq.weibo.service;

import com.hzhq.weibo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:57
 * @desc:
 */
@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;
}
