package by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Builder

public class PageOfDTO<T> {

    private int number;
    private int size;
    @JsonProperty(value = "total_pages")
    private int totalPages;
    @JsonProperty(value = "total_elements")
    private long totalElements;
    private boolean first;
    @JsonProperty(value = "number_of_elements")
    private int numberOfElements;
    private boolean last;
    private List<T> content;
}
