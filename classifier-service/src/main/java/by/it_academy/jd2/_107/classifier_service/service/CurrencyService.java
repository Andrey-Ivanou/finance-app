package by.it_academy.jd2._107.classifier_service.service;

import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CategoryDTO;
import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CurrencyDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCurrDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfDTO;
import by.it_academy.jd2._107.classifier_service.mapper.ClassifierPopulator;
import by.it_academy.jd2._107.classifier_service.repository.api.ICategoryRepository;
import by.it_academy.jd2._107.classifier_service.repository.api.ICurrencyRepository;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCategory;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCurrency;
import by.it_academy.jd2._107.classifier_service.service.api.ICurrencyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CurrencyService implements ICurrencyService {

    private final ICurrencyRepository repositoryCurrency;

    public CurrencyService(ICurrencyRepository repositoryCurrency) {
        this.repositoryCurrency = repositoryCurrency;

    }

    @Override
    @Transactional
    public void createCurrency(CurrencyDTO currencyDTO) {

        List<EntityCurrency> list = this.repositoryCurrency.findAll();
        for (EntityCurrency entity : list)
            if (currencyDTO.getTitle().equals(entity.getTitle()) ||
            currencyDTO.getDescription().equals(entity.getDescription())){
                throw new IllegalArgumentException("Такая валюта уже существует!");
            }

        EntityCurrency currency = ClassifierPopulator.INSTANCE.toEntityCurr(currencyDTO);
        currency.setId(UUID.randomUUID());
        currency.setCreateAt(LocalDateTime.now());

        this.repositoryCurrency.save(currency);
    }

    @Override
    @Transactional(readOnly = true)
    public PageOfDTO<PageOfCurrDTO> pageOfCurr(Integer page, Integer size) {
        Page<EntityCurrency> pageOf = this.repositoryCurrency.findAll(PageRequest.of(page, size));
        return ClassifierPopulator.INSTANCE.toPageCurrDTO(pageOf);
    }
}
