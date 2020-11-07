package com.spring.boot.task.file.reading;

import com.opencsv.CSVReader;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileReaderImplTest {
    @Value("${input.file.path}")
    private static final String INPUT_PATH_OK = "src/test/resources/Test.csv";
    private static final String EXPECTED_EX_MESSAGE = "There is no such file!";
    private static final String INPUT_PATH_BAD = "TRe.csv";
    private static final int OK_LENGTH = 10;

    @Test
    public void readWithOkPath() throws FileNotFoundException {
        List<String[]> resp;
        FileReader reader = new FileReaderImpl(new CSVReader(new java.io.FileReader(INPUT_PATH_OK)));
        resp = reader.read();
        Assert.assertEquals(resp.get(0).length, OK_LENGTH);
    }

    @Test
    public void readWithBadPath() {
        assertThrows(FileNotFoundException.class, () -> {
            new FileReaderImpl(new CSVReader(new java.io.FileReader(INPUT_PATH_BAD))).read();
        });
    }
}