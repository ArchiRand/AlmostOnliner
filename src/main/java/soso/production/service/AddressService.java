package soso.production.service;

import soso.production.model.Address;
import soso.production.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
