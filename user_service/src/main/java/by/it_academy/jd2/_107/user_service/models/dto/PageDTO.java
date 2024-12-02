package by.it_academy.jd2._107.user_service.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Builder
public class PageDTO<T> {

    private int number;
    private int size;
    @JsonProperty(value = "total_pages")
    private int totalPages;
    @JsonProperty(value = "total_elements")
    private int totalElements;
    private boolean first;
    @JsonProperty(value = "number_of_elements")
    private int numberOfElements;
    private boolean last;
    private List<T> content;
}
