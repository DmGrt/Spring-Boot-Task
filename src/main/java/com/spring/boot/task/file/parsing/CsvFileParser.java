package com.spring.boot.task.file.parsing;

import com.opencsv.CSVParser;
import com.spring.boot.task.dto.RecordDto;
import java.io.IOException;
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
    public RecordDto parse(String record) {
        CSVParser parser = new CSVParser();
        RecordDto recordDto = new RecordDto();
        try {
            String[] values = parser.parseLine(record);
            recordDto.setId(Long.parseLong(values[FILE_HEADER_MAP.get("Id")]));
            recordDto.setProductId(values[FILE_HEADER_MAP.get("ProductId")]);
            recordDto.setUserId(values[FILE_HEADER_MAP.get("UserId")]);
            recordDto.setProfileName(values[FILE_HEADER_MAP.get("ProfileName")]);
            recordDto.setHelpfulnessNumerator(Integer.parseInt(
                    values[FILE_HEADER_MAP.get("HelpfulnessNumerator")]));
            recordDto.setHelpfulnessDenominator(Integer.parseInt(
                    values[FILE_HEADER_MAP.get("HelpfulnessDenominator")]));
            recordDto.setScore(Integer.parseInt(
                    values[FILE_HEADER_MAP.get("Score")]));
            recordDto.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                    Long.parseLong(values[FILE_HEADER_MAP.get("Time")])), ZoneId.systemDefault()));
            recordDto.setSummary(values[FILE_HEADER_MAP.get("Summary")]);
            recordDto.setText(values[FILE_HEADER_MAP.get("Text")]);
        } catch (IOException e) {
            throw new RuntimeException("Can't parse record: " + record, e);
        }
        return recordDto;
    }
}
