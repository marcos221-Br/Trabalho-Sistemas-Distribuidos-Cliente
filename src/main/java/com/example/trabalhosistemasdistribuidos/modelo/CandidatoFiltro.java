package com.example.trabalhosistemasdistribuidos.modelo;

import java.util.ArrayList;

import org.json.JSONObject;

public class CandidatoFiltro {
    private String tipo;
    private ArrayList<CompetenciaExperiencia> competenciaExperiencias;
    private JSONObject json;

    public CandidatoFiltro(String tipo){
        this.tipo = tipo;
        this.competenciaExperiencias = new ArrayList<>();
        this.json = new JSONObject();
    }

    public void setCompetenciaExperiencia(CompetenciaExperiencia competenciaExperiencia){
        this.competenciaExperiencias.add(competenciaExperiencia);
    }

    public JSONObject getJson(){
        json.put("competenciasExperiencias", competenciaExperiencias);
        json.put("tipo",tipo);
        return json;
    }
}
