package net.wincn.utils;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.core.TableInfo;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangshuai on 2014-12-08-0008.
 */
public class TableUtils {

    private Map<Class<? extends Model>, TableInfo> tableInfoMap = new HashMap<>();
    private static TableUtils instance;

    private TableUtils() {

    }

    public static synchronized TableUtils getInstance() {
        if (instance == null) {
            instance = new TableUtils();
        }
        return instance;
    }

    /**
     * 根据对象反射TableBind信息
     *
     * @param clazz
     * @return
     */
    public static TableBind getTableBind(Object clazz) {
        TableBind tableBind = null;
        Annotation[] annotations = clazz.getClass().getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation instanceof TableBind) {
                tableBind = (TableBind) annotation;
                break;
            }
        }
        return tableBind;
    }

    public Map<Class<? extends Model>, TableInfo> getTableInfoMap() {
        return tableInfoMap;
    }

    private static TableInfo getTableInfo(Class clazz) {
        TableInfo tableInfo = TableUtils.getInstance().getTableInfoMap().get(clazz);
        return tableInfo;
    }

    public static Class getPkType(Class clazz) {
        TableInfo tableInfo = getTableInfo(clazz);
        return tableInfo == null ? Integer.class : tableInfo.pkType();
    }
}
