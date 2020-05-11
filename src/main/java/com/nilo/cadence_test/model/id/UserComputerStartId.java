package com.nilo.cadence_test.model.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class UserComputerStartId extends UserComputerId {
    @Column(name = "start_id")
    Long startId;

    public UserComputerStartId() {
    }

    public UserComputerStartId(Integer userId, Integer computerId, Long startId) {
        super(userId, computerId);
        this.startId = startId;
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
        if (o == null || getClass() != o.getClass()) return false;
        UserComputerStartId that = (UserComputerStartId) o;
        return userId.equals(that.userId) &&
                computerId.equals(that.computerId) &&
                startId.equals(that.startId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, computerId, startId);
    }
}
