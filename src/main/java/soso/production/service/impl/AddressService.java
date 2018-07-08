package soso.production.service.impl;

import soso.production.model.Address;
import soso.production.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soso.production.service.interfaces.IAddressService;


@Transactional
@Service("addressService")
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address getAddressByUserEmail(String email) {
        return addressRepository.getAddressByAddressOwner_Email(email);
    }

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }
}
