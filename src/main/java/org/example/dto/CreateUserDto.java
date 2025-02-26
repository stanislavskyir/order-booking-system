package org.example.dto;

import lombok.Builder;
import lombok.Value;
import org.example.entity.Role;

@Value // value генерит только файнал класс, геттеры. менять через сеттеры мы не сможем
@Builder //Всегда актуален когда больше чем 5 полей+
public class CreateUserDto {
    String name;
    String email;
    String password;
    String role;
}