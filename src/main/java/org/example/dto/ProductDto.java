package org.example.dto;

import lombok.Builder;
import lombok.Value;
import org.example.entity.Role;

import java.math.BigDecimal;

@Value
@Builder
public class ProductDto {
    Integer id;
    String name;
    String description;
    BigDecimal price;
    Boolean isAvailable;
}
