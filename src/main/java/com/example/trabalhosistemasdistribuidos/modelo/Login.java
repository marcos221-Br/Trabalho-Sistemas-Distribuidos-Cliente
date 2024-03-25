package com.example.trabalhosistemasdistribuidos.modelo;

public class Login {
    private static String login;
    private static String senha;

    public static boolean buscar(){ // Busca o usuário no banco para verificação
        try{
            return false;
        }catch(NullPointerException NPE){
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
}
