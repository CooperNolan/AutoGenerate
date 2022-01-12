package com.keepstudy.autogenerate.domain;

import java.util.List;

public class TableInfo {

    private String dbName;
    private String dbRemark;

    private String javaName;
    private String javaName_fwl; // first word low

    private FieldInfo primary;
    private List<FieldInfo> fieldInfoList;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavaName_fwl() {
        return javaName_fwl;
    }

    public void setJavaName_fwl(String javaName_fwl) {
        this.javaName_fwl = javaName_fwl;
    }

    public String getDbRemark() {
        return dbRemark;
    }

    public void setDbRemark(String dbRemark) {
        this.dbRemark = dbRemark;
    }

    public FieldInfo getPrimary() {
        return primary;
    }

    public void setPrimary(FieldInfo primary) {
        this.primary = primary;
    }

    public List<FieldInfo> getFieldInfoList() {
        return fieldInfoList;
    }

    public void setFieldInfoList(List<FieldInfo> fieldInfoList) {
        this.fieldInfoList = fieldInfoList;
    }
}
