package org.example.mapper;

import org.example.dto.CreateUserDto;
import org.example.entity.Role;
import org.example.entity.User;

public class CreateUserMapper implements Mapper<User, CreateUserDto> {

    private static final CreateUserMapper INSTANCE = new CreateUserMapper();

    private CreateUserMapper() {}

    public static CreateUserMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public User mapFrom(CreateUserDto object) {
        return User.builder()
                .name(object.getName())
                .email(object.getEmail())
                .password(object.getPassword())
                .role(Role.valueOf(object.getRole()))
                .build();
    }
}