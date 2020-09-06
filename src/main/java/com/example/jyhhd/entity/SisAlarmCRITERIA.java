package com.example.jyhhd.entity;

import java.util.Date;

public class SisAlarmCRITERIA {
    private String guid;

    private String sisAlarmId;

    private String alarmName;

    private String alarmValue;

    private String alarmExpression;

    private String alarmGrade;

    private Date createTime;

    private String updator;

    private Date updateTime;

    private String alarmDesc;

    private String creator;

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getSisAlarmId() {
        return sisAlarmId;
    }

    public void setSisAlarmId(String sisAlarmId) {
        this.sisAlarmId = sisAlarmId;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public String getAlarmValue() {
        return alarmValue;
    }

    public void setAlarmValue(String alarmValue) {
        this.alarmValue = alarmValue;
    }

    public String getAlarmExpression() {
        return alarmExpression;
    }

    public void setAlarmExpression(String alarmExpression) {
        this.alarmExpression = alarmExpression;
    }

    public String getAlarmGrade() {
        return alarmGrade;
    }

    public void setAlarmGrade(String alarmGrade) {
        this.alarmGrade = alarmGrade;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdator() {
        return updator;
    }

    public void setUpdator(String updator) {
        this.updator = updator;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAlarmDesc() {
        return alarmDesc;
    }

    public void setAlarmDesc(String alarmDesc) {
        this.alarmDesc = alarmDesc;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}