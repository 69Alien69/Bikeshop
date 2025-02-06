package ru.hackass122.bikeshop.service;

import org.springframework.stereotype.Service;
import ru.hackass122.bikeshop.dao.RoleDAO;
import ru.hackass122.bikeshop.model.Role;
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    @Override
    public Role getRoleByName(String name) {
        name = "ROLE_" + name;
        return roleDAO.findByAuthority(name).get();
    }
}
