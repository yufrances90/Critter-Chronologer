package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {

        String customerName = customerDTO.getName();
        String phoneNumber = customerDTO.getPhoneNumber();
        String notes = customerDTO.getNotes();

        Customer customer = new Customer(customerName, phoneNumber, notes);

        Customer savedCustomer = this.customerRepository.save(customer);

        customerDTO.setId(savedCustomer.getId());

        return customerDTO;
    };

    public List<CustomerDTO> getAllCustomers() {

        List<Customer> customers = this.customerRepository.findAll();

        return customers.stream().map(customer -> {

            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setId(customer.getId());
            customerDTO.setName(customer.getName());
            customerDTO.setPhoneNumber(customer.getPhoneNumber());
            customerDTO.setNotes(customer.getNotes());

            List<Pet> pets = customer.getPets();

            if (pets != null) {
                customerDTO.setPetIds(customer.getPets().stream().map(
                        pet -> pet.getId()).collect(Collectors.toList()));
            }

            return customerDTO;
        }).collect(Collectors.toList());
    }

    public CustomerDTO getOwnerByPet(long petId) {

        Optional<Pet> optionalPet = this.petRepository.findById(petId);

        if (optionalPet.isPresent()) {

            Pet pet = optionalPet.get();

            Customer customer = pet.getCustomer();

            CustomerDTO customerDTO = new CustomerDTO();

            customerDTO.setPetIds(
                    customer.getPets().stream().map(
                            pet1 -> pet1.getId()).collect(Collectors.toList()));
            customerDTO.setNotes(customer.getNotes());
            customerDTO.setPhoneNumber(customer.getPhoneNumber());
            customerDTO.setName(customer.getName());
            customerDTO.setId(customer.getId());

            return customerDTO;
        }

        return null;
    }
}
