package com.spring.boot.task.repository;

import com.spring.boot.task.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleName(Role.RoleName roleName);
}
