package net.wincn.plugins.menuMapper;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;
import com.jfinal.log.Logger;

/**
 * 菜单注入拦截器，需要结合 ${@link MenuMapperPlugin} 使用
 * Created by gefangshuai on 2015/7/6.
 */
public class MenuMapperInterceptor implements Interceptor{
    protected final Logger log = Logger.getLogger(getClass());

    @Override
    public void intercept(Invocation inv) {
        Controller controller = inv.getController();
        String attr = MenuMapper.getInstance().getAttribute();
        Menu menu = MenuMapper.getInstance().getCtrlMap().get(controller.getClass());
        if(menu != null) {
            controller.setAttr(attr, menu.mapper());
            log.debug("menu mapper: " + menu.mapper());
        }
        inv.invoke();
    }
}
