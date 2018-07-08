package soso.production.service.interfaces;

import soso.production.model.User;

public interface IUserService {
    User save(User user);
    User getByEmail(String email);
    User getById(Long id);
}
