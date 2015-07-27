package io.github.gefangshuai.wfinal.security.core;

/**
 * 安全防护规则, 设置拦截的url，用于全局的配置。跟注解并行，注解优先级高于此配置
 * Created by gefangshuai on 2015/7/27.
 */
public class SecurityRule {
    private String[] login;

    public SecurityRule() {
    }

    public String[] getLogin() {
        return login;
    }

    public void setLogin(String[] login) {
        this.login = login;
    }
}
