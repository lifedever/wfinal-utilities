package io.github.gefangshuai.wfinal.security.annotation;

import java.lang.annotation.*;

/**
 * 注册需要校验的角色
 * Created by gefangshuai on 2015/7/17.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface AccessClear {
}
