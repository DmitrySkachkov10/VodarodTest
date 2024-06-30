package by.dmitry_skachkov.vodarodtest.controller;

import by.dmitry_skachkov.vodarodtest.core.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalHandlerController {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionResponseDto> validationExceptionHandler(ValidationException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDto("Invalid request. Please check your input data."));
    }

    @ExceptionHandler(DataBaseException.class)
    public ResponseEntity<ExceptionResponseDto> dataBaseExceptionHandler(DataBaseException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponseDto("A database error occurred. Please try again later or contact support."));
    }

    @ExceptionHandler(FeignClientException.class)
    public ResponseEntity<ExceptionResponseDto> feignExceptionHandler(FeignClientException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponseDto("An error occurred while interacting with an external service. Please try again later."));
    }

    @ExceptionHandler(CustomApplicationException.class)
    public ResponseEntity<ExceptionResponseDto> customExceptionHandler(CustomApplicationException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionResponseDto("An unexpected error occurred. Please try again later."));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
        return ResponseEntity.badRequest().body("Invalid date format: " + ex.getValue());
    }

}