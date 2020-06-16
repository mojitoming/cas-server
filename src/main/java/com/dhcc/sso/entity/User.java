package com.dhcc.sso.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "T_USER")
public class User {
    private String userId;
    private String username;
    private String password;
    private String nickname;
    private String salt;
    private LocalDateTime warrantStartDate;
    private LocalDateTime warrantEndDate;
    private String status;

    @Id
    @Column(name = "USER_ID", nullable = false, length = 32)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 32)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "NICKNAME", nullable = false, length = 32)
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Basic
    @Column(name = "SALT", nullable = true, length = 255)
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
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(nickname, user.nickname) &&
                Objects.equals(salt, user.salt) &&
                Objects.equals(warrantStartDate, user.warrantStartDate) &&
                Objects.equals(warrantEndDate, user.warrantEndDate) &&
                Objects.equals(status, user.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, username, password, nickname, salt, warrantStartDate, warrantEndDate, status);
    }
}
