package net.wincn.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by fangshuai on 2014-09-04-0004.
 */
public class StrUtils {
    public static String getLikePara(String value) {
        return "%"+value+"%";
    }

    public static String getCurrentDateString() {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 根据传递的排序获得相反的排序
     * @param sort
     * @return
     */
    public static String getReverseSort(String sort) {
        return sort.equals("desc") ? "asc" : "desc";
    }

}
