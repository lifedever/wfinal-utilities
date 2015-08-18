package io.github.gefangshuai.wfinal.security.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.JfinalKit;
import com.jfinal.log.Logger;
import io.github.gefangshuai.wfinal.base.utils.SessionKit;
import io.github.gefangshuai.wfinal.security.annotation.AccessClear;
import io.github.gefangshuai.wfinal.security.annotation.AccessPermissions;
import io.github.gefangshuai.wfinal.security.annotation.LoginClear;
import io.github.gefangshuai.wfinal.security.annotation.LoginRequired;
import io.github.gefangshuai.wfinal.security.core.SecurityConst;
import io.github.gefangshuai.wfinal.security.core.SecurityKit;
import io.github.gefangshuai.wfinal.security.core.SecurityRule;
import io.github.gefangshuai.wfinal.security.core.Subject;
import io.github.gefangshuai.wfinal.security.plugin.SecurityPlugin;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 安全拦截器
 * Created by gefangshuai on 2015/7/20.
 */
public class SecurityInterceptor implements Interceptor {
    Logger logger = Logger.getLogger(SecurityInterceptor.class);

    private Set<Class<? extends Controller>> accessPermissionControllers;
    private SecurityRule securityRule;

    private Set<Class<? extends Controller>> getAccessPermissionControllers() {
        if (accessPermissionControllers == null) {
            accessPermissionControllers = new HashSet<>();
            Map<String, Class<? extends Controller>[]> accessPermissionMap = securityRule.getAccessPermissionMap();
            for (Class<? extends Controller>[] classes : accessPermissionMap.values()) {
                for (Class<? extends Controller> clazz : classes) {
                    accessPermissionControllers.add(clazz);
                }
            }
        }
        return accessPermissionControllers;
    }

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        SessionKit.put(controller.getSession());
        Method method = inv.getMethod();
        SecurityPlugin securityPlugin = (SecurityPlugin) JfinalKit.findPlugin(SecurityPlugin.class).get(0);
        securityRule = securityPlugin.getSecurityRule();
        Subject subject = SecurityKit.getSubject(controller);
        controller.setAttr(securityRule.getSubjectKey(), subject);

        if (subject.isLogin() && securityRule.isUseAccessActionFilter()) {
            if (!accessActionCheck(inv)) {
                controller.renderError(403);
            }
        }

        if (needLoginCheck(controller, method)) {   // 需要登录验证
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

        Subject subject = SecurityKit.getSubject(controller);

        // 有清除注解，直接通过校验
        if (method.isAnnotationPresent(AccessClear.class) || controller.getClass().isAnnotationPresent(AccessClear.class))
            return true;

        // 判断subject设置的Permission注解是否通过校验
        if (controller.getClass().isAnnotationPresent(AccessPermissions.class) || method.isAnnotationPresent(AccessPermissions.class)) {
            if (subject.getPermissions() == null)
                return false;
            AccessPermissions anno = controller.getClass().getAnnotation(AccessPermissions.class);
            if (method.isAnnotationPresent(AccessPermissions.class))
                anno = method.getAnnotation(AccessPermissions.class);
            String[] annoPermissions = anno.value();
            for (String permission : annoPermissions) {
                if (Arrays.asList(subject.getPermissions()).contains(permission))
                    return true;
            }
            return false;
        }

        // 当前登录用户是否有全局 AccessPermissionMap 的权限
        if (isThisControllerInAccessPermissions(controller.getClass())) {
            Map<String, Class<? extends Controller>[]> accessPermissionMap = securityRule.getAccessPermissionMap();

            for (String permission : subject.getPermissions()) {
                if (accessPermissionMap.containsKey(permission)) {
                    Class<? extends Controller>[] classes = accessPermissionMap.get(permission);
                    if (Arrays.asList(classes).contains(controller.getClass()))
                        return true;
                }
            }
            return false;
        }

        return true;
    }

    /**
     * 判断当前的Contoller是否被定义在全局的控制列表中
     */
    private boolean isThisControllerInAccessPermissions(Class<? extends Controller> clazz) {
        if (getAccessPermissionControllers().contains(clazz))
            return true;
        return false;
    }

    /**
     * 是否需要登录验证
     *
     * @param controller
     * @param method
     * @return
     */
    private boolean needLoginCheck(Controller controller, Method method) {
        boolean ruleConf = false;
        for (String url : securityRule.getLoginFilterUrls()) {
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
