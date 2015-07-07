package io.github.gefangshuai.plugin.menumapper.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 扩展字符串工具类
 *
 * Created by fangshuai on 2014-09-04-0004.
 */
public class StrUtils {

    /**
     * 获取sql like查询字符串
     * @param value
     * @return %value%
     */
    public static String getLikePara(String value) {
        return "%"+value+"%";
    }

    /**
     * 获取当前日期格式化后的字符串形式（默认格式yyyy-MM-dd）
     * @return
     */
    public static String getCurrentDateString() {
        return getCurrentDateString("yyyy-MM-dd");
    }

    /**
     * 获取当前日期格式化后的字符串形式
     * @return
     */
    public static String getCurrentDateString(String pattern){
        SimpleDateFormat sdf=new SimpleDateFormat(pattern);
        return sdf.format(new Date());
    }

    /**
     * 根据传递的排序获得相反的排序
     * @param sort desc or asc
     * @return
     */
    public static String getReverseSort(String sort) {
        sort = sort.toLowerCase();
        return sort.equals("desc") ? "asc" : "desc";
    }

}
