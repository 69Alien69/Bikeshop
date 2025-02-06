package ru.hackass122.bikeshop.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.hackass122.bikeshop.dao.UserDAO;
import ru.hackass122.bikeshop.model.User;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDAO.save(user);
    }

    @Override
    public User getUser(long userId) {
        return userDAO.findById(userId).orElseThrow();
    }
}
