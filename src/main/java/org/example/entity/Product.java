package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class Product {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Boolean isAvailable;
}
