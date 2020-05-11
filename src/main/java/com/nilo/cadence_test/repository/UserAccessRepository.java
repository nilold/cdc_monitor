package com.nilo.cadence_test.repository;

import com.nilo.cadence_test.model.UserAccess;
import com.nilo.cadence_test.model.id.UserComputerStartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, UserComputerStartId> {
    Optional<UserAccess> findFirstByUserIdAndComputerIdOrderByStartAtDesc(Integer userId, Integer computerId);
}
