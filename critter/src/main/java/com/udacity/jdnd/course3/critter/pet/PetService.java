package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public PetDTO savePet(PetDTO petDTO) {

        Optional<Customer> optionalCustomer =
                this.customerRepository.findById(petDTO.getOwnerId());

        if (!optionalCustomer.isPresent()) {
            return null;
        }

        Customer customer = optionalCustomer.get();

        Pet pet = new Pet();

        pet.setBirthDate(petDTO.getBirthDate());
        pet.setName(petDTO.getName());
        pet.setNotes(petDTO.getNotes());
        pet.setCustomer(customer);

        Pet savedPet = this.petRepository.save(pet);

        customer.addNewPet(pet);

        this.customerRepository.save(customer);

        petDTO.setId(savedPet.getId());

        return petDTO;
    }

    public PetDTO getPet(long id) {

        Optional<Pet> optionalPet = this.petRepository.findById(id);

        if (!optionalPet.isPresent()) {
            return null;
        }

        Pet pet = optionalPet.get();

        PetDTO petDTO = new PetDTO();

        petDTO.setId(pet.getId());
        petDTO.setOwnerId(pet.getCustomer().getId());
        petDTO.setName(pet.getName());
        petDTO.setType(pet.getType());
        petDTO.setBirthDate(pet.getBirthDate());
        petDTO.setNotes(pet.getNotes());

        return petDTO;
    }

    public List<PetDTO> getPetsByOwner(long ownerId) {
        return this.petRepository.findByCustomerId(ownerId).stream().map(pet -> {

            PetDTO petDTO = new PetDTO();

            petDTO.setNotes(pet.getNotes());
            petDTO.setBirthDate(pet.getBirthDate());
            petDTO.setType(pet.getType());
            petDTO.setName(pet.getName());
            petDTO.setOwnerId(pet.getCustomer().getId());
            petDTO.setId(pet.getId());

            return petDTO;
        }).collect(Collectors.toList());
    }

    public List<PetDTO> getPets() {
        return this.petRepository.findAll().stream().map(pet -> {

            PetDTO petDTO = new PetDTO();

            petDTO.setNotes(pet.getNotes());
            petDTO.setBirthDate(pet.getBirthDate());
            petDTO.setType(pet.getType());
            petDTO.setName(pet.getName());
            petDTO.setOwnerId(pet.getCustomer().getId());
            petDTO.setId(pet.getId());

            return petDTO;
        }).collect(Collectors.toList());
    }
}
