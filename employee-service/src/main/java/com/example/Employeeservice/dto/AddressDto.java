package com.example.Employeeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class AddressDto {
    private int id;
    private String lane1;
    private String lane2;
    private String state;
    private String zip;
}
