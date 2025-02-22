package com.nilo.cadence_test.model.id;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserComputerId implements Serializable {
    @Column(name = "user_id")
    Integer userId;

    @Column(name = "computer_id")
    Integer computerId;

    public UserComputerId() {
    }

    public UserComputerId(Integer userId, Integer computerId) {
        this.userId = userId;
        this.computerId = computerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getComputerId() {
        return computerId;
    }

    public void setComputerId(Integer computerId) {
        this.computerId = computerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserComputerId that = (UserComputerId) o;
        return userId.equals(that.userId) &&
                computerId.equals(that.computerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, computerId);
    }
}
