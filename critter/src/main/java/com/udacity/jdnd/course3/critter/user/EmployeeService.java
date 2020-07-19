package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public EmployeeDTO saveEmployee(EmployeeDTO employeeDTO) {

        String name = employeeDTO.getName();
        Set<EmployeeSkill> skills = employeeDTO.getSkills();

        Employee employee = new Employee(name, skills);

        Employee savedEmployee =  this.employeeRepository.save(employee);

        employeeDTO.setId(savedEmployee.getId());

        return  employeeDTO;
    };

    public EmployeeDTO getEmployee(long id) {

        Employee employee = this.employeeRepository.getOne(id);

        EmployeeDTO employeeDTO = new EmployeeDTO();

        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setDaysAvailable(employee.getDaysAvailable());
        employeeDTO.setSkills(employee.getSkills());

        return employeeDTO;
    }

    public void setAvailability(Set<DayOfWeek> daysAvailable, long employeeId) {

        Optional<Employee> optionalEmployee =
                this.employeeRepository.findById(employeeId);

        if (optionalEmployee.isPresent()) {

            Employee employee = optionalEmployee.get();

            employee.setDaysAvailable(daysAvailable);

            this.employeeRepository.save(employee);
        }
    }
}
