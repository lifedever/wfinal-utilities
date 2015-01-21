package net.wincn.core;

import com.jfinal.core.Controller;
import com.jfinal.ext.kit.ModelKit;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Table;
import net.wincn.utils.ObjectUtils;
import net.wincn.utils.StrUtils;
import net.wincn.utils.TableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by fangshuai on 2014-11-09-0009.
 */
public abstract class BaseController<T extends Model> extends Controller {
    protected Map<String, Object> params = new HashMap<>();
    protected Page<T> pageRecords;

    /**
     * 查询并分页
     *
     * @param keyAttrName 查询表单“key”对应要查询的属性
     * @param dbKit
     */
    protected void searchAndPaginate(String keyAttrName, DBKit<T> dbKit) {
        String key = getPara("key", "");
        String sort = getPara("sort", "desc");
        params.put(keyAttrName, key);
        int page = getParaToInt("page", 1);
        pageRecords = dbKit.search(page, params, sort);
        setAttr("sort", StrUtils.getReverseSort(sort));
        setAttr("pageRecords", pageRecords);
        setAttr("key", key);

    }

    /**
     * 返回所有记录，无查询功能
     *
     * @param dbKit
     */
    protected void list(DBKit<T> dbKit) {
        String sort = getPara("sort", "desc");
        List<T> records = dbKit.listRecord(sort);
        setAttr("records", records);
        setAttr("sort", StrUtils.getReverseSort(sort));
    }

    /**
     * 返回所有数据，带查询功能
     *
     * @param keyAttrName 查询表单“key”对应要查询的属性
     * @param dbKit
     */
    protected void list(String keyAttrName, DBKit<T> dbKit) {
        String key = getPara("key", "");
        String sort = getPara("sort", "desc");
        params.put(keyAttrName, key);
        List<T> records = dbKit.search(params, sort);
        setAttr("records", records);
        setAttr("sort", StrUtils.getReverseSort(sort));
        setAttr("key", key);
    }


    private void form(String attr, T dao, Object id) {
        if (id != null && !"0".equals(id.toString())) {    // 编辑
            Model model = dao.findById(id);
            setAttr(attr, model);
        }
    }

    /**
     * 普通Form跳转
     *
     * @param attr 表单属性
     * @param dao
     */
    protected void form(String attr, T dao) {
        Object id = ObjectUtils.getRealTypeValue(getPara(), TableUtils.getPkType(dao.getClass()));
        form(attr, dao, id);
    }

    /**
     * 获取model前进行的处理，如无处理，请默认返回true，否则不会保存数据库
     */
    protected abstract boolean doAfterGetModel(T model);

    /**
     * 没有保存成功要进行的处理（与doAfterGetModel相关）
     *
     * @param model
     */
    protected abstract void doIfNoSave(T model);

    /**
     * 基础保存
     *
     * @param redirectUrl 重定向的url，为空则不做任何操作.(具有doAfterGetModel功能)
     * @param modelClass
     */
    protected void saveOrUpdate(String redirectUrl, Class<T> modelClass) {
        T model = getModel(modelClass);
        if (doAfterGetModel(model)) {
            saveOrUpdate(model);
            redirectUrl(redirectUrl);
        } else {
            doIfNoSave(model);
        }
    }


    /**
     * 基本的增加或更新封装（仅提供基础功能）,id = pkName
     *
     * @param model
     */
    protected void saveOrUpdate(T model) {
        saveOrUpdate(model, TableUtils.getTableBind(model).pkName());
    }


    /**
     * 保存或更新记录
     *
     * @param model
     * @param pkName 主键的名称
     */
    protected void saveOrUpdate(T model, String pkName) {
        Object id = model.get(pkName);
        if (id != null && !"0".equals(id.toString())) { // 更新操作
            model.update();
        } else {
            model.save();
        }
    }

    private void redirectUrl(String redirectUrl) {
        if (StringUtils.isNoneBlank(redirectUrl))
            redirect(redirectUrl);
    }

    /**
     * 基本删除记录操作
     *
     * @param dao
     * @param redirectUrl 重定向的url，为空则不做任何操作
     */
    protected void delete(T dao, String redirectUrl) {
        Object id = ObjectUtils.getRealTypeValue(getPara(), TableUtils.getPkType(dao.getClass()));
        if (id != null && !"0".equals(id.toString())) {
            dao.deleteById(id);
        }
        redirectUrl(redirectUrl);
    }
}
