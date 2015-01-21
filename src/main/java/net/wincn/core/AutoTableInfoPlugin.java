package net.wincn.core;

import com.google.common.collect.Lists;
import com.jfinal.ext.kit.ClassSearcher;
import com.jfinal.log.Logger;
import com.jfinal.plugin.IPlugin;
import com.jfinal.plugin.activerecord.Model;
import net.wincn.utils.TableUtils;

import java.util.List;

/**
 * Created by fangshuai on 2014-12-09-0009.
 */
public class AutoTableInfoPlugin implements IPlugin {
    protected final Logger log = Logger.getLogger(getClass());

    private List<Class<? extends Model>> excludeClasses = Lists.newArrayList();
    private List<String> includeJars = Lists.newArrayList();
    private boolean includeAllJarsInLib = false;
    private List<String> scanPackages = Lists.newArrayList();
    @Override
    public boolean start() {
        List<Class<? extends Model>> modelClasses = ClassSearcher.of(Model.class).scanPackages(scanPackages).injars(includeJars).includeAllJarsInLib(includeAllJarsInLib).search();

        TableInfo tb;
        for (Class modelClass : modelClasses) {
            if (excludeClasses.contains(modelClass)) {
                continue;
            }
            tb = (TableInfo) modelClass.getAnnotation(TableInfo.class);
            if (tb == null) {
                continue;
            } else {
                log.debug("add TableInfo: modelClass: "+ modelClass+" TableInfo: "+tb);
                TableUtils.getInstance().getTableInfoMap().put(modelClass, tb);
            }
        }
        return true;
    }

    @Override
    public boolean stop() {
        return true;
    }
}
