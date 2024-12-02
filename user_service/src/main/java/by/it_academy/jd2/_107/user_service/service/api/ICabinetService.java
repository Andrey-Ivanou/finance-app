package by.it_academy.jd2._107.user_service.service.api;

import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import by.it_academy.jd2._107.user_service.models.UserPrincipal;
import by.it_academy.jd2._107.user_service.models.dto.MeDto;
import by.it_academy.jd2._107.user_service.models.dto.RegCabinetDTO;

public interface ICabinetService {

    void createUser(RegCabinetDTO regCabinetDTO);

    void codeFromUser(String mail, String code);

    MeDto infoMe(EntityUserPrincipal userPrincipal);

    UserPrincipal getByLogin(String mail);

}
