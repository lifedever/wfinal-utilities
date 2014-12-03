package net.wincn.core;

import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import net.wincn.utils.StrUtils;
import org.apache.commons.lang3.StringUtils;

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

    protected void list(DBKit<T> dbKit) {
        String sort = getPara("sort", "desc");
        List<T> records = dbKit.listRecord(sort);
        setAttr("records", records);
        setAttr("sort", StrUtils.getReverseSort(sort));
    }

    /**
     * 普通Form跳转
     *
     * @param attr 表单属性
     * @param dao
     */
    protected void form(String attr, T dao) {
        Integer id = getParaToInt();
        if (id != null && id != 0) {    // 编辑
            Model model = dao.findById(id);
            setAttr(attr, model);
        }
    }

    /**
     * 获取model前进行的处理
     */
    protected abstract boolean doAfterGetModel(T model);

    /**
     * 没有保存成功要进行的处理（与doAfterGetModel相关）
     * @param model
     */
    protected abstract void doIfNoSave(T model);
    /**
     * 基础保存
     * @param redirectUrl 重定向的url，为空则不做任何操作.(具有doAfterGetModel功能)
     * @param modelClass
     */
    protected void saveOrUpdate(String redirectUrl, Class<T> modelClass) {
        T model = getModel(modelClass);
        if(doAfterGetModel(model)) {
            saveOrUpdate(model);
            redirectUrl(redirectUrl);
        }else{
            doIfNoSave(model);
        }
    }

    private void redirectUrl(String redirectUrl){
        if (StringUtils.isNoneBlank(redirectUrl))
            redirect(redirectUrl);
    }

    /**
     * 基本的增加或更新封装（仅提供基础功能）
     * @param model
     */
    protected void saveOrUpdate(T model){
        Integer id = model.getInt("id");
        if (id != null && id != 0) { // 更新操作
            model.update();
        } else {
            model.save();
        }
    }

    /**
     * 基本删除记录操作
     *
     * @param dao
     * @param dbKit
     * @param redirectUrl 重定向的url，为空则不做任何操作
     */
    protected void delete(T dao, DBKit<T> dbKit, String redirectUrl) {
        Integer id = getParaToInt();
        String ids = getPara("ids");
        if (id != null && id != 0) {
            dao.deleteById(id);
        } else {
            if (StringUtils.isNoneBlank(ids)) {
                dbKit.deleteAll(ids);
            }
        }
        redirectUrl(redirectUrl);
    }
}
