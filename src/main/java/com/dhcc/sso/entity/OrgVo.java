package com.dhcc.sso.entity;

import java.io.Serializable;

public class OrgVo implements Serializable {
    private static final Long serialVersionUID = 1L;

    private String orgCode;
    private String orgName;
    private String orgSeqNo;
    private String orgTypeCode;
    private String orgTypeName;
    private String orgTypeSeqNo;

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

    public String getOrgSeqNo() {
        return orgSeqNo;
    }

    public void setOrgSeqNo(String orgSeqNo) {
        this.orgSeqNo = orgSeqNo;
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

    public String getOrgTypeSeqNo() {
        return orgTypeSeqNo;
    }

    public void setOrgTypeSeqNo(String orgTypeSeqNo) {
        this.orgTypeSeqNo = orgTypeSeqNo;
    }
}
