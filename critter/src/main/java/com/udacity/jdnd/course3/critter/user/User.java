package com.udacity.jdnd.course3.critter.user;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * Reasons of using MappedSuperClass strategy are:
 * - In this application, it never needs to query customer and employee at
 * the same time -> Not goint to use TABLE_PER_CLASS & JOINED
 * - The behaviors between customer and employee are quite different, so
 * there will a lot of empty fields if using single table. -> Not going to
 * use Single Table
 */
@MappedSuperclass
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }

    public User(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
