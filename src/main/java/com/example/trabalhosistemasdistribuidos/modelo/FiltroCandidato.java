package com.example.trabalhosistemasdistribuidos.modelo;

import java.util.ArrayList;

import org.json.JSONObject;

public class FiltroCandidato {
    private Integer idCandidato;
    private String email;
    private String nome;
    private ArrayList<String> competencias;
    private ArrayList<Integer> experiencias;

    public FiltroCandidato(Integer idCandidato, String email, String nome){
        this.idCandidato = idCandidato;
        this.email = email;
        this.nome = nome;
        competencias = new ArrayList<>();
        experiencias = new ArrayList<>();
    }

    public void setCompetencias(String competencia){
        this.competencias.add(competencia);
    }

    public void setExperiencias(Integer experiencia){
        this.experiencias.add(experiencia);
    }

    public JSONObject getJson(){
        JSONObject json = new JSONObject();
        json.put("email",email);
        json.put("nome",nome);
        json.put("competencias",competencias);
        return json;
    }

    public ArrayList<String> getCompetencias(){
        return this.competencias;
    }

    public ArrayList<Integer> getExperiencias(){
        return this.experiencias;
    }

    public String getNome(){
        return this.nome;
    }

    public String getEmail(){
        return this.email;
    }

    public Integer getIdCandidato(){
        return this.idCandidato;
    }

    @Override
    public String toString() {
        return "FiltroCandidato [IdCandidato: " + idCandidato + ", email=" + email + ", nome=" + nome + ", competencias=" + competencias + "]";
    }
}
