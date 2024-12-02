package by.it_academy.jd2._107.classifier_service.service.api;

import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CategoryDTO;
import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CurrencyDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCatDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfDTO;
import org.springframework.data.domain.Page;

public interface ICurrencyService {

    void createCurrency(CurrencyDTO currencyDTO);

    PageOfDTO pageOfCurr(Integer page, Integer size);
}
