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
    protected T model;

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
     * @param attr
     * @param dao
     */
    protected void baseForm(String attr, T dao) {
        Integer id = getParaToInt();
        if (id != null && id != 0) {    // 编辑
            Model model = dao.findById(id);
            setAttr(attr, model);
        }
    }

    /**
     * 获取model前进行的处理
     */
    protected abstract void doBeforeModel();

    /**
     * 保存前进行的处理，可以设置属性默认值
     */
    protected abstract void doBeforeSave();

    protected void baseSave(String redirectUrl, Class<T> modelClass) {
        doBeforeModel();
        model = getModel(modelClass);
        doBeforeSave();
        Integer id = model.getInt("id");
        if (id != null && id != 0) { // 更新操作
            model.update();
        } else {
            model.save();
        }
        if (StringUtils.isNoneBlank(redirectUrl))
            redirect(redirectUrl);
    }

    /**
     * 基本删除记录操作
     *
     * @param dao
     * @param dbKit
     * @param redirectUrl
     */
    protected void baseDelete(T dao, DBKit<T> dbKit, String redirectUrl) {
        Integer id = getParaToInt();
        String ids = getPara("ids");
        if (id != null && id != 0) {
            dao.deleteById(id);
        } else {
            if (StringUtils.isNoneBlank(ids)) {
                dbKit.deleteAll(ids);
            }
        }
        if (StringUtils.isNoneBlank(redirectUrl))
            redirect(redirectUrl);
    }
}
