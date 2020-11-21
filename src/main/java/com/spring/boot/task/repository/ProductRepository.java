package com.spring.boot.task.repository;

import com.spring.boot.task.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
