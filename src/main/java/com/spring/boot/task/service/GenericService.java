package com.spring.boot.task.service;

import java.util.List;

public interface GenericService<T> {
    T save(T item);

    void saveAll(List<T> items);
}
