package by.it_academy.jd2._107.user_service.service.api;

import by.it_academy.jd2._107.user_service.models.UserPrincipal;
import by.it_academy.jd2._107.user_service.models.dto.LoginDTO;


public interface IAuthService {

    UserPrincipal login(LoginDTO loginDTO);

    UserPrincipal me();
}
