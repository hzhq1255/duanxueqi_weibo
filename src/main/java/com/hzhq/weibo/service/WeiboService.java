package com.hzhq.weibo.service;

import com.hzhq.weibo.dto.WeiboDTO;
import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.entity.Weibo;
import com.hzhq.weibo.repository.WeiboInfoRepository;
import com.hzhq.weibo.repository.WeiboRepository;
import com.hzhq.weibo.util.EntityUtil;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

//    public Page<WeiboDTO> convertToPageDTO(List<Object[]> objectList,Pageable pageable){
//        List<WeiboDTO> weiboDTOList = new ArrayList<>();
//        for (Object[] objects : objectList) {
//            if (objects.length == 0){
//                continue;
//            }
//            WeiboDTO weibo = new WeiboDTO(
//                    (Integer) objects[0],
//                    (Integer) objects[1],
//                    (String) objects[2],
//                    (String) objects[3],
//                    (String) objects[4],
//                    (WeiboDTO) objects[5],
//                    (Integer) objects[6],
//                    (BigInteger) objects[7],
//                    (BigInteger) objects[8]
//            );
//            weiboDTOList.add(weibo);
//        }
//        Page<WeiboDTO> weiboPage = PageUtil.listConvertToPage(weiboDTOList,pageable);
//        return weiboPage;
//    }

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
        weiboRepository.save(weibo);
        return Result.success("发布微博成功");
    }


}
