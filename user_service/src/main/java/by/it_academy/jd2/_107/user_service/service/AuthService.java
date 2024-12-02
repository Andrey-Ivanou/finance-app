package by.it_academy.jd2._107.user_service.service;

import by.it_academy.jd2._107.user_service.models.UserPrincipal;
import by.it_academy.jd2._107.user_service.models.dto.LoginDTO;
import by.it_academy.jd2._107.user_service.service.api.IAuthService;
import by.it_academy.jd2._107.user_service.service.api.ICabinetService;
import by.it_academy.jd2._107.user_service.service.api.IUserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthService implements IAuthService {

    private final ICabinetService cabinetService;
    private final PasswordEncoder encoder;
    private final UserHolder holder;
    private final UserPrincipal userPrincipal;

    public AuthService(ICabinetService cabinetService, PasswordEncoder encoder, UserHolder holder, UserPrincipal userPrincipal) {
        this.cabinetService = cabinetService;
        this.encoder = encoder;
        this.holder = holder;
        this.userPrincipal = userPrincipal;
    }

    @Override
    public UserPrincipal login(LoginDTO loginDTO) {
        UserPrincipal userPrincipal =
                this.cabinetService.getByLogin(loginDTO.getMail());

        if(userPrincipal == null || !encoder.matches(loginDTO.getPassword(),
                userPrincipal.getPassword())){
            throw new IllegalArgumentException("Логин или пароль неверный");
        }
        return userPrincipal;
    }

    @Override
    public UserPrincipal me() {
        return holder.getUser();
    }
}
