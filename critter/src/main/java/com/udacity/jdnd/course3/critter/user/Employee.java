package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.schedule.Schedule;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
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

    @ManyToMany(
            mappedBy = "employees"
    )
    private List<Schedule> schedules;

    public Employee() {
    }

    public Employee(String name, Set<EmployeeSkill> skills) {

        super(name);

        this.skills = skills;
    }

    public Employee(long id, String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        super(id, name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
    }

    public Employee(String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable, List<Schedule> schedules) {
        super(name);
        this.skills = skills;
        this.daysAvailable = daysAvailable;
        this.schedules = schedules;
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule) {

        if (this.schedules == null) {
            this.schedules = new ArrayList<>();
        }

        this.schedules.add(schedule);
    }
}
