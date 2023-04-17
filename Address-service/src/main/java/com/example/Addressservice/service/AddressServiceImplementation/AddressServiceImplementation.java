package com.example.Addressservice.service.AddressServiceImplementation;

import com.example.Addressservice.entity.Address;
import com.example.Addressservice.exception.ResourceNotFoundException;
import com.example.Addressservice.repository.AddressRepository;
import com.example.Addressservice.service.AddressService;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImplementation implements AddressService {

    private AddressRepository addressRepository;

    public AddressServiceImplementation(AddressRepository addressRepository) {
        super();
        this.addressRepository = addressRepository;
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(int id) {
        Optional<Address> address = addressRepository.findById(id);
        if (address.isPresent()){
            return address.get();
        }else {
            throw new ResourceNotFoundException("Address", "id",id);
        }
    }

    @Override
    public Address updateAddress(int id, Address address) {
        Address existingAddress = addressRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Address", "id", id));
        existingAddress.setZip(address.getZip());
        existingAddress.setState(address.getState());
        existingAddress.setLane1(address.getLane1());
        existingAddress.setLane2(address.getLane2());
        return addressRepository.save(existingAddress);
    }

    @Override
    public void deleteAddressById(int id) {
        Address address = addressRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Address", "id", id));
        addressRepository.deleteById(id);

    }
}
