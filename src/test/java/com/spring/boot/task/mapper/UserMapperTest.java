package com.spring.boot.task.mapper;

import static org.mockito.Mockito.when;

import com.spring.boot.task.dto.RecordDto;
import com.spring.boot.task.model.Role;
import com.spring.boot.task.model.User;
import com.spring.boot.task.service.RoleService;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Set;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class UserMapperTest {
    private static final String USER_ROLE_NAME = "USER";
    private static UserMapper userMapper;
    private static RecordDto expectedRecordDto;
    private static RecordDto emptyRecordDto;

    @BeforeAll
    public static void beforeClass() {
        emptyRecordDto = new RecordDto();
        RoleService roleService = Mockito.mock(RoleService.class);
        when(roleService.findByName(USER_ROLE_NAME))
                .thenReturn(Role.of(USER_ROLE_NAME));
        userMapper = new UserMapper(roleService);
        expectedRecordDto = new RecordDto();
        expectedRecordDto.setId(1L);
        expectedRecordDto.setProductId("B001E4KFG0");
        expectedRecordDto.setUserId("A3SGXH7AUHU8GW");
        expectedRecordDto.setProfileName("delmartian");
        expectedRecordDto.setHelpfulnessNumerator(1);
        expectedRecordDto.setHelpfulnessDenominator(1);
        expectedRecordDto.setScore(5);
        expectedRecordDto.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1303862400L), ZoneId.systemDefault()));
        expectedRecordDto.setSummary("Good Quality Dog Food");
        expectedRecordDto.setText(
                "I have bought several of the Vitality canned dog food products and have"
                        + " found them all to be of good quality. The product looks more "
                        + "like a stew than a processed meat and it smells better. My "
                        + "Labrador is finicky and she appreciates this product better than "
                        + " most.");
    }

    @Test
    public void mapToUserOK() {
        User user = userMapper.map(expectedRecordDto);
        Assert.assertEquals(expectedRecordDto.getUserId(), user.getExternalId());
        Assert.assertEquals(expectedRecordDto.getProfileName(), user.getProfileName());
    }

    @Test
    public void mapEmptyReviewDto() {
        User actualUser = userMapper.map(emptyRecordDto);
        User expectedUser = new User();
        expectedUser.setPassword("1111");
        expectedUser.setRoles(Set.of(Role.of(USER_ROLE_NAME)));
        Assert.assertEquals(expectedUser, actualUser);
    }
}
