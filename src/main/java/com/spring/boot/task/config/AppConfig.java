package com.spring.boot.task.config;

import com.opencsv.CSVReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${input.file.path}")
    private String inputPath;

    @Bean
    public CSVReader getCsvReader() {
        try {
            return new CSVReader(new FileReader(inputPath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("No such file!", e);
        }
    }
}
