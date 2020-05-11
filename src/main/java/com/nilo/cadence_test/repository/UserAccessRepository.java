package com.nilo.cadence_test.repository;

import com.nilo.cadence_test.model.UserAccess;
import com.nilo.cadence_test.model.UserComputerStartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserAccessRepository extends JpaRepository<UserAccess, UserComputerStartId> {
    List<UserAccess> findByIdUserIdAndComputerId(Integer userId, Integer computer_id);

    Optional<UserAccess> findFirstByUserIdAndComputerIdOrderByStartAtDesc(Integer userId, Integer computerId);
}
