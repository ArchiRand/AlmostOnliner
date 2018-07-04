package soso.production.service;

import soso.production.model.User;

public interface IUserService {
    User save(User user);
    User getByEmail(String email);
    User getById(Long id);
}
