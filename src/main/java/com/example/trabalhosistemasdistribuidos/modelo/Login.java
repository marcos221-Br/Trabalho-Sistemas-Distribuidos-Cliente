package com.example.trabalhosistemasdistribuidos.modelo;

import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.ClientApplication;
import com.example.trabalhosistemasdistribuidos.ToJson;

public class Login {
    private static String login;
    private static String senha;
    private static String token;
    private static ToJson json;

    public static boolean buscar(String tipo){ // Busca o usuário no banco para verificação
        String[] funcoes = {"email","senha"};
        String[] valores = {login,senha};
        String jsonRecebido;
        json = new ToJson(tipo, funcoes, valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("200")){
            Login.token = json.getFuncao("token");
            return true;
        }else{
            return false;
        }
    }

    public static void setLogin(String login){
        Login.login = login;
    }

    public static void setSenha(String senha){
        Login.senha = senha;
    }

    public static String getLogin(){
        return Login.login;
    }

    public static String getToken(){
        return Login.token;
    }
}
