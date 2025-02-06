package ru.hackass122.bikeshop.dao;


import ru.hackass122.bikeshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}