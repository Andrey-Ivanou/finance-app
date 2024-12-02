package by.it_academy.jd2._107.user_service.mapper;

import by.it_academy.jd2._107.user_service.entity.EntityUserPrincipal;
import by.it_academy.jd2._107.user_service.models.dto.MeDto;
import by.it_academy.jd2._107.user_service.models.dto.PageDTO;
import by.it_academy.jd2._107.user_service.models.dto.RegCabinetDTO;
import by.it_academy.jd2._107.user_service.models.dto.RegistrationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface UserPopulator {

    UserPopulator INSTANCE = Mappers.getMapper(UserPopulator.class);

    EntityUserPrincipal toEntityUser(RegistrationDTO registrationDTO);

    EntityUserPrincipal toEntityCabinet(RegCabinetDTO regCabinetDTO);

    MeDto toResponseMeDTO(EntityUserPrincipal userPrincipal);

    PageDTO<MeDto> toPageDTO(Page<EntityUserPrincipal> userPrincipals);


}
