package com.nilo.cadence_test.service;

import com.nilo.cadence_test.model.Computer;
import com.nilo.cadence_test.model.Downtime;
import com.nilo.cadence_test.model.id.ComputerStartId;
import com.nilo.cadence_test.repository.DowntimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DowntimeService implements CrudService<Downtime, ComputerStartId> {
    @Autowired
    DowntimeRepository downtimeRepository;

    @Override
    public Optional<Downtime> findById(ComputerStartId id) {
        return downtimeRepository.findById(id);
    }

    @Override
    public Downtime save(Downtime downtime) {
        return downtimeRepository.save(downtime);
    }

    @Override
    public void delete(Downtime downtime) {
        downtimeRepository.delete(downtime);
    }

    @Override
    public void deleteById(ComputerStartId id) {
        downtimeRepository.deleteById(id);
    }

    protected Optional<Downtime> getCurrentDowntime(Integer computerId){
        return downtimeRepository.findFirstByComputerIdAndEndAtIsNullOrderByStartAtDesc(computerId);
    }
}
