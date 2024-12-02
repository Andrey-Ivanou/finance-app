package by.it_academy.jd2._107.classifier_service.service;

import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CategoryDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCatDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfDTO;
import by.it_academy.jd2._107.classifier_service.mapper.ClassifierPopulator;
import by.it_academy.jd2._107.classifier_service.repository.api.ICategoryRepository;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCategory;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCurrency;
import by.it_academy.jd2._107.classifier_service.service.api.ICategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryService implements ICategoryService {

    private final ICategoryRepository categoryRepository;

    public CategoryService(ICategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transactional
    public void createCategory(CategoryDTO categoryDTO) {
        List<EntityCategory> list = this.categoryRepository.findAll();
        for (EntityCategory entity : list)
            if (categoryDTO.getTitle().equals(entity.getTitle())){
                throw new IllegalArgumentException("Такая атегория уже существует!");
            }

        EntityCategory category = ClassifierPopulator.INSTANCE.toEntityCat(categoryDTO);
        category.setId(UUID.randomUUID());
        category.setCreateAt(LocalDateTime.now());

        this.categoryRepository.save(category);
    }

    @Override
    @Transactional(readOnly = true)
    public PageOfDTO<PageOfCatDTO> pageOfCat(Integer page, Integer size) {
        Page<EntityCategory> pageOf = this.categoryRepository.findAll(PageRequest.of(page,size));
        return ClassifierPopulator.INSTANCE.toPageCatDTO(pageOf);
    }

}
