package com.spring.boot.task.mapper;

import com.spring.boot.task.dto.RecordDto;
import com.spring.boot.task.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product map(RecordDto recordDto) {
        Product product = new Product();
        product.setExternalId(recordDto.getProductId());
        return product;
    }
}
