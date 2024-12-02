package by.it_academy.jd2._107.user_service.storage.api;

import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IUserStorage extends JpaRepository <EntityUserPrincipal, UUID>{

   /* @Lock(LockModeType.OPTIMISTIC_FORCE_INCREMENT)*/
    EntityUserPrincipal getUserById(UUID id);

    EntityUserPrincipal findByMail(String mail);
}
