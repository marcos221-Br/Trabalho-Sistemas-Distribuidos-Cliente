package com.example.trabalhosistemasdistribuidos;

import com.example.trabalhosistemasdistribuidos.modelo.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Button entrarCandidato;

    @FXML
    private Button entrarEmpresa;

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    private Label excecao;

    @FXML
    private Hyperlink cadCandidato;

    @FXML
    private Hyperlink cadEmpresa;

    // Evento de botão
    @FXML
    void entrarEmpresa(ActionEvent event) { // Validação de login
        boolean validacao;
        excecao.setVisible(false);
        Login.setLogin(login.getText());
        Login.setSenha(senha.getText());
        validacao = Login.buscar("loginEmpresa");
        if(validacao){
            login.setText("");
            senha.setText("");
            ClientApplication.trocarTela("PrincipalEmpresa");
        }else{
            System.out.println("Usuário ou senha errados");
            excecao.setText("Usuário ou senha incorreta!");
            excecao.setVisible(true);
        }
    }

    @FXML
    void entrarCandidato(ActionEvent event) {
        boolean validacao;
        excecao.setVisible(false);
        Login.setLogin(login.getText());
        Login.setSenha(senha.getText());
        validacao = Login.buscar("loginCandidato");
        if(validacao){
            login.setText("");
            senha.setText("");
            ClientApplication.trocarTela("PrincipalCandidato");
        }else{
            System.out.println("Usuário ou senha errados");
            excecao.setText("Usuário ou senha incorreta!");
            excecao.setVisible(true);
        }
    }

    @FXML
    void cadastrarCandidato(ActionEvent event) {
        login.setText("");
        senha.setText("");
        ClientApplication.trocarTela("CadastrarCandidato");
    }

    @FXML
    void cadastrarEmpresa(ActionEvent event) {
        login.setText("");
        senha.setText("");
        ClientApplication.trocarTela("CadastrarEmpresa");
    }

    @FXML
    void initialize() {

    }

}
