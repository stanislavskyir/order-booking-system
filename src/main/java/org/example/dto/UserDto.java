package org.example.dto;

import lombok.Builder;
import lombok.Value;
import org.example.entity.Role;

@Value
@Builder
public class UserDto {
    Integer id;
    String name;
    String email;
    Role role;
}