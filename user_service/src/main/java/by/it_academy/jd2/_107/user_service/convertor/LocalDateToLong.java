package by.it_academy.jd2._107.user_service.convertor;


import lombok.*;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Component
public class LocalDateToLong {

    private Long dateTime;

    public Long dateToLong(LocalDateTime time) {
        return this.dateTime = time.now().toEpochSecond(ZoneOffset.UTC);
    }

    public Long dateTime() {
        return this.dateTime = LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
    }
}
