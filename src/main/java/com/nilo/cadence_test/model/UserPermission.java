package com.nilo.cadence_test.model;

import javax.persistence.*;

@Entity
public class UserPermission {
    @EmbeddedId
    UserPermissionId userPermissionId;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name="user_id")
    User user;

    @ManyToOne
    @MapsId("computer_id")
    @JoinColumn(name="computer_id")
    Computer computer;

    String permission;

    public UserPermission() {
    }

    public UserPermission(User user, Computer computer, String permission) {
        this.user = user;
        this.computer = computer;
        this.permission = permission;
    }

    public UserPermissionId getUserPermissionId() {
        return userPermissionId;
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
}
