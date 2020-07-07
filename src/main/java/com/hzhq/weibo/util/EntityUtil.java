package com.hzhq.weibo.util;

import com.hzhq.weibo.dto.WeiboDTO;

import java.lang.reflect.Constructor;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/7 14:34
 * @desc:
 */
public class EntityUtil {
    public static  <T> T caseDto(Object[] objectArray, Class[] objectClassArray, Class<T> dtoClass) throws Exception {
        Constructor<T> constructor = dtoClass.getConstructor(objectClassArray);
        return constructor.newInstance(objectArray);
    }


}
