package com.udacity.jdnd.course3.critter.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    @Query(
            value = "SELECT * FROM Employees e " +
                    "INNER JOIN employee_days_available eda on eda" +
                    ".employee_id = e.id " +
                    "INNER JOIN employee_skills es on es.employee_id = e.id " +
                    "WHERE eda.day IN :daysAvailable AND es.skill IN " +
                    ":employeeSkills",
            nativeQuery = true
    )
    List<Employee> findAllBySkillsAndDaysAvailable(
            @Param("daysAvailable") Set<String> daysAvailable,
            @Param("employeeSkills") Set<String> employeeSkills);
}
