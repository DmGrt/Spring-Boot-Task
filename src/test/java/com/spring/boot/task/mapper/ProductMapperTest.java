package com.spring.boot.task.mapper;

import com.spring.boot.task.dto.RecordDto;
import com.spring.boot.task.model.Product;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ProductMapperTest {
    private static ProductMapper productMapper;
    private static RecordDto expectedRecordDto;
    private static RecordDto emptyRecordDto;

    @BeforeAll
    public static void beforeClass() {
        emptyRecordDto = new RecordDto();
        productMapper = new ProductMapper();
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
    public void mapToProductOK() {
        Product actual = productMapper.map(expectedRecordDto);
        Assert.assertEquals(expectedRecordDto.getProductId(), actual.getExternalId());
    }

    @Test
    public void mapEmptyReviewDto() {
        Product actualProduct = productMapper.map(emptyRecordDto);
        Product expectedProduct = new Product();
        Assert.assertEquals(expectedProduct, actualProduct);
    }
}
