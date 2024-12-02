package by.it_academy.jd2._107.user_service.service.api;

import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import by.it_academy.jd2._107.user_service.models.dto.*;

import java.time.LocalDateTime;
import java.util.UUID;

public interface IUserService {

    void createUser(RegistrationDTO userDTO);

    MeDto getUserById(UUID id);

    void  update(RegistrationDTO user, UUID id, Long update);

    PageDTO getUsers(Integer page, Integer size);
}
