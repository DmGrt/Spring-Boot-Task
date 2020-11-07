package com.spring.boot.task.controllers;

import com.spring.boot.task.file.reading.FileReader;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/read")
public class ReadController {
    private final FileReader reader;

    public ReadController(FileReader reader) {
        this.reader = reader;
    }

    @GetMapping
    public List<String[]> read() {
        return reader.read();
    }
}
