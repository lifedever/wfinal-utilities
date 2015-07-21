package io.github.gefangshuai.wfinal.base.utils;

import javax.servlet.http.HttpSession;

/**
 * Created by gefangshuai on 2015/7/21.
 */
public class SessionKit {
    private static ThreadLocal<HttpSession> tl = new ThreadLocal<>();

    public static void put(HttpSession s) {
        tl.set(s);
    }

    public static HttpSession get() {
        return tl.get();
    }

    public static void remove() {
        tl.remove();
    }
}
