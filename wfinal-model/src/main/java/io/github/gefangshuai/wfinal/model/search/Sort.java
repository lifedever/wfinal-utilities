package io.github.gefangshuai.wfinal.model.search;

/**
 * 排序封装
 * Created by gefangshuai on 2015/7/7.
 */
public class Sort {
    private String columnName;
    private Direction direction;

    public Sort(String columnName, Direction direction) {
        this.columnName = columnName;
        this.direction = direction;
    }

    public Sort() {
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
}
