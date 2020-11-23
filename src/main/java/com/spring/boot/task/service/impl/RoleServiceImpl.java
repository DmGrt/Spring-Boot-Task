package com.spring.boot.task.service.impl;

import com.spring.boot.task.model.Role;
import com.spring.boot.task.repository.RoleRepository;
import com.spring.boot.task.service.RoleService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public Role findByName(String name) {
        return repository.findByRoleName(Role.RoleName.valueOf(name));
    }

    @Override
    public Role save(Role role) {
        return repository.save(role);
    }

    @Override
    public void saveAll(List<Role> roles) {
        repository.saveAll(roles);
    }
}
