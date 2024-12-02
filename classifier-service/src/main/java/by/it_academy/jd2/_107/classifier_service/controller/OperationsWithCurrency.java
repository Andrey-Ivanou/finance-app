package by.it_academy.jd2._107.classifier_service.controller;

import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CategoryDTO;
import by.it_academy.jd2._107.classifier_service.DTO.RequestDto.CurrencyDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCatDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfCurrDTO;
import by.it_academy.jd2._107.classifier_service.DTO.ResponseDTO.PageOfDTO;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCategory;
import by.it_academy.jd2._107.classifier_service.repository.entity.EntityCurrency;
import by.it_academy.jd2._107.classifier_service.service.api.ICategoryService;
import by.it_academy.jd2._107.classifier_service.service.api.ICurrencyService;
import jakarta.annotation.security.PermitAll;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class OperationsWithCurrency {

    private final ICurrencyService currencyService;
    private final ICategoryService categoryService;

    public OperationsWithCurrency(ICurrencyService currencyService, ICategoryService categoryService) {
        this.currencyService = currencyService;
        this.categoryService = categoryService;
    }


    @PostMapping("/classifier/currency")
    public ResponseEntity<String> createCurrency(@RequestBody CurrencyDTO currencyDTO){
        currencyService.createCurrency(currencyDTO);
        return new ResponseEntity<>("Валюта добавлена в справочник", HttpStatus.CREATED);
    }


    @GetMapping("/classifier/currency")
    public ResponseEntity<PageOfDTO<PageOfCurrDTO>> getCurrency(@RequestParam (value = "page",
            defaultValue = "0") Integer page, @RequestParam (value = "size",
                                                  defaultValue = "20") Integer size){
        PageOfDTO<PageOfCurrDTO> pageOfDTO = currencyService.pageOfCurr(page, size);
        return new  ResponseEntity<>(pageOfDTO, HttpStatus.OK);
    }


    @PostMapping("/classifier/operation/category")
    public ResponseEntity<String>createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>("Категория добавлена в справочник", HttpStatus.CREATED);
    }


    @GetMapping("/classifier/operation/category")
    public ResponseEntity<PageOfDTO<PageOfCatDTO>> getCategory(@RequestParam (value = "page",
            defaultValue = "0") Integer page, @RequestParam (value = "size", defaultValue = "20") Integer size){
        PageOfDTO<PageOfCatDTO> pageOfDTO = categoryService.pageOfCat(page, size);
        return new ResponseEntity<>(pageOfDTO, HttpStatus.OK);
    }
}
