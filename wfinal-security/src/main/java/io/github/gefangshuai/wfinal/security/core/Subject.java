package io.github.gefangshuai.wfinal.security.core;

/**
 * 授权信息
 * Created by gefangshuai on 2015/7/21.
 */
public class Subject<T> {
    private T t;    // 登录信息
    private boolean login = false;    // 是否登录

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public Subject(T t, boolean login) {
        this.t = t;
        this.login = login;
    }

    public Subject(T t) {
        this.t = t;
        if(t!=null)
            this.login = true;
    }

    public T get() {
        return t;
    }
}
