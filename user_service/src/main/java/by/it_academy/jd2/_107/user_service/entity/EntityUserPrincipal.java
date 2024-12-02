package by.it_academy.jd2._107.user_service.entity;

import by.it_academy.jd2._107.user_service.convertor.LocalDateToLong;
import by.it_academy.jd2._107.user_service.models.dto.UserRole;
import by.it_academy.jd2._107.user_service.models.dto.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.springframework.jca.support.LocalConnectionFactoryBean;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
@Table(name = "users", schema = "app")
@Builder
public class EntityUserPrincipal {

    @Id
    @JdbcTypeCode(Types.VARCHAR)
    @Column(name = "id")
    private UUID id;
    @Column(name = "mail")
    private String mail;
    @Column(name = "full_name")
    private String fio;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role")
    private UserRole role;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private UserStatus status;
    @Column(name = "password")
    private String password;
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_at")
    private LocalDateTime upDateAt;
    @Column(name = "code_user")
    private int code;

    @PrePersist
    private void prePersist(){
        this.upDateAt = LocalDateTime.now();
    }
}

