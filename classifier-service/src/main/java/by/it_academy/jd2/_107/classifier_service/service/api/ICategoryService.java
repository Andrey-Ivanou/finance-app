package by.it_academy.jd2._107.classifier_service.service.api;

import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CategoryDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfDTO;

public interface ICategoryService {

    void createCategory(CategoryDTO categoryDTO);

    PageOfDTO pageOfCat(Integer page, Integer size);
}
