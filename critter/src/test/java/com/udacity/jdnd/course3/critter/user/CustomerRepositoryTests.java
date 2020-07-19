package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetType;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@DataJpaTest()
@RunWith(SpringRunner.class)
public class CustomerRepositoryTests {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    public void saveNewCustomerWithPet() {

        Customer customer = new Customer("Tom", "12345678", "This is a test");

        Pet pet = new Pet(PetType.CAT, "Billy", LocalDate.now(),
                "this is a test");

        customer.addNewPet(pet);

        this.customerRepository.save(customer);

        List<Customer> customers = this.customerRepository.findAll();

        Assertions.assertFalse(customers.isEmpty());

        Customer savedCustomer = customers.get(0);

        Assertions.assertEquals(1L, savedCustomer.getId());
        Assertions.assertEquals(customer.getName(), savedCustomer.getName());
        Assertions.assertEquals(customer.getPhoneNumber(), customer.getPhoneNumber());
        Assertions.assertEquals(customer.getNotes(), customer.getNotes());

        List<Pet> pets = savedCustomer.getPets();

        Assertions.assertFalse(pets.isEmpty());

        Pet savedPet = pets.get(0);

        Assertions.assertEquals(pet.getName(), savedPet.getName());
        Assertions.assertEquals(pet.getBirthDate(), savedPet.getBirthDate());
        Assertions.assertEquals(pet.getCustomer(), savedCustomer);
        Assertions.assertEquals(pet.getType(), savedPet.getType());
    }
}
