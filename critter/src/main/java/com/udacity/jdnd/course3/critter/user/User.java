package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    @NotNull
    private String name;

    @Column(name = "phone_number")
    private String phoneNumber;

    @OneToOne(mappedBy = "user")
    private Customer customer;

    public User(long id, @NotNull String name, String phoneNumber, Customer customer) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
