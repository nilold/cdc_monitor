package com.nilo.cadence_test.model.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ComputerStartId implements Serializable {
    @Column(name = "compute_id", nullable = false)
    Integer computerId;

    @Column(name = "start_id", nullable = false)
    Long startId;

    public ComputerStartId() {
    }

    public ComputerStartId(Integer computerId, Long startId) {
        this.computerId = computerId;
        this.startId = startId;
    }

    public Integer getComputerId() {
        return computerId;
    }

    public void setComputerId(Integer computerId) {
        this.computerId = computerId;
    }

    public Long getStartId() {
        return startId;
    }

    public void setStartId(Long startId) {
        this.startId = startId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ComputerStartId)) return false;
        ComputerStartId that = (ComputerStartId) o;
        return getComputerId().equals(that.getComputerId()) &&
                getStartId().equals(that.getStartId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getComputerId(), getStartId());
    }
}
