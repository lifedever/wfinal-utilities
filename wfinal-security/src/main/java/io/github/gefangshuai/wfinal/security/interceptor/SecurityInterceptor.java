package io.github.gefangshuai.wfinal.security.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.JfinalKit;
import io.github.gefangshuai.wfinal.base.utils.SessionKit;
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
        controller.setAttr(securityPlugin.getSubjectKey(), SecurityKit.getSubject(controller.getSession()));
        if (needLoginCheck(controller, method)) {   // 需要登录验证
            if(hasLogged(controller, method)) {    // 登录成功
                clearUrlBeforeLoginInfo(controller);
                inv.invoke();
            }else {
                saveUrlBeforeLoginToSession(controller);
                if (securityPlugin.isBackToLoginPage()) {
                    controller.redirect(securityPlugin.getLoginUrl());
                } else {
                    controller.renderError(401);
                }
            }
        }else{
            inv.invoke();
        }
    }

    /**
     * 是否需要登录验证
     * @param controller
     * @param method
     * @return
     */
    private boolean needLoginCheck(Controller controller, Method method) {
        return controller.getClass().isAnnotationPresent(LoginRequired.class) || method.isAnnotationPresent(LoginRequired.class);
    }

    /**
     * 将登录前被拦截的url保存到session中
     * @param controller
     */
    private void saveUrlBeforeLoginToSession(Controller controller) {
        String urlBeforeLogin = controller.getRequest().getServletPath();
        controller.setSessionAttr(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN, urlBeforeLogin);
    }

    /**
     * 清除保存的登陆前访问的url
     * @param controller
     */
    private void clearUrlBeforeLoginInfo(Controller controller) {
        if(controller.getSessionAttr(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN)!=null)
            controller.removeSessionAttr(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN);
    }

    /**
     * 验证是否登陆
     * @param controller
     * @param method
     * @return
     */
    private boolean hasLogged(Controller controller, Method method) {
        Subject subject = SecurityKit.getSubject(controller.getSession());
        return subject.isLogin();
    }
}
