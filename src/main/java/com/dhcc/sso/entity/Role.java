package com.dhcc.sso.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "T_ROLE", schema = "URMS")
public class Role {
    private String roleId;
    private String roleName;
    private String roleDesc;
    private Long priority;
    private String status;

    @Id
    @Column(name = "ROLE_ID", nullable = false, length = 32)
    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "ROLE_NAME", nullable = false, length = 32)
    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Basic
    @Column(name = "ROLE_DESC", nullable = false, length = 32)
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
    @Column(name = "STATUS", nullable = true, length = 1)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
                Objects.equals(status, role.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleId, roleName, roleDesc, priority, status);
    }
}
