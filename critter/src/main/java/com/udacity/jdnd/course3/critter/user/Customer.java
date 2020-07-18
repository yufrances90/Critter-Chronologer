package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer extends User implements Serializable {

    @Column
    private String notes;

    @OneToMany(mappedBy = "customer")
    private Set<Pet> pets;

    public Customer(long id, @NotNull String name, String phoneNumber, String notes, Set<Pet> pets) {

        super(id, name, phoneNumber);
        this.notes = notes;
        this.pets = pets;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }
}
