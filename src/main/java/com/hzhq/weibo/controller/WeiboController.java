package com.hzhq.weibo.controller;

import com.hzhq.weibo.entity.User;
import com.hzhq.weibo.entity.Weibo;
import com.hzhq.weibo.service.WeiboService;
import com.hzhq.weibo.util.PageUtil;
import com.hzhq.weibo.util.Result;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/6 22:53
 * @desc:
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class WeiboController {

    @Autowired
    WeiboService weiboService;


    @RequestMapping(value = "/getAllWeibo",method = RequestMethod.GET)
    Result getAllWeibo(@RequestParam("userId") @NotNull Integer userId,
                        @RequestParam("currentPage") @NotNull Integer currentPage,
                       @RequestParam("pageSize") @NotNull Integer pageSize) throws Exception {
        if (currentPage <= 0  || pageSize <= 0){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getAllWeibo(userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/getMyWeibo",method = RequestMethod.GET)
    Result getMyWeibo(@RequestParam("userId") @NotNull Integer userId,
                        @RequestParam("currentPage") @NotNull Integer currentPage,
                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0  || pageSize <= 0){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getAllWeiboByUser(userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/getLikeWeibo",method = RequestMethod.GET)
    Result getLikeWeibo(@RequestParam("userId") @NotNull Integer userId,
                        @RequestParam("currentPage") @NotNull Integer currentPage,
                        @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0  || pageSize <= 0){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getAllLikedWeiboByUser(userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/getUserWeibo",method = RequestMethod.GET)
    Result getUserWeibo(@RequestParam("loginId") @NotNull Integer loginId,
                      @RequestParam("userId") @NotNull Integer userId,
                      @RequestParam("currentPage") @NotNull Integer currentPage,
                      @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0  || pageSize <= 0){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.getUserWeibo(loginId,userId,PageRequest.of(currentPage-1,pageSize));
    }

    @RequestMapping(value = "/sendWeibo",method = RequestMethod.POST)
    Result sendWeibo(@RequestParam("userId") @NotNull Integer userId,
                     @RequestParam("content") @NotNull String content,
                     @RequestParam(value = "tag",required = false) Integer tag,
                     @RequestParam(value = "source",required = false) Integer source){
        User user = new User();
        user.setId(userId);
        Weibo weibo = new Weibo();
        weibo.setId(null);
        weibo.setUser(user);
        weibo.setContent(content);
        weibo.setSendTime(new Date());
        if (source != null){
            weibo.setSource(new Weibo(source));
        }
        if (tag != null){
            weibo.setTag(tag);
        }
        if (predictWeibo(content) == null){
            tag = null;
        }else {
            tag = Integer.parseInt(predictWeibo(content));
        }

        weibo.setTag(tag);
        return weiboService.sendWeibo(weibo);
    }



    String predictWeibo(String content){
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5]");
        Matcher matcher = pattern.matcher(content);
        if (matcher.find()){
            content = matcher.replaceAll("");
        }
        String result = null;
        try{
            String url = "http://localhost:5000/predict";
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            MultiValueMap<String,String> map = new LinkedMultiValueMap<>();
            map.add("content",content);
            RestTemplate restTemplate = new RestTemplate();
            HttpEntity<MultiValueMap<String,String>> request = new HttpEntity<>(map,headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url,request,String.class);
            result = response.getBody();
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println("flask后台没有运行,tag默认为空");
        }

        return result;
    }


    @RequestMapping(value = "/delWeibo",method = RequestMethod.POST)
    Result delWeibo(@RequestParam("weiboId") @NotNull Integer weiboId) {

                return weiboService.delWeibo(weiboId);
    }

    @RequestMapping(value = "/searchWeibo",method = RequestMethod.POST)
    Result searchWeibo(@RequestParam("userId") @NotNull Integer userId,
                       @RequestParam("keyword") @NotNull String keyword,
                       @RequestParam("currentPage") @NotNull Integer currentPage,
                       @RequestParam("pageSize") @NotNull Integer pageSize){
        if (currentPage <= 0  || pageSize <= 0 || keyword.length() == 0){
            return Result.error("无效参数");
        }
        if (pageSize < PageUtil.DEFAULT_PAGE_SIZE){
            pageSize = PageUtil.DEFAULT_PAGE_SIZE;
        }
        return weiboService.searchWeibo(userId,keyword,PageRequest.of(currentPage-1,pageSize));
    }
}
