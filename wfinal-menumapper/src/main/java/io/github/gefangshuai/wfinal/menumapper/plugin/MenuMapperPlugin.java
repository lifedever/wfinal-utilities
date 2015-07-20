package io.github.gefangshuai.wfinal.menumapper.plugin;

import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import io.github.gefangshuai.wfinal.menumapper.interceptor.MenuMapperInterceptor;
import io.github.gefangshuai.wfinal.menumapper.core.MenuMapper;


/**
 * 菜单注入扫描插件, 需要结合 {@link MenuMapperInterceptor} 使用
 * @see MenuMapperInterceptor
 * @author gefangshuai
 */
public class MenuMapperPlugin implements IPlugin {
    protected final Logger log = Logger.getLogger(getClass());
    private String attribute;   // response 到前台的菜单对应属性
    private static final String DEFAULT_MENU_ATTR = "menu"; // 默认菜单属性
    public MenuMapperPlugin() {
        this.attribute = DEFAULT_MENU_ATTR;
    }

    public MenuMapperPlugin(String attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean start() {
        log.debug("----init MenuMapper Plugin!----");
        MenuMapper.getInstance().setAttribute(attribute);
        log.debug("MenuMapper attribute: " + attribute);
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }
}
