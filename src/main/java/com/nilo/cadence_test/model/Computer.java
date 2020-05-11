package com.nilo.cadence_test.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Computer {
    @Id
    @GeneratedValue
    Integer id;

    public Computer() {
    }

    public Integer getId() {
        return id;
    }
}
