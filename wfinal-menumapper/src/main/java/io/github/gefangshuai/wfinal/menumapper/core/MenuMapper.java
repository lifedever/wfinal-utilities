package io.github.gefangshuai.wfinal.menumapper.core;

import io.github.gefangshuai.wfinal.menumapper.annotation.Menu;

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

    private String attribute;



    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }
}
