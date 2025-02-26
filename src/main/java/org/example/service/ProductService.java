package org.example.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dao.ProductDao;
import org.example.dto.ProductDto;
import org.example.entity.Product;
import org.example.mapper.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductService {
    private static final ProductService INSTANCE = new ProductService();
    public static ProductService getInstance() {
        return INSTANCE;
    }

    private final ProductDao productDao = ProductDao.getInstance();

    public Integer create(Product product) {
        productDao.save(product);
        System.out.println("Successfully created product: " + product);
        return product.getId();
    }

    public List<ProductDto> findAll(){
        System.out.println("Finding all products");
        System.out.println(productDao.findAll());
        return productDao.findAll().stream().map(product ->
                        ProductDto.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .description(product.getDescription())
                                .price(product.getPrice())
                                .isAvailable(product.getIsAvailable())
                                .build())
                .collect(Collectors.toList());
    }

}
