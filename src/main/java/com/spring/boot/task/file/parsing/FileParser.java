package com.spring.boot.task.file.parsing;

public interface FileParser<T> {
    T parse(String[] record);
}
