package com.saimon.controle_financeiro.exceptions;

public class CredenciaisInvalidas extends RuntimeException{
    public CredenciaisInvalidas(String message){
        super(message);
    }

    public CredenciaisInvalidas(){
        super("Usuario ou senha incorretos");
    }
}
