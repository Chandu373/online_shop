package com.chandu.product_service.mapper;


import com.chandu.product_service.dto.ProductResponse;
import com.chandu.product_service.model.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

    public ProductResponse  productToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
