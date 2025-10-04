package com.schoolflow.School_flow_api.exceptions.handler;

import com.schoolflow.School_flow_api.dto.ExceptionResponseDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponseDTO> validationExceptionHandler(MethodArgumentNotValidException exception) {

        // Extraer mensajes de error
        List<String> errores = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();

        // Retornar DTO con lista de errores
        return ResponseEntity.badRequest()
                .body(new ExceptionResponseDTO(
                        HttpStatus.BAD_REQUEST.value(),
                        "ARGUMENT_NOT_VALID",
                        errores
                ));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionResponseDTO> validationExceptionHandler(EntityNotFoundException exception) {


        // Retornar DTO con lista de errores
        return ResponseEntity.badRequest()
                .body(new ExceptionResponseDTO(
                        HttpStatus.BAD_REQUEST.value(),
                        "ENTITY_NOT_FOUND",
                        List.of(exception.getMessage())
                ));
    }

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<ExceptionResponseDTO> validationExceptionHandler(EntityExistsException exception) {


        // Retornar DTO con lista de errores
        return ResponseEntity.badRequest()
                .body(new ExceptionResponseDTO(
                        HttpStatus.CONFLICT.value(),
                        "ENTITY_ALREADY_ASIGNED",
                        List.of(exception.getMessage())
                ));
    }

}
