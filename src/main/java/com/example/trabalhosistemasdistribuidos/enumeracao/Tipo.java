package com.example.trabalhosistemasdistribuidos.enumeracao;

public enum Tipo {
    ADM("Administrador"),
    USU("Usuário");

    private String tipo;

    private Tipo(String tipo){
        this.tipo = tipo;
    }

    public String getTipo(){
        return tipo;
    }
}
