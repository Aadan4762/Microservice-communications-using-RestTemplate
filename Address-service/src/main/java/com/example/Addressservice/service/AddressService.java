package com.example.Addressservice.service;

import com.example.Addressservice.entity.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    Address createAddress(Address address);
    List<Address>getAllAddress();
    Address getAddressById(int id);

    Address updateAddress(int id,Address address );
    void deleteAddressById(int id);

}
