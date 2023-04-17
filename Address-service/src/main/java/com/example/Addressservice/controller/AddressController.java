package com.example.Addressservice.controller;

import com.example.Addressservice.dto.AddressDto;
import com.example.Addressservice.entity.Address;
import com.example.Addressservice.service.AddressService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    private AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public ResponseEntity <AddressDto> postAddress(@RequestBody AddressDto addressDto){
        //mapping from dto to Entity
        Address addressRequest = modelMapper.map(addressDto, Address.class);
        Address address = addressService.createAddress(addressRequest);
        //mapping from entity to Dto
        AddressDto addressDtoResponse = modelMapper.map(address, AddressDto.class);
        return new ResponseEntity<AddressDto>(addressDtoResponse, HttpStatus.CREATED);

    }

    @GetMapping
    List<AddressDto> getAllAddresses() {
       return addressService.getAllAddress().stream().map(address -> modelMapper.map(address, AddressDto.class))
               .collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressByUsingId(@PathVariable("id") int id) {
        Address address = addressService.getAddressById(id);
        //mapping from entity to entityDto
        AddressDto addressResponse = modelMapper.map(address, AddressDto.class);
        return ResponseEntity.ok().body(addressResponse);
    }
    @PutMapping("/{id}")
   public ResponseEntity <AddressDto> updateAddress(@PathVariable int id, @RequestBody AddressDto addressDto){
        //mapping from dto to Entity
        Address addressRequest = modelMapper.map(addressDto, Address.class);
        Address address = addressService.updateAddress(id, addressRequest);
        //mapping from entity to Dto
        AddressDto addressResponse = modelMapper.map(address, AddressDto.class);
        return ResponseEntity.ok().body(addressResponse);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddressById(@PathVariable("id") int id){
        addressService.deleteAddressById(id);
        return new ResponseEntity<String>("Address deleted successfully", HttpStatus.OK);
    }
}
