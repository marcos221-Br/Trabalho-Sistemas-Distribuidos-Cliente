package com.example.trabalhosistemasdistribuidos.enumeracao;

public enum Sexo {
    MAS("Masculino"),
    FEM("Feminino"),
    OUT("Outros");

    private String sexo;

    private Sexo(String sexo){
        this.sexo = sexo;
    }

    public String getSexo(){
        return sexo;
    }
}
