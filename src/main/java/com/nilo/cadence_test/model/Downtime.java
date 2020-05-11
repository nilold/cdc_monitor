package com.nilo.cadence_test.model;

import com.nilo.cadence_test.model.id.ComputerStartId;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Downtime {
    @EmbeddedId
    ComputerStartId id;

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

    public Downtime() {
    }

    public Downtime(Computer computer) {
        this.computer = computer;
        this.startAt = new Date();
    }

    public ComputerStartId getId() {
        return id;
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
