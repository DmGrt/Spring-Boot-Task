package com.spring.boot.task.file.reading;

import com.opencsv.CSVReader;
import com.spring.boot.task.exceptions.NoHeaderException;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CsvFileReaderImplTest {
    private static final int OK_LENGTH = 10;
    private static final String INPUT_PATH_OK = "src/test/resources/Test.csv";
    private static final String INPUT_PATH_WITHOUT_HEADER =
            "src/test/resources/WithoutHeader.csv";
    private static final String INPUT_PATH_BAD = "src/test/resources/TRe.csv";
    private static final String LINE_FROM_FILE = "1,B001E4KFG0,A3SGXH7AUHU8GW,"
            + "delmartian,1,1,5,1303862400,Good Quality Dog Food,I have bought "
            + "several of the Vitality canned dog food products and have found "
            + "them all to be of good quality. The product looks more like a stew "
            + "than a processed meat and it smells better. My Labrador is finicky "
            + "and she appreciates this product better than  most.";

    @Test
    public void readWithBadPath() {
        Assert.assertThrows(FileNotFoundException.class, () -> new CsvFileReaderImpl(new CSVReader(new java.io.FileReader(INPUT_PATH_BAD))).read());
    }

    @Test
    public void isReadOk() throws FileNotFoundException {
        FileReader reader = new CsvFileReaderImpl(
                new CSVReader(new java.io.FileReader(INPUT_PATH_OK)));
        List<String[]> resp = reader.read();
        String lineFromFile = String.join(",", resp.get(0));
        Assert.assertEquals(LINE_FROM_FILE, lineFromFile);
        Assert.assertEquals(OK_LENGTH, resp.get(0).length);
    }

    @Test
    public void isHeaderRemoved() throws FileNotFoundException {
        FileReader reader = new CsvFileReaderImpl(
                new CSVReader(new java.io.FileReader(INPUT_PATH_OK)));
        List<String[]> resp = reader.read();
        String firstLine = String.join(",", resp.get(0));
        Assert.assertEquals(LINE_FROM_FILE, firstLine);
    }

    @Test
    public void fileWithoutHeader() {
        Assert.assertThrows(NoHeaderException.class, () -> new CsvFileReaderImpl(new CSVReader(
                new java.io.FileReader(INPUT_PATH_WITHOUT_HEADER))).read());
    }
}
