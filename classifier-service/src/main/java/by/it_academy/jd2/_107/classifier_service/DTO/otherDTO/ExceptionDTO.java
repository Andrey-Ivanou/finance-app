package by.it_academy.jd2._107.classifier_service.DTO.otherDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExceptionDTO {

    String logref;
    String message;
}
