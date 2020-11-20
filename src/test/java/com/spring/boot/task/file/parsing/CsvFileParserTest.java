package com.spring.boot.task.file.parsing;

import com.opencsv.CSVReader;
import com.spring.boot.task.dto.RecordDto;
import com.spring.boot.task.file.reading.CsvFileReaderImpl;
import com.spring.boot.task.file.reading.FileReader;
import java.io.FileNotFoundException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvFileParserTest {
    private static final String OK_PATH = "src/test/resources/Test.csv";
    private static final String BAD_FORMAT = "src/test/resources/BadFormat.csv";

    @Autowired
    private FileParser<RecordDto> parser;

    @Test
    void isParseOK() throws FileNotFoundException {
        RecordDto recordDto = new RecordDto();
        recordDto.setId(1L);
        recordDto.setProductId("B001E4KFG0");
        recordDto.setUserId("A3SGXH7AUHU8GW");
        recordDto.setProfileName("delmartian");
        recordDto.setHelpfulnessNumerator(1);
        recordDto.setHelpfulnessDenominator(1);
        recordDto.setScore(5);
        recordDto.setTime(LocalDateTime.ofInstant(Instant.ofEpochSecond(
                1303862400L), ZoneId.systemDefault()));
        recordDto.setSummary("Good Quality Dog Food");
        recordDto.setText("I have bought several of the Vitality canned dog food products"
                + " and have found them all to be of good quality. The product looks more "
                + "like a stew than a processed meat and it smells better. My Labrador is "
                + "finicky and she appreciates this product better than  most.");
        FileReader reader = new CsvFileReaderImpl(new CSVReader(new java.io.FileReader(OK_PATH)));
        String[] line = reader.read().get(0);
        Assert.assertEquals(recordDto, parser.parse(String.join(",", line)));
    }

    @Test
    public void badFormatParse() throws FileNotFoundException {
        FileReader reader = new CsvFileReaderImpl(
                new CSVReader(new java.io.FileReader(BAD_FORMAT)));
        Assert.assertThrows(RuntimeException.class,
                () -> parser.parse(String.join(",", reader.read().get(0))));
    }
}
