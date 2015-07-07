package io.github.gefangshuai.plugin.model.core;

/**
 * Created by gefangshuai on 2015/7/7.
 */
public class Sort {
    private String columnName;
    private Direction direction;

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
