package com.nilo.cadence_test.service;

import com.nilo.cadence_test.exceptions.NoSessionException;
import com.nilo.cadence_test.exceptions.OpenedSessionException;
import com.nilo.cadence_test.model.*;
import com.nilo.cadence_test.model.id.UserComputerId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MonitorService {
    @Autowired
    UserPermissionService userPermissionService;

    @Autowired
    UserAccessService userAccessService;

    @Autowired
    DowntimeService downtimeService;

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
            userAccessService.assertSessionIsClosed(user.getId(), computer.getId());
        } catch (OpenedSessionException e) {
            e.printStackTrace();
            return false;
        }
        UserAccess userAccess = new UserAccess(user, computer);
        userAccessService.save(userAccess);
        return true;
    }

    /**
     * @param user user with opened session
     * @param computer computer where session is opened
     * @return Session closing date if an opened session was found. Empty otherwise.
     */
    public Optional<Date> closeUserSession(User user, Computer computer){
        Optional<Date> endAt = Optional.empty();

        try {
            userAccessService.assertSessionIsOpened(user.getId(), computer.getId());
        } catch (NoSessionException e) {
            e.printStackTrace();
        }

        Optional<UserAccess> userAccessOptional = userAccessService
                .getLastUserOpenedAccess(user.getId(), computer.getId());

        if(userAccessOptional.isPresent()){
            Date date = new Date();
            userAccessOptional.get().setEndAt(date);
            userAccessService.save(userAccessOptional.get());
            endAt = Optional.of(date);
        }

        return endAt;
    }

    /**
     * @param computer computer to register the start of a downtime
     * @return true if computer had no started downtime, false otherwise.
     * There will be no effect if this function is invoked for a computer with an already started downtime
     */
    public boolean startDowntime(Computer computer){
        if(downtimeService.getCurrentDowntime(computer.getId()).isPresent())
            return false;

        return downtimeService.save(new Downtime(computer)) != null;
    }

    /**
     * @param computer computer which downtime's finished
     * @return Downtime end date if a started downtime was found. Empty otherwise.
     */
    public Optional<Date> finishDownTime(Computer computer){
        Optional<Date> endAt = Optional.empty();

        Optional<Downtime> downtimeOptional = downtimeService.getCurrentDowntime(computer.getId());

        if(downtimeOptional.isPresent()){
            Date date = new Date();
            downtimeOptional.get().setEndAt(date);
            downtimeService.save(downtimeOptional.get());
            endAt = Optional.of(date);
        }

        return endAt;
    }
}