package io.github.gefangshuai.wfinal.web.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import io.github.gefangshuai.wfinal.web.annotation.Pjax;

/**
 * @author gefangshuai
 *         email: gefangshuai@163.com
 *         webSite: http://wincn.net
 *         weibo: http://weibo.com/gefangshuai | @LifeDever
 *         createDate: 2015/9/5.
 */
public class PjaxInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation inv) {
        if (!"true".equals(inv.getController().getRequest().getHeader("X-PJAX")) && inv.getMethod().isAnnotationPresent(Pjax.class)) {
            Pjax pjax = inv.getMethod().getAnnotation(Pjax.class);
            inv.getController().getResponse().setHeader("X-PJAX-URL", inv.getActionKey());
            inv.getController().renderJsp(pjax.value());
        }
        inv.invoke();
    }
}
