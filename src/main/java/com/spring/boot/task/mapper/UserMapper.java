package com.spring.boot.task.mapper;

import com.spring.boot.task.dto.RecordDto;
import com.spring.boot.task.model.User;
import com.spring.boot.task.service.RoleService;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private static final String PASSWORD = "1111";
    private static final String USER_ROLE_NAME = "USER";
    private final RoleService roleService;

    public UserMapper(RoleService roleService) {
        this.roleService = roleService;
    }

    public User map(RecordDto recordDto) {
        User user = new User();
        user.setExternalId(recordDto.getUserId());
        user.setProfileName(recordDto.getProfileName());
        user.setPassword(PASSWORD);
        user.setRoles(Set.of(roleService.findByName(USER_ROLE_NAME)));
        return user;
    }
}
