package soso.production.service;

import soso.production.model.Phone;

public interface IPhoneService {
    Phone save(Phone phone);
    Phone getPhoneByUserEmail(String email);
}
