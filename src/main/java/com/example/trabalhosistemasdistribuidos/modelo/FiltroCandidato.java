package com.example.trabalhosistemasdistribuidos.modelo;

import java.util.ArrayList;

import org.json.JSONObject;

public class FiltroCandidato {
    private Integer idCandidato;
    private String email;
    private String nome;
    private ArrayList<String> competencias;
    private Boolean selected;

    public FiltroCandidato(Integer idCandidato, String email, String nome, Boolean selected){
        this.idCandidato = idCandidato;
        this.email = email;
        this.nome = nome;
        this.selected = selected;
        competencias = new ArrayList<>();
    }

    public void setCompetencias(String competencia){
        this.competencias.add(competencia);
    }

    public Integer getIdCandidato(){
        return this.idCandidato;
    }

    public JSONObject getJson(){
        if(selected){
            JSONObject json = new JSONObject();
            json.put("idCandidato",idCandidato);
            json.put("email",email);
            json.put("nome",nome);
            json.put("competencias",competencias);
            return json;
        }
        return null;
    }

    public ArrayList<String> getCompetencias(){
        return this.competencias;
    }

    public String getNome(){
        return this.nome;
    }

    @Override
    public String toString() {
        return "FiltroCandidato [idCandidato=" + idCandidato + ", email=" + email + ", nome=" + nome + ", competencias=" + competencias + "]";
    }
}
