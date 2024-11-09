package com.chandu.product_service.controller;

import com.chandu.product_service.dto.ProductRequest;
import com.chandu.product_service.dto.ProductResponse;
import com.chandu.product_service.model.Product;
import com.chandu.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Product> create(@RequestBody ProductRequest productRequest){
        Product product = productService.create(productRequest);
        return ResponseEntity.ok(product);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<ProductResponse> productResponseList = productService.getAllProducts();
        return ResponseEntity.ok(productResponseList);
    }
}
