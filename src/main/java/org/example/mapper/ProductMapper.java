package org.example.mapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dto.ProductDto;
import org.example.entity.Product;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class ProductMapper implements Mapper<ProductDto, Product> {

    private static final ProductMapper INSTANCE = new ProductMapper();
    public static ProductMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public ProductDto mapFrom(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .isAvailable(product.getIsAvailable())
                .build();
    }
}
