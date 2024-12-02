package by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder
public class PageOfCurrDTO {

    private UUID id;

    private LocalDateTime createAt;

    private LocalDateTime updateAt;

    private String title;

    private String description;
}
