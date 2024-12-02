package by.it_academy.jd2._107.user_service.service;

import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import by.it_academy.jd2._107.user_service.mailSender.api.INotificationSenderService;
import by.it_academy.jd2._107.user_service.mapper.UserPopulator;
import by.it_academy.jd2._107.user_service.models.UserPrincipal;
import by.it_academy.jd2._107.user_service.models.dto.MeDto;
import by.it_academy.jd2._107.user_service.models.dto.RegCabinetDTO;
import by.it_academy.jd2._107.user_service.models.dto.UserRole;
import by.it_academy.jd2._107.user_service.models.dto.UserStatus;
import by.it_academy.jd2._107.user_service.service.api.ICabinetService;
import by.it_academy.jd2._107.user_service.storage.api.IUserStorage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

import static java.lang.Integer.*;

@Service
public class CabinetService  implements ICabinetService {

    private final PasswordEncoder encoder;
    private final IUserStorage userStorage;
    private final INotificationSenderService mailService;
    private Random random = new Random();


    public CabinetService(PasswordEncoder encoder, IUserStorage userStorage, INotificationSenderService mailService) {

        this.encoder = encoder;
        this.userStorage = userStorage;
        this.mailService = mailService;
    }

    @Override
    public void createUser(RegCabinetDTO regCabinetDTO) {
        if (regCabinetDTO.getMail() == null || regCabinetDTO.getMail().isBlank()) {
            throw new IllegalArgumentException("Вы не ввели e-mail");
        }

        if (regCabinetDTO.getFio() == null || regCabinetDTO.getFio().isBlank()) {
            throw new IllegalArgumentException("Вы не ввели полное имя");
        }

        if (regCabinetDTO.getPassword() == null || regCabinetDTO.getPassword().isBlank()) {
            throw new IllegalArgumentException("Вы не ввели пароль");
        }

        List<EntityUserPrincipal> list = this.userStorage.findAll();
        for (EntityUserPrincipal entity : list)
            if (regCabinetDTO.getMail().equals(entity.getMail())) {
                throw new IllegalArgumentException("Такой Login уже существует!");
            }
        EntityUserPrincipal entityUser = UserPopulator.INSTANCE.toEntityCabinet(regCabinetDTO);

        entityUser.setId(UUID.randomUUID());
        entityUser.setRole(UserRole.USER);
        entityUser.setStatus(UserStatus.WAITING_ACTIVATION);
        entityUser.setPassword(encoder.encode(regCabinetDTO.getPassword())); //Шифрование
        entityUser.setCreateAt(LocalDateTime.now());
        entityUser.setCode(random.nextInt(1001));

        this.userStorage.saveAndFlush(entityUser);
    }

    @Override
    public void codeFromUser(String mail, String code) {

        EntityUserPrincipal entity = this.userStorage.findByMail(mail);
        if (entity == null) {
            throw new IllegalArgumentException("Пользователя с таким e-mail в системе не найден!");
        }

        if (Objects.equals(code, String.valueOf(entity.getCode()))) {

            mailService.send(mail, code);

            entity.setStatus(UserStatus.valueOf("ACTIVATED"));
            userStorage.saveAndFlush(entity);
        }

    }

    @Override
    public MeDto infoMe(EntityUserPrincipal userPrincipal) {
        return UserPopulator.INSTANCE.toResponseMeDTO(userPrincipal);
    }

    @Override
    public UserPrincipal getByLogin(String mail) {
        EntityUserPrincipal userPrincipal = null;

        EntityUserPrincipal user = this.userStorage.findByMail(mail);
        if (user.getStatus().ordinal() == UserStatus.ACTIVATED.ordinal()) {
            userPrincipal = user;
        } else {
            mailService.send(user.getMail(), String.valueOf(user.getCode()));
            throw new IllegalArgumentException("Пользователь не активирован! Код отправлен на почту.");
        }
        return new UserPrincipal(userPrincipal);
    }
}
