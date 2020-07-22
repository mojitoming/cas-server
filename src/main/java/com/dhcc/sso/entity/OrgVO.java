package com.dhcc.sso.entity;

import java.io.Serializable;

public class OrgVO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String orgCode; // 机构代码
    private String orgName; // 机构名称
    private String orgOdn; // 机构顺序
    private Integer cisLevel; // 医保结算等级
    private String orgTypeCode; // 机构类型代码
    private String orgTypeName; // 机构类型名称
    private String orgTypeOdn; // 机构类型顺序

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgOdn() {
        return orgOdn;
    }

    public void setOrgOdn(String orgOdn) {
        this.orgOdn = orgOdn;
    }

    public Integer getCisLevel() {
        return cisLevel;
    }

    public void setCisLevel(Integer cisLevel) {
        this.cisLevel = cisLevel;
    }

    public String getOrgTypeCode() {
        return orgTypeCode;
    }

    public void setOrgTypeCode(String orgTypeCode) {
        this.orgTypeCode = orgTypeCode;
    }

    public String getOrgTypeName() {
        return orgTypeName;
    }

    public void setOrgTypeName(String orgTypeName) {
        this.orgTypeName = orgTypeName;
    }

    public String getOrgTypeOdn() {
        return orgTypeOdn;
    }

    public void setOrgTypeOdn(String orgTypeOdn) {
        this.orgTypeOdn = orgTypeOdn;
    }
}
