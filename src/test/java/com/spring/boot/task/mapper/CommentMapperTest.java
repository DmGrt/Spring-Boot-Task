package com.spring.boot.task.mapper;

import static org.mockito.Mockito.when;

import com.spring.boot.task.dto.RecordDto;
import com.spring.boot.task.model.Comment;
import com.spring.boot.task.model.Product;
import com.spring.boot.task.model.Role;
import com.spring.boot.task.model.User;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class CommentMapperTest {
    private static RecordDto fullRecordDto;
    private static RecordDto badRecordDto;
    private static RecordDto emptyCommentDto;
    private static ProductMapper productMapper;
    private static UserMapper userMapper;
    private static CommentMapper commentMapper;

    @BeforeAll
    public static void beforeAll() {
        fullRecordDto = new RecordDto();
        fullRecordDto.setId(1L);
        fullRecordDto.setProductId("B001E4KFG0");
        fullRecordDto.setUserId("A3SGXH7AUHU8GW");
        fullRecordDto.setProfileName("delmartian");
        fullRecordDto.setHelpfulnessNumerator(1);
        fullRecordDto.setHelpfulnessDenominator(1);
        fullRecordDto.setScore(5);
        fullRecordDto.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1303862400L), ZoneId.systemDefault()));
        fullRecordDto.setSummary("Good Quality Dog Food");
        fullRecordDto.setText(
                "I have bought several of the Vitality canned dog food products and have"
                        + " found them all to be of good quality. The product looks more "
                        + "like a stew than a processed meat and it smells better. My "
                        + "Labrador is finicky and she appreciates this product better than "
                        + " most.");

        badRecordDto = new RecordDto();
        badRecordDto.setProductId("B001E4KFG0");
        badRecordDto.setUserId("A3SGXH7AUHU8GW");
        badRecordDto.setProfileName("delmartian");

        emptyCommentDto = new RecordDto();
        productMapper = Mockito.mock(ProductMapper.class);
        userMapper = Mockito.mock(UserMapper.class);
        commentMapper = new CommentMapper(productMapper, userMapper);
    }

    @Test
    public void mappingFullCommentDtoTest() {
        Comment expectedComment = createComment(fullRecordDto);
        expectedComment.setHelpfulnessNumerator(1);
        expectedComment.setHelpfulnessDenominator(1);
        expectedComment.setScore(5);
        expectedComment.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(1303862400L),
                ZoneId.systemDefault()));
        expectedComment.setSummary("Good Quality Dog Food");
        expectedComment.setText(
                "I have bought several of the Vitality canned dog food products and have"
                + " found them all to be of good quality. The product looks more "
                + "like a stew than a processed meat and it smells better. My "
                + "Labrador is finicky and she appreciates this product better than "
                + " most.");

        Comment actualComment = commentMapper.map(fullRecordDto);
        Assert.assertEquals(expectedComment, actualComment);
    }

    @Test
    public void mappingEmptyCommentDtoTest() {
        Comment expectedComment = new Comment();
        Comment actualComment = commentMapper.map(emptyCommentDto);
        Assert.assertEquals(expectedComment, actualComment);
    }

    @Test
    public void mapCommentDtoWithoutCommentFieldTest() {
        Comment expectedComment = createComment(badRecordDto);
        Comment actualComment = commentMapper.map(badRecordDto);
        Assert.assertEquals(expectedComment, actualComment);
    }

    private Comment createComment(RecordDto commentDto) {
        Product product = new Product();
        product.setExternalId("B001E4KFG0");
        when(productMapper.map(commentDto)).thenReturn(product);

        User user = new User();
        user.setPassword("1111");
        user.setRoles(Set.of(Role.of("USER")));
        user.setExternalId("A3SGXH7AUHU8GW");
        user.setProfileName("delmartian");
        when(userMapper.map(commentDto)).thenReturn(user);

        Comment comment = new Comment();
        comment.setProduct(product);
        comment.setUser(user);
        return comment;
    }
}
