package com.example.Employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EmployeeDto {
    private int id;
    private String name;
    private String email;
    private String bloodgroup;

    private AddressDto addressDto;

}
