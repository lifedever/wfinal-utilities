package io.github.gefangshuai.wfinal.security.annotation;

import java.lang.annotation.*;

/**
 * 清除登录验证标识
 * Created by gefangshuai on 2015/7/27.
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD })
public @interface LoginClear {
}
