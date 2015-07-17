package io.github.gefangshuai.wfinal.menumapper.core;

import java.lang.annotation.*;

/**
 * 菜单注入
 * Created by gefangshuai on 2015/7/6.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Menu {

    /**
     * 菜单名称
     * @return
     */
    String mapper ();
}
