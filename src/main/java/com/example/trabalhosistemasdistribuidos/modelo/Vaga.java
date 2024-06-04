package com.example.trabalhosistemasdistribuidos.modelo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.ClientApplication;
import com.example.trabalhosistemasdistribuidos.ToJson;

public class Vaga {
    private int codigo;
    private String nome;
    private float faixaSalarial;
    private String descricao;
    private ArrayList<String> competencia;
    private String estado;
    private ToJson json;

    public Vaga(String nome, float faixaSalarial, String descricao, String estado){
        this.nome = nome;
        this.faixaSalarial = faixaSalarial;
        this.descricao = descricao;
        this.estado = estado;
        competencia = new ArrayList<>();
    }

    public Vaga(int codigo, String nome, float faixaSalarial, String descricao, String estado){
        this.codigo = codigo;
        this.nome = nome;
        this.faixaSalarial = faixaSalarial;
        this.descricao = descricao;
        this.estado = estado;
        competencia = new ArrayList<>();
    }

    public Vaga(int codigo){
        this.codigo = codigo;
        competencia = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getnome() {
        return nome;
    }

    public void setnome(String nome) {
        this.nome = nome;
    }

    public float getFaixaSalarial() {
        return faixaSalarial;
    }

    public void setFaixaSalarial(float faixaSalarial) {
        this.faixaSalarial = faixaSalarial;
    }

    public ArrayList<String> getCompetencia() {
        return competencia;
    }

    public void setCompetencia(String competencia) {
        this.competencia.add(competencia);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEstado(){
        return this.estado;
    }

    public void setEstado(String estado){
        this.estado = estado;
    }

    public boolean cadastrar(){
        String[] funcoes = {"nome","email","descricao","estado","token"};
        String[] valores = {this.nome,Login.getLogin(),this.descricao,this.estado,Login.getToken()};
        String jsonRecebido;
        json = new ToJson("cadastrarVaga", funcoes, valores);
        json.adicionarJson("faixaSalarial", this.faixaSalarial);
        json.adicionarJson("competencias", this.competencia);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            return true;
        }else{
            return false;
        }
    }

    public void buscar(){
        String[] funcoes = {"email","token"};
        String[] valores = {Login.getLogin(),Login.getToken()};
        String jsonRecebido;
        json = new ToJson("visualizarVaga", funcoes, valores);
        json.adicionarJson("idVaga", this.codigo);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        this.descricao = json.getFuncao("descricao")+"";
        this.faixaSalarial = Float.parseFloat(json.getFuncao("faixaSalarial")+"");
        this.estado = json.getFuncao("estado")+"";
        for(int i=0; i<((JSONArray) json.getFuncao("competencias")).length(); i++){
            competencia.add(((JSONArray) json.getFuncao("competencias")).getString(i));
        }
    }

    public boolean excluir(){
        String[] funcoes = {"email","token"};
        String[] valores = {Login.getLogin(),Login.getToken()};
        String jsonRecebido;
        json = new ToJson("apagarVaga", funcoes, valores);
        json.adicionarJson("idVaga", codigo);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            return true;
        }else{
            return false;
        }
    }

    public boolean atualizar(){
        String[] funcoes = {"nome","email","descricao","estado","token"};
        String[] valores = {this.nome,Login.getLogin(),this.descricao,this.estado,Login.getToken()};
        String jsonRecebido;
        json = new ToJson("atualizarVaga", funcoes, valores);
        json.adicionarJson("faixaSalarial", this.faixaSalarial);
        json.adicionarJson("competencias", this.competencia);
        json.adicionarJson("idVaga", codigo);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String toString() {
        return "Vaga [codigo=" + codigo + ", nome=" + nome + ", faixaSalarial=" + faixaSalarial + ", descricao="
                + descricao + ", competencia=" + competencia + ", estado=" + estado + "]";
    }
}
