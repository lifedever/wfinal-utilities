package io.github.gefangshuai.wfinal.model.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 查询工具类
 * Created by gefangshuai on 2015/7/9.
 */
public class QueryUtils {
    /**
     * 获得适用于like查询的值(两边都有<code>%</code>)
     *
     * @param value
     * @return
     */
    public static String getLikeValue(String value) {
        return "%" + getNotNullValue(value) + "%";
    }

    /**
     * 获得适用于like查询的值(左边有<code>%</code>)
     * @param value
     * @return
     */
    public static String getLikeValueLeft(String value) {
        return "%" + getNotNullValue(value);
    }

    /**
     * 获得适用于like查询的值(右边有<code>%</code>)
     * @param value
     * @return
     */
    public static String getLikeValueRight(String value) {
        return getNotNullValue(value) + "%";
    }

    private static String getNotNullValue(String value) {
        return StringUtils.isBlank(value) ? "" : value;
    }
}
