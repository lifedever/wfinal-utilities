package io.github.gefangshuai.wfinal.security.core;

import com.google.gson.Gson;
import com.jfinal.core.Controller;
import io.github.gefangshuai.wfinal.security.proxy.LoginValidateProxy;
import org.apache.commons.lang3.StringUtils;

/**
 * 贯穿整个安全框架的工具类
 * Created by gefangshuai on 2015/7/20.
 */
public class SecurityKit {

    /**
     * 用户登录信息存储
     */
    public static boolean login(LoginValidateProxy loginValidateProxy, Controller controller) {
        Subject subject = loginValidateProxy.loginCheck();
        if (subject != null && subject.isLogin()) {    // 登录成功，将信息保存到session
            saveSubjectInfoToSession(controller, subject);
            return true;
        } else {  // 登录失败，返回false
            return false;
        }
    }

    /**
     * 用户退出
     */
    public static void logout(Controller controller) {
        controller.removeSessionAttr(SecurityConst.SECURITY_SESSION_SUBJECT_KEY);
        controller.removeCookie(SecurityConst.SECURITY_SESSION_SUBJECT_KEY);
    }

    /**
     * 获取登录用户信息
     *
     * @return
     */
    public static Subject getSubject(Controller controller) {
        Subject subject = controller.getSessionAttr(SecurityConst.SECURITY_SESSION_SUBJECT_KEY);
        if (subject == null) {
            String subJson = controller.getCookie(SecurityConst.SECURITY_SESSION_SUBJECT_KEY);
            subject = new Gson().fromJson(subJson, Subject.class);
            if (subject == null)
                return new Subject(null, false, null);
        }
        return subject;
    }

    /**
     * 获取登录用户信息
     */
    public static <T> Subject<T> getSubject(Controller controller, Class<T> clazz) {
        Subject<T> subject = getSubject(controller);
        return subject;
    }

    public static String getUrlBeforeLogin(Controller controller) {
        String urlBeforeLogin = controller.getSessionAttr(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN);
        if (StringUtils.isBlank(urlBeforeLogin))
            return "";
        return urlBeforeLogin;
    }

    private static void saveSubjectInfoToSession(Controller controller, Subject subject) {
        controller.setSessionAttr(SecurityConst.SECURITY_SESSION_SUBJECT_KEY, subject);
    }

    private static void saveSubjectInfoToCookie(Controller controller, Subject subject) {
        try {
            Gson gson = new Gson();
            String subjectJson = gson.toJson(subject);
            // 默认7天
            controller.setCookie(SecurityConst.SECURITY_SESSION_SUBJECT_KEY, subjectJson, 604800);
        } catch (Exception e) {
            throw new SecurityException("'subject' is too large to save cookie, please simplify it！");
        }
    }

}
