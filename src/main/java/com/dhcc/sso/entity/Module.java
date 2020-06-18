package com.dhcc.sso.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "T_MODULE")
public class Module implements Serializable {
    private static final long serialVersionUID = -3491047505773696559L;

    private Long moduleId;
    private String moduleName;
    private String moduleType;
    private String moduleAction;
    private Long parentId;
    private Long odn;
    private String moduleIcon;
    private String status;
    private LocalDateTime createDate;
    private String creator;

    @Id
    @Column(name = "MODULE_ID", nullable = false, precision = 0)
    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    @Basic
    @Column(name = "MODULE_NAME", nullable = false, length = 200)
    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    @Basic
    @Column(name = "MODULE_TYPE", nullable = false, length = 10)
    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    @Basic
    @Column(name = "MODULE_ACTION", nullable = false, length = 200)
    public String getModuleAction() {
        return moduleAction;
    }

    public void setModuleAction(String moduleAction) {
        this.moduleAction = moduleAction;
    }

    @Basic
    @Column(name = "PARENT_ID", nullable = false, precision = 0)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Basic
    @Column(name = "ODN", nullable = true, precision = 0)
    public Long getOdn() {
        return odn;
    }

    public void setOdn(Long odn) {
        this.odn = odn;
    }

    @Basic
    @Column(name = "MODULE_ICON", nullable = true, length = 200)
    public String getModuleIcon() {
        return moduleIcon;
    }

    public void setModuleIcon(String moduleIcon) {
        this.moduleIcon = moduleIcon;
    }

    @Basic
    @Column(name = "STATUS", nullable = false, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = false)
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATOR", nullable = false, length = 32)
    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
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
                   Objects.equals(odn, module.odn) &&
                   Objects.equals(moduleIcon, module.moduleIcon) &&
                   Objects.equals(status, module.status) &&
                   Objects.equals(createDate, module.createDate) &&
                   Objects.equals(creator, module.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(moduleId, moduleName, moduleType, moduleAction, parentId, odn, moduleIcon, status, createDate, creator);
    }
}
