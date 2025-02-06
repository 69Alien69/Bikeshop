package ru.hackass122.bikeshop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hackass122.bikeshop.model.Role;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role, Long> {
    Optional<Role> findByAuthority(String authority);
}
