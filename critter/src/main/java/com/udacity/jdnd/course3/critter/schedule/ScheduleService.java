package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public ScheduleDTO createSchedule(ScheduleDTO scheduleDTO) {

        Schedule schedule = new Schedule();

        schedule.setDate(scheduleDTO.getDate());
        schedule.setActivities(scheduleDTO.getActivities());

        List<Pet> pets =
                this.petRepository.findAllById(scheduleDTO.getPetIds());

        List<Employee> employees =
                (List<Employee>) this.employeeRepository.findAllById(scheduleDTO.getEmployeeIds());

        schedule.setPets(pets);
        schedule.setEmployees(employees);

        Schedule savedSchedule = this.scheduleRepository.save(schedule);

        pets.forEach(pet -> {
            pet.addSchedule(savedSchedule);
        });

        this.petRepository.saveAll(pets);

        employees.forEach(employee -> {
            employee.addSchedule(savedSchedule);
        });

        this.employeeRepository.saveAll(employees);

        scheduleDTO.setId(savedSchedule.getId());

        return scheduleDTO;
    }

    public List<ScheduleDTO> getAllSchedules() {
        return this.scheduleRepository.findAll().stream().map(schedule -> {

            ScheduleDTO scheduleDTO = new ScheduleDTO();

            scheduleDTO.setId(schedule.getId());
            scheduleDTO.setActivities(schedule.getActivities());
            scheduleDTO.setDate(schedule.getDate());
            scheduleDTO.setEmployeeIds(
                    schedule.getEmployees().stream().map(Employee::getId)
                            .collect(Collectors.toList()));
            scheduleDTO.setPetIds(schedule.getPets().stream().map(Pet::getId
            ).collect(Collectors.toList()));

            return scheduleDTO;
        }).collect(Collectors.toList());
    }
}
