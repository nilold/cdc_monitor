package com.nilo.cadence_test.service;

import com.nilo.cadence_test.exceptions.OpenedSessionException;
import com.nilo.cadence_test.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MonitorService {
    @Autowired
    UserPermissionService userPermissionService;

    @Autowired
    UserAccessService userAccessService;

    public boolean setUserPermission(User user, Computer computer){
        Optional<UserPermission> userPermissionOptional = getUserPermission(user, computer);
        UserPermission userPermission;

        if(userPermissionOptional.isPresent()){
            userPermission = userPermissionOptional.get();
            if(userPermission.isRevoked()){
                userPermission.setRevoked(false);
            }else{
                return false; // nothing to do
            }
        }else{
            userPermission = new UserPermission(user, computer);
        }

        userPermissionService.save(userPermission);
        return true;
    }

    public boolean setAdminUserPermission(User user, Computer computer){
        Optional<UserPermission> userPermissionOptional = getUserPermission(user, computer);
        UserPermission userPermission;

        if(userPermissionOptional.isPresent()){
            userPermission = userPermissionOptional.get();

            if(userPermission.isAdmin()) return false; // nothing to do

            userPermission.setPermission(UserPermission.PERMISSION.ADMIN);
        }else{
            userPermission = new UserPermission(user, computer, UserPermission.PERMISSION.ADMIN);
        }

        userPermissionService.save(userPermission);

        return true;
    }

    public Optional<UserPermission> getUserPermission(User user, Computer computer){
        return userPermissionService.findById(new UserComputerId(user.getId(), computer.getId()));
    }

    /**
     * @param user user who owns the session
     * @param computer computer where the session will start
     * @return false if a session is already opened for this user in this computer. True otherwise.
     */
    public boolean startUserSession(User user, Computer computer) {
        try {
            assertSessionIsClosed(user, computer);
        } catch (OpenedSessionException e) {
            e.printStackTrace();
            return false;
        }
        UserAccess userAccess = new UserAccess(user, computer);
        userAccessService.save(userAccess);
        return true;
    }

    private void assertSessionIsClosed(User user, Computer computer) throws OpenedSessionException {
        Optional<UserAccess> userAccessOptional = userAccessService
                .getLastUserOpennedAccess(user.getId(), computer.getId());
        if(userAccessOptional.isPresent())
            throw new OpenedSessionException(userAccessOptional.get());


    }


}