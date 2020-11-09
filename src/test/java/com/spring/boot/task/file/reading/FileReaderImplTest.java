package com.spring.boot.task.file.reading;

import com.opencsv.CSVReader;
import com.spring.boot.task.exceptions.NoHeaderException;
import java.io.FileNotFoundException;
import java.util.List;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FileReaderImplTest {
    private static final String INPUT_PATH_OK = "src/test/resources/Test.csv";
    private static final String INPUT_PATH_WITHOUT_HEADER = "src/test/resources/WithoutHeader.csv";
    private static final String INPUT_PATH_BAD = "TRe.csv";
    private static final int OK_LENGTH = 10;
    private static final String FILE_HEADER = "Id,ProductId,UserId,ProfileName,"
            + "HelpfulnessNumerator,HelpfulnessDenominator,Score,Time,Summary,Text";
    private static final String LINE_FROM_FILE = "1,B001E4KFG0,A3SGXH7AUHU8GW,delmartian,1,1,5,"
            + "1303862400,Good Quality Dog Food,I have bought several of the Vitality canned "
            + "dog food products and have found them all to be of good quality. The product "
            + "looks more like a stew than a processed meat and it smells better. My Labrador "
            + "is finicky and she appreciates this product better than  most.";

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

    @Test
    public void isReadOk() throws FileNotFoundException {
        FileReader reader = new FileReaderImpl(new CSVReader(new java.io.FileReader(INPUT_PATH_OK)));
        List<String[]> resp = reader.read();
        StringBuilder sb = new StringBuilder();
        for (String s: resp.get(1)) {
            sb.append(s).append(",");
        }
        assertEquals(LINE_FROM_FILE, sb.substring(0,sb.length() - 1));
    }

    @Test
    public void fileWithHeader() throws FileNotFoundException {
        FileReader reader = new FileReaderImpl(new CSVReader(new java.io.FileReader(INPUT_PATH_OK)));
        List<String[]> resp = reader.read();
        StringBuilder sb = new StringBuilder();
        for (String s: resp.get(0)) {
            sb.append(s).append(",");
        }
        assertEquals(FILE_HEADER, sb.substring(0,sb.length() - 1));
    }

    @Test
    public void fileWithoutHeader() {
        assertThrows(NoHeaderException.class, () -> {
            new FileReaderImpl(new CSVReader(new java.io.FileReader(INPUT_PATH_WITHOUT_HEADER))).read();
        });
    }
}
