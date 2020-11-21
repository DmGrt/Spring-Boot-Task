package com.spring.boot.task.service.impl;

import com.spring.boot.task.model.Product;
import com.spring.boot.task.repository.ProductRepository;
import com.spring.boot.task.service.ProductService;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public void saveAll(List<Product> products) {
        repository.saveAll(products);
    }
}
