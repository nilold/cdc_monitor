package com.nilo.cadence_test.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserAccess {
    @EmbeddedId
    UserComputerStartId id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne
    @MapsId("computer_id")
    @JoinColumn(name = "computer_id")
    Computer computer;

    @Column(name="start_at")
    @Temporal(TemporalType.TIMESTAMP)
    Date startAt;

    @Column(name="end_at")
    @Temporal(TemporalType.TIMESTAMP)
    Date endAt;


    public UserAccess() {
    }

    public UserAccess(User user, Computer computer) {
        this.user = user;
        this.computer = computer;
        this.startAt = new Date();
    }

    public UserComputerStartId getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Computer getComputer() {
        return computer;
    }

    public Date getStartAt() {
        return startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
    }
}
