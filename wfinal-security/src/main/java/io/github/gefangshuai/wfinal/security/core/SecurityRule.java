package io.github.gefangshuai.wfinal.security.core;

import com.jfinal.core.Controller;

import java.util.Map;

/**
 * 安全防护规则, 设置拦截的url，用于全局的配置。跟注解并行，注解优先级高于此配置
 * Created by gefangshuai on 2015/7/27.
 */
public class SecurityRule {
    private String[] loginFilterUrls;    // 要过滤的url
    private String loginUrl;    // 登录url
    private boolean backToLoginPage = false;    // 登录失败是否跳转回登陆页面
    private boolean useAccessActionFilter = false;  // 是否启用用户请求拦截
    private String[] noRedirectUrls;    // 排除登录成功，不进行重定向的地址
    private Map<String, Class<? extends Controller>[]> accessPermissionMap;     // 特定权限对应的请求地址前缀，如: <code>accessPermissionMap.put("admin", new Controller[]{UserController.class, BookController.class})</code>
    private String subjectKey = SecurityConst.SECURITY_SESSION_SUBJECT_KEY;     // 前台获取登录信息的key

    public SecurityRule() {
    }

    public String[] getLoginFilterUrls() {
        return loginFilterUrls;
    }

    /**
     * 设置要过滤的url
     * @param loginFilterUrls
     */
    public void setLoginFilterUrls(String[] loginFilterUrls) {
        this.loginFilterUrls = loginFilterUrls;
    }

    public String getLoginUrl() {
        return loginUrl;
    }

    /**
     * 设置登录页面地址
     * @param loginUrl
     */
    public void setLoginUrl(String loginUrl) {
        this.loginUrl = loginUrl;
    }

    public boolean isBackToLoginPage() {
        return backToLoginPage;
    }

    /**
     * 校验失败是否跳转回登录页面
     * @param backToLoginPage
     */
    public void setBackToLoginPage(boolean backToLoginPage) {
        this.backToLoginPage = backToLoginPage;
    }

    public String getSubjectKey() {
        return subjectKey;
    }

    /**
     * 设置Subject对应的key，默认为 <code>SecurityConst.SECURITY_SESSION_SUBJECT_KEY</code>
     * @param subjectKey
     */
    public void setSubjectKey(String subjectKey) {
        this.subjectKey = subjectKey;
    }

    public boolean isUseAccessActionFilter() {
        return useAccessActionFilter;
    }

    /**
     * 是否开启更细的权限访问
     * @param useAccessActionFilter
     */
    public void setUseAccessActionFilter(boolean useAccessActionFilter) {
        this.useAccessActionFilter = useAccessActionFilter;
    }

    public Map<String, Class<? extends Controller>[]> getAccessPermissionMap() {
        return accessPermissionMap;
    }

    /**
     * 特定权限对应的请求地址前缀，如: <code>accessPermissionMap.put("admin", new Controller[]{"/user","/admin"})</code>
     * @param accessPermissionMap
     */
    public void setAccessPermissionMap(Map<String, Class<? extends Controller>[]> accessPermissionMap) {
        this.accessPermissionMap = accessPermissionMap;
    }

    public String[] getNoRedirectUrls() {
        return noRedirectUrls;
    }

    /**
     * 排除登录成功，不重定向的地址
     * @param noRedirectUrls
     */
    public void setNoRedirectUrls(String[] noRedirectUrls) {
        this.noRedirectUrls = noRedirectUrls;
    }
}
