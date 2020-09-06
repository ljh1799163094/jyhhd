package com.example.jyhhd.entity;

import java.util.Date;

public class SisAlarm {
    private String taTagNo;

    private String taTagNam;

    private String taTagDscr;

    private String unit;

    private String isEnable;

    private String expression;

    private String skillId;

    private String orgId;

    private String locaId;

    private String creator;

    private Date createTime;

    private String updator;

    private Date updateTime;

    private String continuedTime;

    private String sisType;

    private String isCheck;

    public String getTaTagNo() {
        return taTagNo;
    }

    public void setTaTagNo(String taTagNo) {
        this.taTagNo = taTagNo;
    }

    public String getTaTagNam() {
        return taTagNam;
    }

    public void setTaTagNam(String taTagNam) {
        this.taTagNam = taTagNam;
    }

    public String getTaTagDscr() {
        return taTagDscr;
    }

    public void setTaTagDscr(String taTagDscr) {
        this.taTagDscr = taTagDscr;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getLocaId() {
        return locaId;
    }

    public void setLocaId(String locaId) {
        this.locaId = locaId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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

    public String getContinuedTime() {
        return continuedTime;
    }

    public void setContinuedTime(String continuedTime) {
        this.continuedTime = continuedTime;
    }

    public String getSisType() {
        return sisType;
    }

    public void setSisType(String sisType) {
        this.sisType = sisType;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}