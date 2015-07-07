package io.github.gefangshuai.plugin;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;

import java.lang.reflect.Method;

/**
 * 菜单注入拦截器，需要结合 ${@link MenuMapperPlugin} 使用
 * Created by gefangshuai on 2015/7/6.
 */
public class MenuMapperInterceptor implements Interceptor{
    protected final Logger log = Logger.getLogger(getClass());

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        Method method = inv.getMethod();
        String attr = MenuMapper.getInstance().getAttribute();

        Menu menu = MenuMapper.getInstance().getCtrlMap().get(method);
        if(menu != null) {
            controller.setAttr(attr, menu.mapper());
            log.debug("menu class mapper: " + menu.mapper());
        }else {
            menu = MenuMapper.getInstance().getCtrlMap().get(controller.getClass());
            if (menu != null) {
                controller.setAttr(attr, menu.mapper());
                log.debug("menu method mapper: " + menu.mapper());
            }
        }
        inv.invoke();
    }
}
