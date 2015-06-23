package net.wincn.core;

import java.util.List;

/**
 * Created by gefangshuai on 2015/6/23.
 */
public class SqlExceptObj {
    private String sql;
    private List<Object> objects;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getObjects() {
        return objects;
    }

    public void setObjects(List<Object> objects) {
        this.objects = objects;
    }
}
