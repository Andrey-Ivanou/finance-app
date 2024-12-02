package by.it_academy.jd2._107.user_service.controller;

import by.it_academy.jd2._107.user_service.controller.utils.JwtTokenHandler;
import by.it_academy.jd2._107.user_service.exceptions.DuplicateEntityException;
import by.it_academy.jd2._107.user_service.models.UserPrincipal;
import by.it_academy.jd2._107.user_service.models.dto.LoginDTO;
import by.it_academy.jd2._107.user_service.models.dto.MeDto;
import by.it_academy.jd2._107.user_service.models.dto.RegCabinetDTO;
import by.it_academy.jd2._107.user_service.service.api.IAuthService;
import by.it_academy.jd2._107.user_service.service.api.ICabinetService;
import by.it_academy.jd2._107.user_service.service.api.IUserService;
import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CabinetController {

    private final ICabinetService cabinetService;
    private final IAuthService authService;
    private final JwtTokenHandler jwtHandler;

    public CabinetController(IUserService userService, ICabinetService cabinetService, IAuthService authService, JwtTokenHandler jwtHandler) {
        this.cabinetService = cabinetService;
        this.authService = authService;
        this.jwtHandler = jwtHandler;
    }

    @PostMapping("/cabinet/registration")
    public ResponseEntity<RegCabinetDTO> registration(@RequestBody RegCabinetDTO regCabinetDTO) throws DuplicateEntityException {
        cabinetService.createUser(regCabinetDTO);
        return new ResponseEntity<>(regCabinetDTO, HttpStatus.CREATED);
    }

    @GetMapping("cabinet/verification/{code}/{mail}")
    public ResponseEntity<String>  sendCode(@PathVariable ("code") String code, @PathVariable ("mail") String mail){
        cabinetService.codeFromUser(mail,code);
        return new ResponseEntity<>("Пользователь верифицирован", HttpStatus.OK);
    }

    @PostMapping("/cabinet/login")
    public String loginUser(@RequestBody LoginDTO loginDTO){
        UserPrincipal userPrincipal = authService.login(loginDTO);
        return jwtHandler.generateAccessToken(userPrincipal);
    }

    @GetMapping("/cabinet/me")
    public ResponseEntity<MeDto> infoMe(){
        EntityUserPrincipal userPrincipal = authService.me().getUserPrincipal();
        MeDto meDto = cabinetService.infoMe(userPrincipal);
        return new ResponseEntity<>(meDto , HttpStatus.OK);
    }
}
