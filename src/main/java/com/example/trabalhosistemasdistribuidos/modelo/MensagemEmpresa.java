package com.example.trabalhosistemasdistribuidos.modelo;

public class MensagemEmpresa {
    private String nome;
    private String email;
    private String ramo;

    public MensagemEmpresa(String nome, String email, String ramo){
        this.nome = nome;
        this.email = email;
        this.ramo = ramo;
    }

    public String getNome(){
        return this.nome;
    }

    public String getRamo(){
        return this.ramo;
    }

    public String getEmail(){
        return this.email;
    }
}
