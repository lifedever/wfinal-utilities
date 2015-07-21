package io.github.gefangshuai.wfinal.security.core;

/**
 * 安全框架有关的常量
 * Created by gefangshuai on 2015/7/17.
 */
public final class SecurityConst {
    // 保存登录用户信息的session key
    public static final String SECURITY_SESSION_SUBJECT_KEY = "security_session_subject_key";
    // 保存用户登录前访问的url信息，用于登录后重定向
    public static final String SECURITY_SESSION_URL_BEFORE_LOGIN = "security_session_url_before_login";
}
