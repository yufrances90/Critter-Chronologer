package com.udacity.jdnd.course3.critter.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query(value =
            "SELECT * FROM schedules s INNER JOIN employee_schedule es " +
                    "ON es.schedule_id = s.id WHERE es.employee_id = :employeeId",
            nativeQuery = true)
    List<Schedule> findAllByEmployeeId(@Param("employeeId") Long employeeId);

    @Query(value =
            "SELECT * FROM schedules s INNER JOIN pet_schedule ps " +
                    "ON ps.schedule_id = s.id WHERE ps.pet_id = :petId",
            nativeQuery = true)
    List<Schedule> findAllByPetId(@Param("petId") Long petId);

    @Query(value =
            "SELECT * FROM schedules s " +
                    "INNER JOIN pet_schedule ps " +
                    "ON ps.schedule_id = s.id " +
                    "INNER JOIN pets p ON p.id = ps.pet_id " +
                    "WHERE p.customer_id = :customerId",
            nativeQuery = true)
    List<Schedule> findAllByCustomerId(@Param("customerId") Long customerId);
}
