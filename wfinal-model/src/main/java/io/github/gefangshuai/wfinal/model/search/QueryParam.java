package io.github.gefangshuai.wfinal.model.search;

/**
 * 查询参数
 * Created by gefangshuai on 2015/7/9.
 */
public class QueryParam {
    private String column;
    private Operator operator;
    private Object value;

    public QueryParam(String column, Operator operator, Object value) {
        this.column = column;
        this.operator = operator;
        this.value = value;
    }

    public String getColumn() {
        return column;
    }

    public Operator getOperator() {
        return operator;
    }

    public Object getValue() {
        return value;
    }
}