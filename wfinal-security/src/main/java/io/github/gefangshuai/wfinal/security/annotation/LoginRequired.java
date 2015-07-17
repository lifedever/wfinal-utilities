package io.github.gefangshuai.wfinal.security.annotation;

import java.lang.annotation.*;

/**
 * 标注需要登录验证
 * Created by gefangshuai on 2015/7/17.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface LoginRequired {
}
