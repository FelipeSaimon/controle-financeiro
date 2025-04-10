package com.saimon.controle_financeiro.exceptions;

public class CamposObrigatorios extends RuntimeException{
    public CamposObrigatorios(String mensagem){
        super(mensagem);
    }

    public CamposObrigatorios(){
        super("Verifique os campos obrigatórios");
    }
}
