package com.nilo.cadence_test.service;

import com.nilo.cadence_test.model.UserPermission;
import com.nilo.cadence_test.model.id.UserComputerId;
import com.nilo.cadence_test.repository.UserPermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserPermissionService implements CrudService<UserPermission, UserComputerId>{
    @Autowired
    UserPermissionRepository userPermissionRepository;

    @Override
    public Optional<UserPermission> findById(UserComputerId id) {
        return userPermissionRepository.findById(id);
    }

    @Override
    public UserPermission save(UserPermission userPermission) {
        return userPermissionRepository.save(userPermission);
    }

    @Override
    public void delete(UserPermission userPermission) {
        userPermissionRepository.delete(userPermission);
    }

    @Override
    public void deleteById(UserComputerId id) {
        userPermissionRepository.deleteById(id);
    }
}
