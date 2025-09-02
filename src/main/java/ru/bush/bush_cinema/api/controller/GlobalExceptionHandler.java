package ru.bush.bush_cinema.api.controller;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.bush.bush_cinema.api.Result;
import ru.bush.bush_cinema.model.session.SitReserveException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SitReserveException.class)
    public ResponseEntity<Result> handleSitReserveException(Exception ex) {
        return ResponseEntity
                .badRequest()
                .body(Result.error(ex.getMessage()));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    public ResponseEntity<Result> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult()
                .getAllErrors()
                .get(0)
                .getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Result.error(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Result> handleAll(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Result.error("Внутренняя ошибка: " + ex.getMessage()));
    }
}
