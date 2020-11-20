package com.spring.boot.task.dto;

import java.time.LocalDateTime;
import lombok.Data;

@Data
public class RecordDto {
    private Long id;
    private String productId;
    private String userId;
    private String profileName;
    private int helpfulnessNumerator;
    private int helpfulnessDenominator;
    private int score;
    private LocalDateTime time;
    private String summary;
    private String text;
}
