package soso.production.service.interfaces;

import soso.production.model.Address;

public interface IAddressService {
    Address getAddressByUserEmail(String email);
    Address save(Address address);
}
