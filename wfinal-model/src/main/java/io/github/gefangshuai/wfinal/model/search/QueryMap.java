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
    private List<Link> links = new ArrayList<>();

    public QueryMap(QueryParam queryParam) {
        this.and(queryParam);
    }

    /**
     * and 连接查询
     */
    public QueryMap and(QueryParam queryParam) {
        if(queryParam != null) {
            queryParams.add(queryParam);
            links.add(Link.AND);
        }
        return this;
    }

    /**
     * and 连接查询
     */
    public QueryMap and(String column, Operator operator, Object value) {
        QueryParam queryParam = new QueryParam(column, operator, value);
        queryParams.add(queryParam);
        links.add(Link.AND);
        return this;
    }

    /**
     * or 连接查询
     */
    public QueryMap or(QueryParam queryParam) {
        if(queryParam != null) {
            queryParams.add(queryParam);
            links.add(Link.OR);
        }
        return this;
    }

    /**
     * or 连接查询
     */
    public QueryMap or(String column, Operator operator, Object value) {
        QueryParam queryParam = new QueryParam(column, operator, value);
        queryParams.add(queryParam);
        links.add(Link.OR);
        return this;
    }

    /**
     * 获取查询sql语句
     *
     * @param model
     * @return
     */
    public String getQuerySql(WModel model) {
        String sql = getSqlExceptSelect("select *", model);
        logger.debug("query sql: " + sql);
        return sql;
    }

    public String getQuerySql(WModel model, Sort sort) {
        String sql = appendSort(getQuerySql(model), sort);
        logger.debug("query sql: " + sql);
        return sql;
    }

    /**
     * 获取期望的sql语句
     *
     * @param model
     * @return
     */
    public String getSqlExceptSelect(String select, WModel model) {
        if (StringUtils.isBlank(select))
            select = "";
        StringBuilder sb = new StringBuilder(select + " from " + model.getTableName());
        sb.append(" where 1=1");
        for (int i = 0; i < queryParams.size(); i++) {
            sb.append(" " + links.get(i).getLink() + " " + queryParams.get(i).getColumn());
            sb.append(" ");
            sb.append(queryParams.get(i).getOperator().getOperator());
            sb.append(" ?");
        }
        logger.debug("SqlExceptSelect: " + sb.toString());
        return sb.toString();
    }

    public String getSqlExceptSelect(String select, WModel model, Sort sort) {
        String sql = appendSort(getSqlExceptSelect(select, model), sort);
        logger.debug("query sql: " + sql);
        return sql;
    }

    /**
     * 获取所有查询值
     *
     * @return
     */
    public Object[] getParas() {
        List<Object> paramList = new ArrayList<>();
        for (QueryParam queryParam : queryParams) {
            paramList.add(queryParam.getValue());
        }
        return paramList.toArray();
    }

    /* private method below */
    private String appendSort(String sql, Sort sort) {
        return sql + " order by " + sort.getColumnName() + " " + sort.getDirection().getDirection();
    }
}