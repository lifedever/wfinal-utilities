package io.github.gefangshuai.wfinal.model.search;

/**
 * Created by gefangshuai on 2015/7/9.
 */
public enum  Operator {
    EQ("="),
    NE("<>"),
    LT("<"),
    LE("<="),
    GT(">"),
    GE(">="),
    LK("like");
    private String operator;

    Operator(String operator){
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }
}
