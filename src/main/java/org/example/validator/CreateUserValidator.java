package org.example.validator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.example.dto.CreateUserDto;
import org.example.entity.Role;
import org.example.utils.LocalDateFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserValidator implements Validator<CreateUserDto> {
    private final static CreateUserValidator INSTANCE = new CreateUserValidator();

    public ValidationResult isValid(CreateUserDto userDto){
        var validationResult = new ValidationResult();

        if (Role.find(userDto.getRole()).isEmpty()){
            validationResult.add(Error.of("invalid.role", "Role is invalid"));
        }

        if (userDto.getName() == null || userDto.getName().trim().isEmpty()){
            validationResult.add(Error.of("invalid.name", "Name is invalid"));
        }

        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.email", "Email is required"));
        } else if (!userDto.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            validationResult.add(Error.of("invalid.email", "Email format is invalid"));
        }

        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            validationResult.add(Error.of("invalid.password", "Password is required"));
        } else if (userDto.getPassword().length() < 8) {
            validationResult.add(Error.of("invalid.password", "Password must be at least 8 characters long"));
        }


        //также добавить для остальных poley
        return validationResult;
    }

    public static CreateUserValidator getInstance() {
        return INSTANCE;
    }
}