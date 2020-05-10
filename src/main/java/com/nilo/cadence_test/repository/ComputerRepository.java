package com.nilo.cadence_test.repository;

import com.nilo.cadence_test.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerRepository extends JpaRepository<Computer, Integer> {
}
