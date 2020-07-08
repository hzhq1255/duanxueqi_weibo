package com.hzhq.weibo.service;

import com.hzhq.weibo.dto.WeiboDTO;
import com.hzhq.weibo.entity.Weibo;
import com.hzhq.weibo.repository.WeiboInfoRepository;
import com.hzhq.weibo.repository.WeiboRepository;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.util.Date;

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

    @Autowired
    WeiboInfoRepository weiboInfoRepository;


    public Result getAllWeibo(Integer userId, Pageable pageable) throws Exception {
        Page<WeiboDTO> weiboPage = weiboInfoRepository.selectAllWeibo(userId,pageable);
        Object data = PageUtil.getPageData(weiboPage);
        return Result.success(data);
    }

    public Result getAllWeiboByUser(Integer userId, Pageable pageable) {
        Page<WeiboDTO> weiboPage= weiboInfoRepository.selectAllWeiboByUser(userId,pageable);
        Object data = PageUtil.getPageData(weiboPage);
        return Result.success(data);
    }

    public Result getAllLikedWeiboByUser(Integer userId, Pageable pageable) {
        Page<WeiboDTO> weiboPage = weiboInfoRepository.selectAllLikedWeiboByUser(userId,pageable);
        Object data = PageUtil.getPageData(weiboPage);
        return Result.success(data);
    }

    public Result sendWeibo(Weibo weibo){
        try {
            weiboRepository.save(weibo);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("发布失败");
        }
        return Result.success("成功发布微博");

    }

    public Result delWeibo(Integer weiboId){
        int count = 0;
        try{
            count = weiboRepository.deleteWeiboById(weiboId);
        }catch (Exception e){
            e.printStackTrace();
            return Result.error("删除失败");
        }
        if (count == 0){
            return Result.error("删除失败");
        }
        return Result.success("成功删除"+count+"行");
    }


}
