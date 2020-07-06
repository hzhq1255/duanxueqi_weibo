package com.hzhq.weibo.service;

import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.entity.Weibo;
import com.hzhq.weibo.repository.WeiboRepository;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 20:47
 * @desc:
 */
@Service
public class WeiboService {

    @Autowired
    WeiboRepository weiboRepository;

    public Result getAllWeibo(Pageable pageable){
        Page<Weibo> weiboPage = weiboRepository.selectAll(pageable);
        System.out.println(weiboPage);
        Object data = PageUtil.getPageData(weiboPage);

        return Result.success(data);
    }

    public Result getLikeWeibo(Integer userId,Pageable pageable){
        User user = new User();
        user.setId(userId);
        Page<Weibo> weiboPage = weiboRepository.findAllByUser(user,pageable);
        Object data = PageUtil.getPageData(weiboPage);
        return Result.success(data);
    }


}
