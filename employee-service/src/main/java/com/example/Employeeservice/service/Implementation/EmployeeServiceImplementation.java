package com.example.Employeeservice.service.Implementation;

import com.example.Employeeservice.entity.Employee;
import com.example.Employeeservice.exception.ResourceNotFoundException;
import com.example.Employeeservice.repository.EmployeeRepository;
import com.example.Employeeservice.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeServiceImplementation(EmployeeRepository employeeRepository) {
        super();
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
       return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            return employee.get();
        }else {
            throw new ResourceNotFoundException("Employee", "id",id);
        }
    }

    @Override
    public Employee updateEmployee(int id, Employee employee) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", "id", id));
        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setBloodgroup(employee.getBloodgroup());
        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployeeById(int id) {
        Employee employee = employeeRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("Employee", "id", id));
        employeeRepository.deleteById(id);

    }
}
