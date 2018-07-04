package soso.production.service;

import soso.production.model.Address;

public interface IAddressService {
    Address getAddressByUserEmail(String email);
    Address save(Address address);
}
