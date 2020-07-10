package com.hzhq.weibo;

import com.hzhq.weibo.dto.UserCommentDTO;
import com.hzhq.weibo.entity.Weibo;
import com.hzhq.weibo.repository.CommentRepository;
import com.hzhq.weibo.repository.WeiboRepository;
import com.hzhq.weibo.service.CommentService;
import com.hzhq.weibo.util.Result;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@SpringBootTest
class WeiboApplicationTests {

    @Autowired
    WeiboRepository weiboRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentService commentService;

    @Test
    void contextLoads() {
    }

    @Test
    void getWeibo(){
        Page<Weibo> weiboPage = weiboRepository.selectAll(PageRequest.of(0,10));
        System.out.println(weiboPage);
    }

    @Test
    void getComment(){
//        Page<Object[]> page = commentRepository.selectAllCommentByUserId(2,PageRequest.of(0,10));
        Result page = commentService.getAllCommentByUserId(2,PageRequest.of(0,10));
        System.out.println(page.toString());
    }

}
