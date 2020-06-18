package com.dhcc.sso.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "T_ROLE")
public class Role implements Serializable {
    private static final long serialVersionUID = -9006688092480040230L;

    private Long roleId;
    private String roleName;
    private String roleDesc;
    private Long priority;
    private String status;
    private LocalDateTime createDate;
    private String creator;

    @Id
    @Column(name = "ROLE_ID", nullable = false, precision = 0)
    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "ROLE_NAME", nullable = false, length = 200)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ROLE_DESC", nullable = true, length = 2000)
    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    @Basic
    @Column(name = "PRIORITY", nullable = true, precision = 0)
    public Long getPriority() {
        return priority;
    }

    public void setPriority(Long priority) {
        this.priority = priority;
    }

    @Basic
    @Column(name = "STATUS", nullable = true, length = 10)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "CREATE_DATE", nullable = true)
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Basic
    @Column(name = "CREATOR", nullable = true, length = 200)
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
        Role role = (Role) o;
        return Objects.equals(roleId, role.roleId) &&
                   Objects.equals(roleName, role.roleName) &&
                   Objects.equals(roleDesc, role.roleDesc) &&
                   Objects.equals(priority, role.priority) &&
                   Objects.equals(status, role.status) &&
                   Objects.equals(createDate, role.createDate) &&
                   Objects.equals(creator, role.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleDesc, priority, status, createDate, creator);
    }
}
