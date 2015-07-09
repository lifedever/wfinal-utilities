package io.github.gefangshuai.wfinal.model.core;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import io.github.gefangshuai.wfinal.model.search.PageRequest;
import io.github.gefangshuai.wfinal.model.search.QueryMap;
import io.github.gefangshuai.wfinal.model.search.Sort;

import java.util.List;

/**
 * 扩展jfinal model功能
 * Created by gefangshuai on 2015/7/7.
 */
public class WModel<M extends Model> extends Model<M> {
    private String pkName;
    private String tableName;

    public WModel() {
        TableBind tb = ModelMapper.getInstance().getTable(getClass());
        if (tb != null) {
            pkName = tb.pkName();
            tableName = tb.tableName();
        }
    }

    /**
     * 获取当前表主键
     */
    public String getPkName() {
        return pkName;
    }

    /**
     * 获取当前表名
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 自动判断保存还是更新
     */
    public boolean saveOrUpdate() {
        M m = findById(get(pkName));
        if (m == null) {
            return save();
        } else {
            return update();
        }
    }

    /**
     * 查询所有记录
     */
    public List<M> findAll() {
        return find(new QueryMap(null).getQuerySql(this));
    }

    /**
     * 查询所有记录
     */
    public List<M> findAll(QueryMap queryMap) {
        return find(queryMap.getQuerySql(this), queryMap.getParas());

    }
    /**
     * 查询所有记录，带排序
     */
    public List<M> findAll(Sort sort) {
        return find(new QueryMap(null).getQuerySql(this, sort));
    }

    /**
     * 查询所有记录，带排序
     */
    public List<M> findAll(QueryMap queryMap, Sort sort) {
        return find(queryMap.getQuerySql(this, sort), queryMap.getParas());
    }


    /**
     * 分页查询所有记录
     */
    public Page<M> pageRecord(PageRequest pageRequest) {
        return paginate(pageRequest.getPageNumber(), pageRequest.getPageSize(), "select * ", new QueryMap(null).getSqlExceptSelect(null, this));
    }

    /**
     * 分页查询所有记录，带排序
     */
    public Page<M> pageRecord(PageRequest pageRequest, Sort sort) {
        return paginate(pageRequest.getPageNumber(), pageRequest.getPageSize(), "select * ", new QueryMap(null).getSqlExceptSelect(null, this, sort));
    }

    /**
     * 分页查询记录，带排序
     */
    public Page<M> pageRecord(PageRequest pageRequest, QueryMap queryMap) {
        return paginate(pageRequest.getPageNumber(), pageRequest.getPageSize(), "select * ", queryMap.getSqlExceptSelect(null, this), queryMap.getParas());
    }

    /**
     * 分页查询记录，带排序
     */
    public Page<M> pageRecord(PageRequest pageRequest, QueryMap queryMap, Sort sort) {
        return paginate(pageRequest.getPageNumber(), pageRequest.getPageSize(), "select * ", queryMap.getSqlExceptSelect(null, this, sort), queryMap.getParas());
    }

    /**
     * 分页查询
     */
    public Page<M> pageRecord(PageRequest pageRequest, String select, String sqlExceptSelect, Object... paras) {
        return paginate(pageRequest.getPageNumber(), pageRequest.getPageSize(), select, sqlExceptSelect, paras);
    }


}
