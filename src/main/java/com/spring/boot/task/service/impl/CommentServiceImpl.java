package com.spring.boot.task.service.impl;

import com.spring.boot.task.model.Comment;
import com.spring.boot.task.repository.CommentRepository;
import com.spring.boot.task.service.CommentService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository repository;

    public CommentServiceImpl(CommentRepository repository) {
        this.repository = repository;
    }

    @Override
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Override
    public void saveAll(List<Comment> comments) {
        repository.saveAll(comments);
    }
}
