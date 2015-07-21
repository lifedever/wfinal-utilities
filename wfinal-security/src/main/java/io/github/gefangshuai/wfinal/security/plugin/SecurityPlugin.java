package io.github.gefangshuai.wfinal.security.plugin;

import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import io.github.gefangshuai.wfinal.security.core.SecurityConst;

/**
 * Created by gefangshuai on 2015/7/17.
 */
public class SecurityPlugin implements IPlugin {
    private final Logger log = Logger.getLogger(getClass());
    private String loginUrl;    // 登录url
    private boolean backToLoginPage = false;    // 登录失败是否跳转回登陆页面
    private String subjectKey = SecurityConst.SECURITY_SESSION_SUBJECT_KEY;     // 前台获取登录信息的key

    public SecurityPlugin(String loginUrl, boolean backToLoginPage) {
        this.loginUrl = loginUrl;
        this.backToLoginPage = backToLoginPage;
    }

    public SecurityPlugin(String loginUrl, boolean backToLoginPage, String subjectKey) {
        this.loginUrl = loginUrl;
        this.backToLoginPage = backToLoginPage;
        this.subjectKey = subjectKey;
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
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }


}
