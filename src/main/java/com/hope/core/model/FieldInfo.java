package com.hope.core.model;


import javafx.scene.shape.VLineTo;

/**
 * @author haha
 */
public class FieldInfo {

    private String columnName;
    private String fieldName;
    private String fieldClass;
    private String fieldComment;
    private String length;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldClass() {
        return fieldClass;
    }

    public void setFieldClass(String fieldClass) {
        this.fieldClass = fieldClass;
    }

    public String getFieldComment() {
        return fieldComment;
    }

    public void setFieldComment(String fieldComment) {
        this.fieldComment = fieldComment;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
