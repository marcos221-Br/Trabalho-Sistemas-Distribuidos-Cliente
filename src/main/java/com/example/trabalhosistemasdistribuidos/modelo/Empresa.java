package com.example.trabalhosistemasdistribuidos.modelo;

import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.ClientApplication;
import com.example.trabalhosistemasdistribuidos.ToJson;

public class Empresa {
    private String razaoSocial;
    private String ramo;
    private String descricao;
    private String email;
    private String senha;
    private String cnpj;
    private ToJson json;

    public Empresa(String email, String razaoSocial, String ramo, String descricao, String senha, String cnpj){
        this.email = email;
        this.senha = senha;
        this.razaoSocial = razaoSocial;
        this.ramo = ramo;
        this.descricao = descricao;
        this.senha = senha;
        this.cnpj = cnpj;
    }

    public Empresa(String email){
        this(email, "", "", "", "", "");
    }

    public String getRazaoSocial() {
        return this.razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getRamo() {
        return this.ramo;
    }

    public void setRamo(String ramo) {
        this.ramo = ramo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return this.senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public boolean buscar(){
        String[] funcoes = {"email"};
        String[] valores = {this.email};
        String jsonRecebido;
        json = new ToJson("visualizarEmpresa",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if(json.getFuncao("status").equals("201")){
            this.senha = json.getFuncao("senha");
            this.cnpj = json.getFuncao("cnpj");
            this.descricao = json.getFuncao("descricao");
            this.ramo = json.getFuncao("ramo");
            this.razaoSocial = json.getFuncao("razaoSocial");
            return true;
        }else{
            return false;
        }
    }

    public boolean apagar(){
        String[] funcoes = {"email"};
        String[] valores = {this.email};
        String jsonRecebido;
        json = new ToJson("apagarEmpresa",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if(json.getFuncao("status").equals("201")){
            this.senha = "";
            this.cnpj = "";
            this.descricao = "";
            this.ramo = "";
            this.razaoSocial = "";
            return true;
        }else{
            return false;
        }
    }

    public boolean atualizar(){
        String[] funcoes = {"email","razaoSocial","cnpj","senha","descricao","ramo"};
        String[] valores = {this.email,this.razaoSocial,this.cnpj + "",this.senha,this.descricao,this.ramo};
        String jsonRecebido;
        json = new ToJson("atualizarEmpresa",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if(json.getFuncao("status").equals("201")){
            return true;
        }else{
            return false;
        }
    }

    public boolean cadastrar(){
        String[] funcoes = {"razaoSocial","email","cnpj","senha","descrição","ramo"};
        String[] valores = {this.razaoSocial,this.email,this.cnpj + "",this.senha,this.descricao,this.ramo};
        String jsonRecebido;
        json = new ToJson("cadastrarEmpresa",funcoes,valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if(json.getFuncao("status").equals("201")){
            return true;
        }else{
            return false;
        }
    }
}
