package com.example.trabalhosistemasdistribuidos.modelo;

public class CompetenciaExperiencia {
    private String competencia;
    private String experiencia;

    public CompetenciaExperiencia(String competencia, String experiencia){
        this.competencia = competencia;
        this.experiencia = experiencia;
    }

    public String getCompetencia(){
        return this.competencia;
    }

    public String getExperiencia(){
        return this.experiencia;
    }

    public void setCompetencia(String competencia){
        this.competencia = competencia;
    }

    public void setExperiencia(String experiencia){
        this.experiencia = experiencia;
    }
}
