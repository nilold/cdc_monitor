package com.nilo.cadence_test.repository;

import com.nilo.cadence_test.model.UserPermission;
import com.nilo.cadence_test.model.id.UserComputerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionRepository extends JpaRepository<UserPermission, UserComputerId> {
}
