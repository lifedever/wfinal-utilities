package io.github.gefangshuai.wfinal.security.plugin;

import com.google.common.collect.Lists;
import com.jfinal.core.Controller;
import com.jfinal.ext.kit.ClassSearcher;
import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.Model;
import io.github.gefangshuai.wfinal.security.annotation.LoginRequired;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by gefangshuai on 2015/7/17.
 */
public class SecurityPlugin implements IPlugin {
    protected final Logger log = Logger.getLogger(getClass());

    private List<Class<? extends Model>> excludeClasses = Lists.newArrayList();
    private List<String> includeJars = Lists.newArrayList();
    private boolean includeAllJarsInLib = false;
    private List<String> scanPackages = Lists.newArrayList();

    public SecurityPlugin() {
    }


    @Override
    public boolean start() {
        log.debug("----init Security Plugin!----");
        scanLoginRequiredAnno();
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }

    //region 扫描各种注解
    /**
     * 扫描 <code>LoginRequired</code> 注解
     */
    private void scanLoginRequiredAnno() {
        List<Class<? extends Controller>> controllerClasses = ClassSearcher.of(Controller.class).scanPackages(scanPackages).injars(includeJars).includeAllJarsInLib(includeAllJarsInLib).search();
        for (Class ctlClass : controllerClasses) {
            if (excludeClasses.contains(ctlClass)) {
                continue;
            }

            if (ctlClass.isAnnotationPresent(LoginRequired.class)) {    // 类级别包含注解，不需要继续

            } else {  // 不包含此注解，再次检查方法上是否有
                Method[] methods = ctlClass.getMethods();
                for (Method method : methods) {
                    if (method.isAnnotationPresent(LoginRequired.class)) {

                    } else {
                        continue;
                    }
                }
            }
        }
    }
    //endregion
}
