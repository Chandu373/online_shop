package com.chandu.product_service.service;

import com.chandu.product_service.dto.ProductRequest;
import com.chandu.product_service.dto.ProductResponse;
import com.chandu.product_service.mapper.ProductMapper;
import com.chandu.product_service.model.Product;
import com.chandu.product_service.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public Product create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        log.info("create - Product is {} created",product.getId());
        return productRepository.save(product);
    }

    public List<ProductResponse> getAllProducts() {
          return  productRepository. findAll().stream().map(productMapper::productToProductResponse).toList();
    }
}
