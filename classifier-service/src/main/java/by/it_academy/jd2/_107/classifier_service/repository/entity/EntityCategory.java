package by.it_academy.jd2._107.classifier_service.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "category", schema = "app")
public class EntityCategory {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Version
    @Column(name = "update_at")
    private LocalDateTime updateAt;
}
