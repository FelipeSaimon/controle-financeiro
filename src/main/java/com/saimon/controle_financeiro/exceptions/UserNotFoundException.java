package com.saimon.controle_financeiro.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }

    public UserNotFoundException(){
        super("Usuario não encontrado!");
    }
}
