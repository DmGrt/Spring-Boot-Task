package com.spring.boot.task.service.impl;

import com.spring.boot.task.model.User;
import com.spring.boot.task.repository.UserRepository;
import com.spring.boot.task.service.UserService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public void saveAll(List<User> users) {
        repository.saveAll(users);
    }
}
