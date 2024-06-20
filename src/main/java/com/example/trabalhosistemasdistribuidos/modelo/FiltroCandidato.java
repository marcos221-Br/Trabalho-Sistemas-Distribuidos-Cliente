package com.example.trabalhosistemasdistribuidos.modelo;

import java.util.ArrayList;

import org.json.JSONObject;

public class FiltroCandidato {
    private String email;
    private String nome;
    private ArrayList<String> competencias;
    private ArrayList<Integer> experiencias;
    private Boolean selected;

    public FiltroCandidato(String email, String nome, Boolean selected){
        this.email = email;
        this.nome = nome;
        this.selected = selected;
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
        if(selected){
            JSONObject json = new JSONObject();
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

    public ArrayList<Integer> getExperiencias(){
        return this.experiencias;
    }

    public String getNome(){
        return this.nome;
    }

    @Override
    public String toString() {
        return "FiltroCandidato [email=" + email + ", nome=" + nome + ", competencias=" + competencias + "]";
    }
}
