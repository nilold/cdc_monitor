package com.nilo.cadence_test.model;

import com.nilo.cadence_test.model.id.UserComputerId;

import javax.persistence.*;

@Entity
public class UserPermission {
    @EmbeddedId
    UserComputerId id;
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;
    @ManyToOne
    @MapsId("computer_id")
    @JoinColumn(name = "computer_id")
    Computer computer;
    String permission;
    boolean revoked = false;
    public UserPermission() {
    }

    public UserPermission(User user, Computer computer) {
        this(user, computer, PERMISSION.USER);
    }

    public UserPermission(User user, Computer computer, PERMISSION permission) {
        this.user = user;
        this.computer = computer;
        this.permission = permission.toString();
    }

    public boolean isAdmin() {
        return getPermission().equals(PERMISSION.ADMIN.toString());
    }

    public UserComputerId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Computer getComputer() {
        return computer;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(PERMISSION permission) {
        this.permission = permission.toString();
    }

    public boolean isRevoked() {
        return revoked;
    }

    public void setRevoked(boolean revoked) {
        this.revoked = revoked;
    }

    public enum PERMISSION {
        USER,
        ADMIN
    }
}
