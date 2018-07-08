package soso.production.service.impl;

import soso.production.model.User;
import soso.production.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import soso.production.service.interfaces.IUserService;

@Transactional
@Service("userService")
public class UserService implements IUserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            String currentPassword = user.getPassword();
            String encodedPassword = passwordEncoder.encode(currentPassword);
            user.setPassword(encodedPassword);
        }
        return userRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.getByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userRepository.getOne(""+id);
    }
}
