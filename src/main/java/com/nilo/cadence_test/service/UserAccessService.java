package com.nilo.cadence_test.service;

import com.nilo.cadence_test.model.UserAccess;
import com.nilo.cadence_test.model.UserComputerStartId;
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

    public Optional<UserAccess> getLastUserOpennedAccess(Integer userId, Integer computerId){
        Optional<UserAccess> lastUserAccessOptional = userAccessRepository
                .findFirstByUserIdAndComputerIdOrderByStartAtDesc(userId, computerId);

        if(lastUserAccessOptional.isPresent() && lastUserAccessOptional.get().getEndAt() == null){
            return lastUserAccessOptional;
        }

        return Optional.empty();
    }
}
