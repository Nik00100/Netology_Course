package ru.netology.springboot_task3_nginx.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.springboot_task3_nginx.exceptions.InvalidCredentials;
import ru.netology.springboot_task3_nginx.exceptions.UnauthorizedUser;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidCredentials.class)
    public ResponseEntity<String> icHandler(InvalidCredentials exc) {
        exc.printStackTrace();
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedUser.class)
    public ResponseEntity<String> uuHandler(UnauthorizedUser exc) {
        exc.printStackTrace();
        return new ResponseEntity<>(exc.getMessage(), HttpStatus.UNAUTHORIZED);
    }
}
