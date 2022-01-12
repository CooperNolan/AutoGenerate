package com.cooper.autogenerate.domain;

public class FieldInfo {

    private String dbName;
    private String dbType;
    private String dbRemark;

    private String javaName;
    private String javaName_fwc; // first word capital
    private String javaType;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }


    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getDbRemark() {
        return dbRemark;
    }

    public void setDbRemark(String dbRemark) {
        this.dbRemark = dbRemark;
    }

    public String getJavaName() {
        return javaName;
    }

    public void setJavaName(String javaName) {
        this.javaName = javaName;
    }

    public String getJavaName_fwc() {
        return javaName_fwc;
    }

    public void setJavaName_fwc(String javaName_fwc) {
        this.javaName_fwc = javaName_fwc;
    }

    public String getJavaType() {
        return javaType;
    }

    public void setJavaType(String javaType) {
        this.javaType = javaType;
    }
}
