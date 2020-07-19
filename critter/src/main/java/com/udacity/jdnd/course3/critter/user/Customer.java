package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends User {

    private String phoneNumber;
    private String notes;

    @OneToMany(mappedBy = "customer")
    private List<Pet> pets;

    public Customer() {
    }

    public Customer(String name, String phoneNumber, String notes) {
        super(name);

        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Customer(long id, String name) {
        super(id, name);
    }

    public Customer(long id, String name, String phoneNumber, String notes) {

        super(id, name);

        this.phoneNumber = phoneNumber;
        this.notes = notes;
    }

    public Customer(long id, String name, String phoneNumber, String notes, List<Pet> pets) {
        super(id, name);
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    public void addNewPet(Pet pet) {

        if (this.pets == null) {
             this.pets = new ArrayList<>();
        }

        this.pets.add(pet);

        pet.setCustomer(this);
    }
}
