package com.spring.boot.task.file.reading;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class FileReaderImpl implements FileReader {
    private final CSVReader reader;

    public FileReaderImpl(CSVReader reader) {
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
            return lines;
        } catch (IOException e) {
            throw new RuntimeException("Bad IO operation!");
        } catch (CsvValidationException e) {
            throw new RuntimeException("Can't validate Csv!");
        }
    }
}
