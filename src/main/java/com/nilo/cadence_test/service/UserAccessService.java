package com.nilo.cadence_test.service;

import com.nilo.cadence_test.exceptions.NoSessionException;
import com.nilo.cadence_test.exceptions.OpenedSessionException;
import com.nilo.cadence_test.model.Computer;
import com.nilo.cadence_test.model.User;
import com.nilo.cadence_test.model.UserAccess;
import com.nilo.cadence_test.model.id.UserComputerStartId;
import com.nilo.cadence_test.repository.UserAccessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccessService implements CrudService<UserAccess, UserComputerStartId>{
    @Autowired
    UserAccessRepository userAccessRepository;

    @Override
    public Optional<UserAccess> findById(UserComputerStartId id) {
        return userAccessRepository.findById(id);
    }

    @Override
    public UserAccess save(UserAccess userAccess) {
        return userAccessRepository.save(userAccess);
    }

    @Override
    public void delete(UserAccess userAccess) {
        userAccessRepository.delete(userAccess);
    }

    @Override
    public void deleteById(UserComputerStartId id) {
        userAccessRepository.deleteById(id);
    }

    protected Optional<UserAccess> getLastUserOpenedAccess(Integer userId, Integer computerId){
        Optional<UserAccess> lastUserAccessOptional = userAccessRepository
                .findFirstByUserIdAndComputerIdOrderByStartAtDesc(userId, computerId);

        if(lastUserAccessOptional.isPresent() && lastUserAccessOptional.get().getEndAt() == null){
            return lastUserAccessOptional;
        }

        return Optional.empty();
    }

    protected void assertSessionIsClosed(Integer userId, Integer computerId) throws OpenedSessionException {
        Optional<UserAccess> userAccessOptional = getLastUserOpenedAccess(userId, computerId);
        if(userAccessOptional.isPresent())
            throw new OpenedSessionException(userAccessOptional.get());
    }

    protected void assertSessionIsOpened(Integer userId, Integer computerId) throws NoSessionException {
        Optional<UserAccess> userAccessOptional = getLastUserOpenedAccess(userId, computerId);
        if(userAccessOptional.isEmpty())
            throw new NoSessionException(userId, computerId);
    }
}
