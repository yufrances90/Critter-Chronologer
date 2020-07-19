package com.udacity.jdnd.course3.critter.user;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer extends User {

    private String phoneNumber;
    private String notes;

    public Customer(long id, String name) {
        super(id, name);
    }

    public Customer(long id, String name, String phoneNumber, String notes) {

        super(id, name);
        
        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }
}
