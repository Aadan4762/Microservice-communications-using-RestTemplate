package com.example.Employeeservice.controller;

import com.example.Employeeservice.dto.AddressDto;
import com.example.Employeeservice.dto.EmployeeDto;
import com.example.Employeeservice.entity.Employee;
import com.example.Employeeservice.service.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v2/employees")
public class EmployeeController {

    @Autowired
   private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/create")
    public ResponseEntity<EmployeeDto> postEmployee(@RequestBody EmployeeDto employeeDto){
        //mapping from dto to Entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);
        Employee employee = employeeService.createEmployee(employeeRequest);
        //mapping from entity to Dto
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return new ResponseEntity<EmployeeDto>(employeeResponse, HttpStatus.CREATED);

    }
    @GetMapping
    List<EmployeeDto> getAllEmployees(){
        return employeeService.getAllEmployees().stream().map(employee -> modelMapper.map(employee, EmployeeDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") int id){

        Employee employee = employeeService.getEmployeeById(id);
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        //RestTemplate call using Address url
        AddressDto addressDto = restTemplate.getForObject("http://localhost:8081/address-app/api/v1/address/{id}", AddressDto.class, id);
        employeeResponse.setAddressDto(addressDto);
        return ResponseEntity.ok().body(employeeResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity <EmployeeDto> updateEmployee(@PathVariable int id, @RequestBody EmployeeDto employeeDto){
        //mapping from dto to Entity
        Employee employeeRequest = modelMapper.map(employeeDto, Employee.class);
        Employee employee = employeeService.updateEmployee(id, employeeRequest);
        //mapping from entity to Dto
        EmployeeDto employeeResponse = modelMapper.map(employee, EmployeeDto.class);
        return ResponseEntity.ok().body(employeeResponse);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable("id") int id){
        employeeService.deleteEmployeeById(id);
        return new ResponseEntity<String>("Employee deleted successfully...!", HttpStatus.OK);
    }
}

