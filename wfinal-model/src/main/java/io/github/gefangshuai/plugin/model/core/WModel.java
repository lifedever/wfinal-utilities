package io.github.gefangshuai.plugin.model.core;

import com.jfinal.ext.plugin.tablebind.TableBind;
import com.jfinal.plugin.activerecord.Model;

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
     *
     * @return
     */
    public String getPkName() {
        return pkName;
    }

    /**
     * 获取当前表名
     *
     * @return
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 自动判断保存还是更新
     *
     * @return
     */
    public boolean saveOrUpdate() {
        if (get(pkName) == null) {
            return save();
        } else {
            return update();
        }
    }

    /**
     * 查询所有记录
     *
     * @return
     */
    public List<M> findAll() {
        return find("select * from " + tableName);
    }

    /**
     * 查询所有记录
     *
     * @param sort 排序
     * @return
     */
    public List<M> findAll(Sort sort) {
        StringBuilder sb = new StringBuilder("select * from " + tableName);
        sb.append(" order by");
        sb.append(" ");
        sb.append(sort.getColumnName());
        sb.append(" ");
        sb.append(sort.getDirection().getDirection());
        return find(sb.toString());
    }
}
