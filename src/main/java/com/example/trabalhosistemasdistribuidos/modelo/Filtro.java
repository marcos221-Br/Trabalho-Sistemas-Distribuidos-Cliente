package com.example.trabalhosistemasdistribuidos.modelo;

import java.util.ArrayList;

import org.json.JSONObject;

public class Filtro {
    private ArrayList<String> competencias;
    private String tipo;

    public Filtro(String tipo){
        this.competencias = new ArrayList<>();
        this.tipo = tipo;
    }

    public void setCompetencias(String competencia){
        this.competencias.add(competencia);
    }

    public JSONObject getJson(){
        JSONObject json = new JSONObject();
        json.put("competencias",competencias);
        json.put("tipo",tipo);
        return json;
    }
}
