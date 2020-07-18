package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.io.Serializable;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name="employees")
public class Employee implements Serializable {

    @ElementCollection(targetClass = EmployeeSkill.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_skill")
    @Column(name = "skill")
    private Set<EmployeeSkill> employeeSkillSet;

    @ElementCollection(targetClass = DayOfWeek.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "employee_schedule")
    @Column(name = "day_available")
    private Set<DayOfWeek> daysAvalable;

    public Employee(Set<EmployeeSkill> employeeSkillSet, Set<DayOfWeek> daysAvalable) {
        this.employeeSkillSet = employeeSkillSet;
        this.daysAvalable = daysAvalable;
    }

    public Set<EmployeeSkill> getEmployeeSkillSet() {
        return employeeSkillSet;
    }

    public void setEmployeeSkillSet(Set<EmployeeSkill> employeeSkillSet) {
        this.employeeSkillSet = employeeSkillSet;
    }

    public Set<DayOfWeek> getDaysAvalable() {
        return daysAvalable;
    }

    public void setDaysAvalable(Set<DayOfWeek> daysAvalable) {
        this.daysAvalable = daysAvalable;
    }
}
