package com.learningstuff.stockexchange_application.service;

import com.learningstuff.stockexchange_application.model.Role;
import com.learningstuff.stockexchange_application.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void createRole(Role role)
    {
        roleRepository.save(role);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role findByRole(String role) {

        Optional<Role> Optional = roleRepository.findByRole(role);

        return  Optional.get();
    }
}
