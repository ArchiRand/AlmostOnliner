package soso.production.service.interfaces;

import soso.production.model.Phone;

public interface IPhoneService {
    Phone save(Phone phone);
    Phone getPhoneByUserEmail(String email);
}
