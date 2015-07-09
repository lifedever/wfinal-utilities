package io.github.gefangshuai.wfinal.model.search;

import com.jfinal.log.Logger;
import io.github.gefangshuai.wfinal.model.core.WModel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 查询参数封装
 * Created by gefangshuai on 2015/7/9.
 */
public class QueryMap {
    private Logger logger = Logger.getLogger(QueryMap.class);

    private List<QueryParam> queryParams = new ArrayList<>();

    public QueryMap put(QueryParam queryParam) {
        queryParams.add(queryParam);
        return this;
    }

    public QueryMap put(String colume, Operator operator, Object value) {
        QueryParam queryParam = new QueryParam(colume, operator, value);
        queryParams.add(queryParam);
        return this;
    }

    /**
     * 获取查询sql语句
     * @param model
     * @return
     */
    public String getQuerySql(WModel model) {
        String sql =getSqlExceptSelect("select *",model);
        logger.debug("query sql: " + sql);
        return sql;
    }
    public String getQuerySql(WModel model, Sort sort) {
        String sql = getQuerySql(model) + " order by " + sort.getColumnName() + " " + sort.getDirection().getDirection();
        logger.debug("query sql: " + sql);
        return sql;
    }
    /**
     * 获取期望的sql语句
     * @param model
     * @return
     */
    public String getSqlExceptSelect(String select, WModel model){
        if(StringUtils.isBlank(select))
            select = "";
        StringBuilder sb = new StringBuilder(select + " from " + model.getTableName());
        sb.append(" where 1=1");
        for (QueryParam queryParam : queryParams) {
            sb.append(" and " + queryParam.getColumn());
            sb.append(" ");
            sb.append(queryParam.getOperator().getOperator());
            sb.append(" ?");
        }
        logger.debug("SqlExceptSelect: " + sb.toString());
        return sb.toString();
    }

    /**
     * 获取所有查询值
     * @return
     */
    public Object[] getParas() {
        List<Object> paramList = new ArrayList<>();
        for (QueryParam queryParam : queryParams) {
            paramList.add(queryParam.getValue());
        }
        return paramList.toArray();
    }
}
