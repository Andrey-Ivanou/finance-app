package by.it_academy.jd2._107.user_service.controller.advice;

import by.it_academy.jd2._107.user_service.exceptions.DuplicateEntityException;
import by.it_academy.jd2._107.user_service.models.dto.ExceptionDTO;
import org.aspectj.apache.bcel.classfile.ExceptionTable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.authorization.ExceptionTranslationWebFilter;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler
    public ResponseEntity<?> handle(IllegalArgumentException ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .logref("error"
                ).message(ex.getMessage())
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(Exception ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .logref("error"
                ).message("Сервер не смог корректно обработать запрос. Пожалуйста обратитесь к администратору")
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(NoSuchElementException ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .logref("error")
                .message("Запрос некорректен. Сервер не может обработать запрос")
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> handle(AuthenticationException ex) {
        ExceptionDTO exceptionDTO = ExceptionDTO.builder()
                .logref("error")
                .message("Для выполнения запроса по данному адресу необходимо передать токен авторизации.")
                .build();

        return new ResponseEntity<>(exceptionDTO, HttpStatus.UNAUTHORIZED);
    }

}
