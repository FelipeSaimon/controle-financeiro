package com.saimon.controle_financeiro.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionsHandler {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionsHandler.class);

    // Exceções genéricas já existentes
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleBusinessException(IllegalArgumentException businessException) {
        return new ResponseEntity<>(businessException.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFoundException(NoSuchElementException notFoundException) {
        return new ResponseEntity<>("Resource ID not found.", HttpStatus.NOT_FOUND);
    }

    // EXCEÇÃO PARA USUÁRIO NÃO ENCONTRADO
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFound(UserNotFoundException ex){
        return new ResponseEntity<>("Usuario não encontrado. ", HttpStatus.NOT_FOUND);
    };

    // EXCEÇÃO PARA CREDENCIAIS INVÁLIDAS
    @ExceptionHandler(CredenciaisInvalidas.class)
    public ResponseEntity<String> handleCredenciaisInvalidas(CredenciaisInvalidas ex){
//        return new ResponseEntity<>("Usuario ou senha incorretos. ", HttpStatus.UNAUTHORIZED);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    // EXCEÇÃO PARA CAMPOS OBRIGATÓRIOS
    @ExceptionHandler(CamposObrigatorios.class)
    public ResponseEntity<String> handleCamposObrigatorios(CamposObrigatorios ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
