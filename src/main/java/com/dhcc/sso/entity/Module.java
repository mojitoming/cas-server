package com.dhcc.sso.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_MODULE", schema = "URMS")
public class Module {
    private String moduleId;
    private String moduleName;
    private String moduleType;
    private String moduleAction;
    private String parentId;
    private Long seqNo;
    private String moduleIcon;

    @Id
    @Column(name = "MODULE_ID", nullable = false, length = 32)
    public String getModuleId() {
        return moduleId;
    }

    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    @Basic
    @Column(name = "MODULE_NAME", nullable = false, length = 255)
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "MODULE_TYPE", nullable = false, length = 1)
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "MODULE_ACTION", nullable = true, length = 255)
    public String getModuleAction() {
        return moduleAction;
    }

    public void setModuleAction(String moduleAction) {
        this.moduleAction = moduleAction;
    }

    @Basic
    @Column(name = "PARENT_ID", nullable = false, length = 32)
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "SEQ_NO", nullable = true, precision = 0)
    public Long getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Long seqNo) {
        this.seqNo = seqNo;
    }

    @Basic
    @Column(name = "MODULE_ICON", nullable = true, length = 20)
    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Module module = (Module) o;
        return Objects.equals(moduleId, module.moduleId) &&
                Objects.equals(moduleName, module.moduleName) &&
                Objects.equals(moduleType, module.moduleType) &&
                Objects.equals(moduleAction, module.moduleAction) &&
                Objects.equals(parentId, module.parentId) &&
                Objects.equals(seqNo, module.seqNo) &&
                Objects.equals(moduleIcon, module.moduleIcon);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, moduleName, moduleType, moduleAction, parentId, seqNo, moduleIcon);
    }
}
