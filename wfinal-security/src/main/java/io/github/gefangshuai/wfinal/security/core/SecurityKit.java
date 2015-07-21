package io.github.gefangshuai.wfinal.security.core;

import io.github.gefangshuai.wfinal.security.exception.NoCallLoginMethodException;
import io.github.gefangshuai.wfinal.security.proxy.LoginValidateProxy;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpSession;

/**
 * 贯穿整个安全框架的工具类
 * Created by gefangshuai on 2015/7/20.
 */
public class SecurityKit {

    /**
     * 用户登录信息存储
     */
    public static boolean login(LoginValidateProxy loginValidateProxy, HttpSession session) {
        Subject subject = loginValidateProxy.loginCheck();
        if (subject != null && subject.isLogin()) {    // 登录成功，将信息保存到session
            session.setAttribute(SecurityConst.SECURITY_SESSION_SUBJECT_KEY, subject);
            return true;
        } else {  // 登录失败，返回false
            return false;
        }
    }

    /**
     * 用户退出
     */
    public static void logout(HttpSession session){
        session.removeAttribute(SecurityConst.SECURITY_SESSION_SUBJECT_KEY);
    }

    /**
     * 获取登录用户信息
     *
     * @param session
     * @return
     */
    public static Subject getSubject(HttpSession session) {
        Subject subject = (Subject) session.getAttribute(SecurityConst.SECURITY_SESSION_SUBJECT_KEY);
        if (subject == null)
            return new Subject(null, false);
        return subject;
    }

    public static String getUrlBeforeLogin(HttpSession session) {
        String urlBeforeLogin = (String) session.getAttribute(SecurityConst.SECURITY_SESSION_URL_BEFORE_LOGIN);
        if(StringUtils.isBlank(urlBeforeLogin))
            throw  new NoCallLoginMethodException();
        return urlBeforeLogin;
    }
}
