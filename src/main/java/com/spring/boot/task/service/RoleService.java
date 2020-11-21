package com.spring.boot.task.service;

import com.spring.boot.task.model.Role;

public interface RoleService extends GenericService<Role> {
    Role findByName(String name);
}
