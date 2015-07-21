package io.github.gefangshuai.wfinal.security.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.JfinalKit;
import io.github.gefangshuai.wfinal.security.annotation.LoginRequired;
import io.github.gefangshuai.wfinal.security.core.*;
import io.github.gefangshuai.wfinal.security.core.SecurityKit;
import io.github.gefangshuai.wfinal.security.plugin.SecurityPlugin;

import java.lang.reflect.Method;

/**
 * 安全拦截器
 * Created by gefangshuai on 2015/7/20.
 */
public class SecurityInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        SessionKit.put(controller.getSession());
        Method method = inv.getMethod();
        SecurityPlugin securityPlugin = (SecurityPlugin) JfinalKit.findPlugin(SecurityPlugin.class).get(0);
        if(loginCheck(controller, method)) {
            controller.setAttr(securityPlugin.getSubjectKey(), SecurityKit.getSubject(controller.getSession()));
            inv.invoke();
        }else {
            if (securityPlugin.isBackToLoginPage()) {
                controller.redirect(securityPlugin.getLoginUrl());
            }else{
                controller.renderError(401);
            }
        }
    }

    /**
     * 验证是否登陆
     * @param controller
     * @param method
     * @return
     */
    private boolean loginCheck(Controller controller, Method method) {
        Subject subject = SecurityKit.getSubject(controller.getSession());
        // 类上有注解，则所有的方法请求都需要进行过滤
        if (controller.getClass().getAnnotation(LoginRequired.class) != null && !subject.isLogin()) {
            return false;
        }
        // 注解在方法上
        if (method.getAnnotation(LoginRequired.class) != null && !subject.isLogin()) {
            return false;
        }
        return true;
    }
}
