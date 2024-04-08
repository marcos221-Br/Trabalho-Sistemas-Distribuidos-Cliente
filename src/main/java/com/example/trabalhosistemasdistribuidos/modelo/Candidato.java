package com.example.trabalhosistemasdistribuidos.modelo;

import com.example.trabalhosistemasdistribuidos.ClientApplication;
import com.example.trabalhosistemasdistribuidos.ToJson;

public class Candidato {
    private String nome;
    private String email;
    private String senha;
    private ToJson json;

    public Candidato(String email, String nome, String senha){
        this.email = email;
        this.nome = nome;
        this.senha = senha;
    }

    public Candidato(String email){
        this(email, "", "");
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getNome(){
        return this.nome;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getSenha(){
        return this.senha;
    }

    public boolean buscar(){
        String[] funcoes = {"email"};
        String[] valores = {this.email};
        String jsonRecebido;
        json = new ToJson("visualizarCandidato",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(jsonRecebido);
        if(json.getFuncao("status").equals("201")){
            this.nome = json.getFuncao("nome");
            this.senha = json.getFuncao("senha");
            return true;
        }else{
            return false;
        }
    }

    public boolean apagar(){
        String[] funcoes = {"email"};
        String[] valores = {this.email};
        String jsonRecebido;
        json = new ToJson("apagarCandidato",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(jsonRecebido);
        if(json.getFuncao("status").equals("201")){
            this.nome = "";
            this.senha = "";
            return true;
        }else{
            return false;
        }
    }

    public boolean atualizar(){
        String[] funcoes = {"email","nome","senha"};
        String[] valores = {this.email,this.nome,this.senha};
        String jsonRecebido;
        json = new ToJson("atualizarCandidato",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(jsonRecebido);
        if(json.getFuncao("status").equals("201")){
            return true;
        }else{
            return false;
        }
    }

    public boolean cadastrar(){
        String[] funcoes = {"nome","email","senha"};
        String[] valores = {this.nome,this.email,this.senha};
        String jsonRecebido;
        json = new ToJson("cadastrarCandidato",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(jsonRecebido);
        if(json.getFuncao("status").equals("201")){
            return true;
        }else{
            return false;
        }
    }
}
