package com.nilo.cadence_test.repository;

import com.nilo.cadence_test.model.UserPermission;
import com.nilo.cadence_test.model.UserPermissionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserPermissionRepository extends JpaRepository<UserPermission, UserPermissionId> {
}
