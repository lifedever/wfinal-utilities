package io.github.gefangshuai.wfinal.security.plugin;

import com.jfinal.ext.kit.JfinalKit;
import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import io.github.gefangshuai.wfinal.security.core.SecurityConst;
import io.github.gefangshuai.wfinal.security.core.SecurityRule;
import io.github.gefangshuai.wfinal.security.interceptor.SecurityInterceptor;

/**
 * Created by gefangshuai on 2015/7/17.
 */
public class SecurityPlugin implements IPlugin {
    private final Logger log = Logger.getLogger(getClass());
    private String loginUrl;    // 登录url
    private boolean backToLoginPage = false;    // 登录失败是否跳转回登陆页面
    private String subjectKey = SecurityConst.SECURITY_SESSION_SUBJECT_KEY;     // 前台获取登录信息的key
    private SecurityRule securityRule;

    public SecurityPlugin() {

    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public void setBackToLoginPage(boolean backToLoginPage) {
        this.backToLoginPage = backToLoginPage;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }

    public SecurityRule getSecurityRule() {
        return securityRule;
    }

    public void setSecurityRule(SecurityRule securityRule) {
        this.securityRule = securityRule;
    }

    public boolean isBackToLoginPage() {
        return backToLoginPage;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    @Override
    public boolean start() {
        log.debug("----init Security Plugin!----");
        JfinalKit.getInterceptors().addGlobalActionInterceptor(new SecurityInterceptor());
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }


}
