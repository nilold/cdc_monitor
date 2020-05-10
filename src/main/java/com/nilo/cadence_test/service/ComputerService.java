package com.nilo.cadence_test.service;

import com.nilo.cadence_test.model.Computer;
import com.nilo.cadence_test.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ComputerService implements CrudService<Computer, Integer>{
    @Autowired
    ComputerRepository computerRepository;

    @Override
    public Optional<Computer> findById(Integer id) {
        return computerRepository.findById(id);
    }

    @Override
    public Computer save(Computer computer) {
        return computerRepository.save(computer);
    }

    @Override
    public void delete(Computer computer) {
        computerRepository.delete(computer);
    }

    @Override
    public void deleteById(Integer id) {
        computerRepository.deleteById(id);
    }
}
