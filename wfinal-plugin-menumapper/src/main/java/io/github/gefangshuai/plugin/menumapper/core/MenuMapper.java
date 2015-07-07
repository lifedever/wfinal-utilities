package io.github.gefangshuai.plugin.menumapper.core;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gefangshuai on 2015/7/6.
 */
public class MenuMapper {
    private static MenuMapper instance;

    public static synchronized MenuMapper getInstance() {
        if (instance == null) {
            instance = new MenuMapper();
        }
        return instance;
    }

    private MenuMapper() {
    }

    private Map<Object, Menu> ctrlMap = new HashMap<>();
    private String attribute;

    public Map<Object, Menu> getCtrlMap() {
        return ctrlMap;
    }

    public void setCtrlMap(Map<Object, Menu> ctrlMap) {
        this.ctrlMap = ctrlMap;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
