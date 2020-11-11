package com.spring.boot.task.file.reading;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import com.spring.boot.task.exceptions.NoHeaderException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CsvFileReaderImpl implements FileReader {
    private static final String FILE_HEADER = "Id,ProductId,UserId,ProfileName,"
            + "HelpfulnessNumerator,HelpfulnessDenominator,Score,Time,Summary,Text";
    private final CSVReader reader;

    public CsvFileReaderImpl(CSVReader reader) {
        this.reader = reader;
    }

    @Override
    public List<String[]> read() {
        try (reader) {
            List<String[]> lines = new ArrayList<>();
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                lines.add(nextLine);
            }
            if (isHeaderProvided(lines.get(0))) {
                return lines;
            } else {
                throw new NoHeaderException("File without header!");
            }
        } catch (IOException e) {
            throw new RuntimeException("Bad IO operation!", e);
        } catch (CsvValidationException e) {
            throw new RuntimeException("Can't validate Csv!", e);
        }
    }

    private boolean isHeaderProvided(String[] line) {
        return FILE_HEADER.equals(String.join(",", line));
    }
}
