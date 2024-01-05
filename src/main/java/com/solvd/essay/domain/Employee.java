package com.solvd.essay.domain;

import com.solvd.essay.service.EmployeeService;

import java.util.Date;
import java.util.List;

public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String personalId;
    private Date birthDate;
    private Double salary;

    private List<Employee> employeeList;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public java.sql.Date getBirthDate() {
        return (java.sql.Date) birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", personalId='" + personalId + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                ", employeeList=" + employeeList +
                '}';
    }
}
