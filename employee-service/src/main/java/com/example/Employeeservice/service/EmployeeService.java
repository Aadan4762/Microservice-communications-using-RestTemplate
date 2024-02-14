package com.example.Employeeservice.service;

import com.example.Employeeservice.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    Employee createEmployee(Employee employee);
    List<Employee>getAllEmployees();
    Employee getEmployeeById(int id);
    Employee updateEmployee(int id, Employee employee);
    void deleteEmployeeById(int id);
}
