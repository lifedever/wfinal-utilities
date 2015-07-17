package io.github.gefangshuai.wfinal.menumapper.plugin;

import com.google.common.collect.Lists;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.ClassSearcher;
import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.Model;
import io.github.gefangshuai.wfinal.menumapper.core.Menu;
import io.github.gefangshuai.wfinal.menumapper.interceptor.MenuMapperInterceptor;
import io.github.gefangshuai.wfinal.menumapper.core.MenuMapper;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 菜单注入扫描插件, 需要结合 {@link MenuMapperInterceptor} 使用
 * @see MenuMapperInterceptor
 * @author gefangshuai
 */
public class MenuMapperPlugin implements IPlugin {
    protected final Logger log = Logger.getLogger(getClass());

    private List<Class<? extends Model>> excludeClasses = Lists.newArrayList();
    private List<String> includeJars = Lists.newArrayList();
    private boolean includeAllJarsInLib = false;
    private List<String> scanPackages = Lists.newArrayList();

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
        List<Class<? extends Controller>> controllerClasses = ClassSearcher.of(Controller.class).scanPackages(scanPackages).injars(includeJars).includeAllJarsInLib(includeAllJarsInLib).search();
        Menu menu;
        for (Class ctlClass : controllerClasses) {
            if (excludeClasses.contains(ctlClass)) {
                continue;
            }

            Method[] methods = ctlClass.getMethods();
            for (Method method : methods) {
                menu = (Menu) method.getAnnotation(Menu.class);
                if (menu == null) { // 方法中没有注解，则继续添加类注解
                    menu = (Menu) ctlClass.getAnnotation(Menu.class);
                    if (menu == null) {
                        continue;
                    } else {
                        MenuMapper.getInstance().getCtrlMap().put(ctlClass, menu);
                    }
                }else {
                    MenuMapper.getInstance().getCtrlMap().put(method, menu);
                }
            }
        }
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }
}
