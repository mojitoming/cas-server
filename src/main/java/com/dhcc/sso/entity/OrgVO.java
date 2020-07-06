package com.dhcc.sso.entity;

import java.io.Serializable;

public class OrgVO implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String orgCode;
    private String orgName;
    private String orgOdn;
    private String orgTypeCode;
    private String orgTypeName;
    private String orgTypeOdn;

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
