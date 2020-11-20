package com.spring.boot.task.file.parsing;

import com.opencsv.CSVParser;
import com.spring.boot.task.dto.RecordDto;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class CsvFileParser implements FileParser<RecordDto> {
    private static final Map<String, Integer> FILE_HEADER_MAP = new HashMap<>();

    static {
        FILE_HEADER_MAP.put("Id", 0);
        FILE_HEADER_MAP.put("ProductId", 1);
        FILE_HEADER_MAP.put("UserId", 2);
        FILE_HEADER_MAP.put("ProfileName", 3);
        FILE_HEADER_MAP.put("HelpfulnessNumerator", 4);
        FILE_HEADER_MAP.put("HelpfulnessDenominator", 5);
        FILE_HEADER_MAP.put("Score", 6);
        FILE_HEADER_MAP.put("Time", 7);
        FILE_HEADER_MAP.put("Summary", 8);
        FILE_HEADER_MAP.put("Text", 9);
    }

    @Override
    public RecordDto parse(String[] record) {
        CSVParser parser = new CSVParser();
        RecordDto recordDto = new RecordDto();
        recordDto.setId(Long.parseLong(record[FILE_HEADER_MAP.get("Id")]));
        recordDto.setProductId(record[FILE_HEADER_MAP.get("ProductId")]);
        recordDto.setUserId(record[FILE_HEADER_MAP.get("UserId")]);
        recordDto.setProfileName(record[FILE_HEADER_MAP.get("ProfileName")]);
        recordDto.setHelpfulnessNumerator(Integer.parseInt(
                record[FILE_HEADER_MAP.get("HelpfulnessNumerator")]));
        recordDto.setHelpfulnessDenominator(Integer.parseInt(
                record[FILE_HEADER_MAP.get("HelpfulnessDenominator")]));
        recordDto.setScore(Integer.parseInt(
                record[FILE_HEADER_MAP.get("Score")]));
        recordDto.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                Long.parseLong(record[FILE_HEADER_MAP.get("Time")])), ZoneId.systemDefault()));
        recordDto.setSummary(record[FILE_HEADER_MAP.get("Summary")]);
        recordDto.setText(record[FILE_HEADER_MAP.get("Text")]);
        return recordDto;
    }
}
