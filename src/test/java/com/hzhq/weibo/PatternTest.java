package com.hzhq.weibo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: hzhq1255
 * @mail: hzhq1255@163.com
 * @date: 2020/7/9 23:49
 * @desc:
 */
public class PatternTest {
    public static void main(String[] args) {
        String str = "asAsdf234(\\n\\sdf\t你好asdfm2)./'\"`234啊";
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9\\u4E00-\\u9FA5]");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()){
            System.out.println(matcher.replaceAll(""));
        }

    }
}
