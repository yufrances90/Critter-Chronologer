package com.udacity.jdnd.course3.critter.user;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee extends User {

    @ElementCollection(
            targetClass = EmployeeSkill.class,
            fetch = FetchType.EAGER
    )
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="employee_skills")
    @Column(name="skill")
    private Set<EmployeeSkill> skills;

    @ElementCollection(
            targetClass = DayOfWeek.class,
            fetch = FetchType.EAGER
    )
    @Enumerated(EnumType.STRING)
    @CollectionTable(name="employee_days_available")
    @Column(name="day")
    private Set<DayOfWeek> daysAvailable;

    public Employee(long id, String name) {
        super(id, name);
    }

    public Employee(long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        super(id, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}
