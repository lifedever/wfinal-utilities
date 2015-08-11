package io.github.gefangshuai.wfinal.security.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.JfinalKit;
import io.github.gefangshuai.wfinal.base.utils.SessionKit;
import io.github.gefangshuai.wfinal.security.annotation.AccessClear;
import io.github.gefangshuai.wfinal.security.annotation.LoginClear;
import io.github.gefangshuai.wfinal.security.annotation.LoginRequired;
import io.github.gefangshuai.wfinal.security.core.SecurityConst;
import io.github.gefangshuai.wfinal.security.core.SecurityKit;
import io.github.gefangshuai.wfinal.security.core.SecurityRule;
import io.github.gefangshuai.wfinal.security.core.Subject;
import io.github.gefangshuai.wfinal.security.plugin.SecurityPlugin;

import java.lang.reflect.Method;
import java.util.List;

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
        SecurityRule securityRule = securityPlugin.getSecurityRule();
        Subject subject = SecurityKit.getSubject(controller);
        controller.setAttr(securityRule.getSubjectKey(), subject);

        if (subject.isLogin() && securityRule.isUseAccessActionFilter()) {
            if(!accessActionCheck(inv)){
                controller.renderError(403);
            }
        }

        if (needLoginCheck(securityRule, controller, method)) {   // 需要登录验证
            if (hasLogged(controller)) {    // 登录成功
                clearUrlBeforeLoginInfo(controller);
                inv.invoke();
            } else {
                saveUrlBeforeLoginToSession(controller);
                if (securityRule.isBackToLoginPage()) {
                    controller.redirect(securityRule.getLoginUrl());
                } else {
                    controller.renderError(401);
                }
            }
        } else {
            inv.invoke();
        }
    }

    /**
     * 是否有权限访问当前
     */
    private boolean accessActionCheck(Invocation inv) {
        Controller controller = inv.getController();
        Method method = inv.getMethod();

        if (method.isAnnotationPresent(AccessClear.class) || controller.getClass().isAnnotationPresent(AccessClear.class))
            return true;

        Subject subject = SecurityKit.getSubject(controller);
        if (subject.getAccessAction() == null)
            return false;
        String servletPath = controller.getRequest().getServletPath();
        for (String accA : subject.getAccessAction()) {
            if (servletPath.startsWith(accA)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否需要登录验证
     *
     * @param controller
     * @param method
     * @return
     */
    private boolean needLoginCheck(SecurityRule securityRule, Controller controller, Method method) {
        boolean ruleConf = false;
        for (String url : securityRule.getFilterUrls()) {
            url = url.endsWith("/") ? url : url + "/";
            String servletPath = controller.getRequest().getServletPath();
            servletPath = servletPath.endsWith("/") ? servletPath : servletPath + "/";
            if (servletPath.startsWith(url)) {
                ruleConf = true;
                break;
            }
        }

        return (ruleConf ||
                controller.getClass().isAnnotationPresent(LoginRequired.class) ||
                method.isAnnotationPresent(LoginRequired.class)) &&
                !method.isAnnotationPresent(LoginClear.class);
    }

    /**
     * 将登录前被拦截的url保存到session中
     *
     * @param controller
     */
    private void saveUrlBeforeLoginToSession(Controller controller) {
        String urlBeforeLogin = controller.getRequest().getServletPath();
        controller.setSessionAttr(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN, urlBeforeLogin);
    }

    /**
     * 清除保存的登陆前访问的url
     *
     * @param controller
     */
    private void clearUrlBeforeLoginInfo(Controller controller) {
        if (controller.getSessionAttr(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN) != null)
            controller.removeSessionAttr(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN);
    }

    /**
     * 验证是否登陆
     *
     * @param controller
     * @return
     */
    private boolean hasLogged(Controller controller) {
        Subject subject = SecurityKit.getSubject(controller);
        return subject.isLogin();
    }
}
