package io.github.gefangshuai.wfinal.model.core;


import com.google.common.collect.Lists;
import com.jfinal.ext.kit.ClassSearcher;
import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gefangshuai on 2015/7/7.
 */
public class ModelMapper {
    private List<Class<? extends Model>> excludeClasses = Lists.newArrayList();
    private List<String> includeJars = Lists.newArrayList();
    private boolean includeAllJarsInLib = false;
    private List<String> scanPackages = Lists.newArrayList();

    private static ModelMapper instance;

    private Logger logger = Logger.getLogger(ModelMapper.class);

    public static synchronized ModelMapper getInstance() {
        if (instance == null) {
            instance = new ModelMapper();
        }
        return instance;
    }

    private ModelMapper() {
        scanModels();
    }

    /**
     * 扫描所有的model类
     */
    private void scanModels(){
        List<Class<? extends Model>> modelClasses = ClassSearcher.of(Model.class).scanPackages(scanPackages).injars(includeJars).includeAllJarsInLib(includeAllJarsInLib).search();

        TableBind tb;
        for (Class modelClass : modelClasses) {
            if (excludeClasses.contains(modelClass)) {
                continue;
            }
            tb = (TableBind) modelClass.getAnnotation(TableBind.class);
            if (tb == null) {
                continue;
            } else {
                modelMap.put(modelClass, tb);
            }
        }
        logger.debug("model mapper inited: " + modelMap.size());
    }

    private Map<Class, TableBind> modelMap = new HashMap<>();

    public TableBind getTable(Class<? extends Model> clazz) {
        return modelMap.get(clazz);
    }

}
