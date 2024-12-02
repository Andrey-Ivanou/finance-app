package by.it_academy.jd2._107.user_service.service;


import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import by.it_academy.jd2._107.user_service.mailSender.api.INotificationSenderService;
import by.it_academy.jd2._107.user_service.mapper.UserPopulator;
import by.it_academy.jd2._107.user_service.models.dto.*;
import by.it_academy.jd2._107.user_service.service.api.IUserService;
import by.it_academy.jd2._107.user_service.storage.api.IUserStorage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;



@Service
public class UserService implements IUserService {

    private final PasswordEncoder encoder;
    private final IUserStorage userStorage;
    private final INotificationSenderService mailService;
    private Random random = new Random();


    public UserService(PasswordEncoder encoder, IUserStorage userStorage,
                       INotificationSenderService mailService) {
        this.encoder = encoder;
        this.userStorage = userStorage;
        this.mailService = mailService;

    }

    @Override
    @Transactional
    public void createUser(RegistrationDTO userDTO) {  //users  POST

        int code = random.nextInt(1001);

        List<EntityUserPrincipal> list = this.userStorage.findAll();
        for (EntityUserPrincipal entity : list)
            if (userDTO.getMail().equals(entity.getMail())) {
                throw new IllegalArgumentException("Такой Login уже существует!");
            }
        EntityUserPrincipal entityUser = UserPopulator.INSTANCE.toEntityUser(userDTO);

        entityUser.setId(UUID.randomUUID());
        entityUser.setPassword(encoder.encode(userDTO.getPassword())); //Шифрование
        entityUser.setCreateAt(LocalDateTime.now());
        entityUser.setCode(code);
        this.userStorage.save(entityUser);

        mailService.send(userDTO.getMail(), String.valueOf(code));
    }

    @Override
    @Transactional(readOnly = true)
    public PageDTO<MeDto> getUsers(Integer page, Integer size) {       //users  GET

        Page<EntityUserPrincipal> pageOf = this.userStorage
                .findAll(PageRequest.of(page, size));
        if (page > pageOf.getTotalPages() - 1) {
            throw new IllegalArgumentException("Такой страницы не существует");
        }
        return UserPopulator.INSTANCE.toPageDTO(pageOf);
    }

    @Override
    @Transactional(readOnly = true)
    public MeDto getUserById(UUID id) {                //users/{uuid}
            EntityUserPrincipal userPrincipal = this.userStorage.findById(id).orElseThrow();
        return UserPopulator.INSTANCE.toResponseMeDTO(userPrincipal);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly=false)
    public void update(RegistrationDTO registrationDTO, UUID id, Long update) {     //users/{uuid}/dt_update/{dt_update}

        EntityUserPrincipal user = this.userStorage.getUserById(id);
        if(user == null) {
            throw new IllegalArgumentException("Пользователя с таким id в системе не найдено!");
        }

        if (user.getUpDateAt().toEpochSecond(ZoneOffset.UTC) != update) {
            throw new IllegalArgumentException("Повторите операцию снова!");
        }
            user.setMail(registrationDTO.getMail());
            user.setFio(registrationDTO.getFio());
            user.setRole(registrationDTO.getRole());
            user.setStatus(registrationDTO.getStatus());
            user.setPassword(encoder.encode(registrationDTO.getPassword()));

            this.userStorage.saveAndFlush(user);
    }

}
