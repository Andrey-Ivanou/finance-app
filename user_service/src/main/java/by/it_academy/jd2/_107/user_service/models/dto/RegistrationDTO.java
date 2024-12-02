package by.it_academy.jd2._107.user_service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RegistrationDTO {

    private String mail;
    private String fio;
    private UserRole role;
    private UserStatus status;
    private String password;
}
