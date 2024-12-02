package by.it_academy.jd2._107.classifier_service.controller.advice;

import by.it_academy.jd2._107.classifier_service.DTO.otherDTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    public ResponseEntity<?> handle(IllegalArgumentException ex){
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .logref("error"
                ).message(ex.getMessage())
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception ex){
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .logref("error"
                ).message("Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору")
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
