package by.it_academy.jd2._107.user_service.controller;

import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import by.it_academy.jd2._107.user_service.exceptions.DuplicateEntityException;
import by.it_academy.jd2._107.user_service.models.dto.MeDto;
import by.it_academy.jd2._107.user_service.models.dto.PageDTO;
import by.it_academy.jd2._107.user_service.models.dto.RegistrationDTO;
import by.it_academy.jd2._107.user_service.service.api.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<String> create(@RequestBody RegistrationDTO user) throws DuplicateEntityException {
        userService.createUser(user);
        return new  ResponseEntity<>("Пользователь добавлен", HttpStatus.CREATED);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity <PageDTO<MeDto>> getUsers(@RequestParam (value = "page", defaultValue = "0") Integer page,
                                                 @RequestParam (value = "size", defaultValue = "20") Integer size){
        PageDTO<MeDto> pageDTO = userService.getUsers(page, size);
        return new ResponseEntity<>(pageDTO, HttpStatus.OK);
    }

    @GetMapping("/users/{uuid}")
    public ResponseEntity<MeDto> getUserById(@PathVariable ("uuid") UUID uuid) {
        MeDto user = userService.getUserById(uuid);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/users/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> updateUser(@RequestBody RegistrationDTO user, @PathVariable ("uuid") UUID uuid,
                           @PathVariable ("dt_update") Long dt_update){
        userService.update(user, uuid, dt_update);
        return new ResponseEntity<>("Пользователь обновлён", HttpStatus.OK);
    }
}
