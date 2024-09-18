package com.kokobato.huynhduc.kokobatodemo.services;

import com.kokobato.huynhduc.kokobatodemo.models.Role;
import com.kokobato.huynhduc.kokobatodemo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
}
