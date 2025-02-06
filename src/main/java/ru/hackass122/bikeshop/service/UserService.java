package ru.hackass122.bikeshop.service;

import ru.hackass122.bikeshop.model.User;

public interface UserService {
    void saveUser(User user);
    User getUser(long userId);
}
