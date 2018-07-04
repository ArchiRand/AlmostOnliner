package soso.production.service;

import soso.production.model.Phone;
import soso.production.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("phoneService")
public class PhoneService implements IPhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    @Override
    public Phone save(Phone phone) {
        return phoneRepository.save(phone);
    }

    @Override
    public Phone getPhoneByUserEmail(String email) {
        return phoneRepository.getPhoneByPhoneOwner_Email(email);
    }
}
