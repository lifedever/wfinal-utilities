package io.github.gefangshuai.wfinal.security.core;

/**
 * 安全防护规则, 设置拦截的url，用于全局的配置。跟注解并行，注解优先级高于此配置
 * Created by gefangshuai on 2015/7/27.
 */
public class SecurityRule {
    private String[] filterUrls;    // 要过滤的url
    private String loginUrl;    // 登录url
    private boolean backToLoginPage = false;    // 登录失败是否跳转回登陆页面
    private String subjectKey = SecurityConst.SECURITY_SESSION_SUBJECT_KEY;     // 前台获取登录信息的key

    public SecurityRule() {
    }

    public String[] getFilterUrls() {
        return filterUrls;
    }

    public void setFilterUrls(String[] filterUrls) {
        this.filterUrls = filterUrls;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public boolean isBackToLoginPage() {
        return backToLoginPage;
    }

    public void setBackToLoginPage(boolean backToLoginPage) {
        this.backToLoginPage = backToLoginPage;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }
}
