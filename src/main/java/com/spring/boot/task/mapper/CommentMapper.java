package com.spring.boot.task.mapper;

import com.spring.boot.task.dto.RecordDto;
import com.spring.boot.task.model.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private final ProductMapper productMapper;
    private final UserMapper userMapper;

    public CommentMapper(ProductMapper productMapper, UserMapper userMapper) {
        this.productMapper = productMapper;
        this.userMapper = userMapper;
    }

    public Comment map(RecordDto recordDto) {
        Comment comment = new Comment();
        comment.setUser(userMapper.map(recordDto));
        comment.setProduct(productMapper.map(recordDto));
        comment.setHelpfulnessNumerator(recordDto.getHelpfulnessNumerator());
        comment.setHelpfulnessDenominator(recordDto.getHelpfulnessDenominator());
        comment.setScore(recordDto.getScore());
        comment.setTime(recordDto.getTime());
        comment.setSummary(recordDto.getSummary());
        comment.setText(recordDto.getText());
        return comment;
    }
}
