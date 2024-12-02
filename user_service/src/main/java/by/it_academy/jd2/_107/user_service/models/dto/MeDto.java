package by.it_academy.jd2._107.user_service.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MeDto {

    private UUID id;

    private String mail;

    private String fio;

    private UserRole role;

    private UserStatus status;

    private LocalDateTime createAt;

    private LocalDateTime upDateAt;

}
