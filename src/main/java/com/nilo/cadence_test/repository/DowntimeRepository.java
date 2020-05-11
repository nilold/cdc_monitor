package com.nilo.cadence_test.repository;

import com.nilo.cadence_test.model.Downtime;
import com.nilo.cadence_test.model.id.ComputerStartId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DowntimeRepository extends JpaRepository<Downtime, ComputerStartId> {
    Optional<Downtime> findFirstByComputerIdAndEndAtIsNullOrderByStartAtDesc(Integer computerId);
}
