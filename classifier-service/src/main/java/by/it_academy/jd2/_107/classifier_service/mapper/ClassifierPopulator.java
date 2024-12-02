package by.it_academy.jd2._107.classifier_service.mapper;

import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CategoryDTO;
import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CurrencyDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCatDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfDTO;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCategory;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCurrency;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCategory;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCurrency;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCurrDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCatDTO;
import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CurrencyDTO;
import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CategoryDTO;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

@Mapper
public interface ClassifierPopulator {

    ClassifierPopulator INSTANCE = Mappers.getMapper(ClassifierPopulator.class);

    EntityCategory toEntityCat(CategoryDTO categoryDTO);

    EntityCurrency toEntityCurr(CurrencyDTO currencyDTO);

    PageOfCatDTO toCatDTO(EntityCategory category);

    PageOfCurrDTO toCurrDTO(EntityCurrency entityCurrency);

    PageOfDTO<PageOfCatDTO> toPageCatDTO(Page<EntityCategory> entityCategories);

    PageOfDTO<PageOfCurrDTO> toPageCurrDTO(Page<EntityCurrency> entityCurrencies);


}
