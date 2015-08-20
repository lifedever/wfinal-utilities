package io.github.gefangshuai.wfinal.security.core;

/**
 * 授权信息
 * Created by gefangshuai on 2015/7/21.
 */
public class Subject<T> {
    private T t;    // 登录信息
    private boolean login = false;    // 是否登录
    private String[] permissions;            // 与权限相关的Role
    private String homeUrl = "/";             // 当前用户的主页
    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }


    public String[] getPermissions() {
        return permissions;
    }

    public void setPermissions(String[] permissions) {
        this.permissions = permissions;
    }

    public Subject(T t, boolean login, String[] permissions) {
        this.t = t;
        this.login = login;
        this.permissions = permissions;
    }

    public Subject(T t, boolean login) {
        this.t = t;
        this.login = login;
    }

    public Subject(T t, boolean login, String[] permissions, String homeUrl) {
        this.t = t;
        this.login = login;
        this.permissions = permissions;
        this.homeUrl = homeUrl;
    }

    public Subject(T t) {
        this.t = t;
        if (t != null)
            this.login = true;
    }

    public T get() {
        return t;
    }

    public String getHomeUrl() {
        return homeUrl;
    }

    public void setHomeUrl(String homeUrl) {
        this.homeUrl = homeUrl;
    }
}
