package com.dhcc.sso.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "T_USER")
public class User implements Serializable {
    private static final long serialVersionUID = 8331512426156411953L;

    private Long userId;
    private String username;
    private String password;
    private String nickname;
    private String salt;
    private LocalDateTime warrantStartDate;
    private LocalDateTime warrantEndDate;
    private String status;
    private LocalDateTime createDate;
    private String creator;

    @Id
    @Column(name = "USER_ID", nullable = false, precision = 0)
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 200)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 200)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "NICKNAME", nullable = false, length = 200)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "SALT", nullable = false, length = 200)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Basic
    @Column(name = "WARRANT_START_DATE", nullable = true)
    public LocalDateTime getWarrantStartDate() {
        return warrantStartDate;
    }

    public void setWarrantStartDate(LocalDateTime warrantStartDate) {
        this.warrantStartDate = warrantStartDate;
    }

    @Basic
    @Column(name = "WARRANT_END_DATE", nullable = true)
    public LocalDateTime getWarrantEndDate() {
        return warrantEndDate;
    }

    public void setWarrantEndDate(LocalDateTime warrantEndDate) {
        this.warrantEndDate = warrantEndDate;
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
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                   Objects.equals(username, user.username) &&
                   Objects.equals(password, user.password) &&
                   Objects.equals(nickname, user.nickname) &&
                   Objects.equals(salt, user.salt) &&
                   Objects.equals(warrantStartDate, user.warrantStartDate) &&
                   Objects.equals(warrantEndDate, user.warrantEndDate) &&
                   Objects.equals(status, user.status) &&
                   Objects.equals(createDate, user.createDate) &&
                   Objects.equals(creator, user.creator);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, nickname, salt, warrantStartDate, warrantEndDate, status, createDate, creator);
    }
}
