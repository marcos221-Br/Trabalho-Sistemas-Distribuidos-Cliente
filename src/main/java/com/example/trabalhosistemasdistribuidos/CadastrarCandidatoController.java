package com.example.trabalhosistemasdistribuidos;

import com.example.trabalhosistemasdistribuidos.modelo.Candidato;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CadastrarCandidatoController {

    private Candidato candidato;
    
    @FXML
    private Hyperlink voltar;

    @FXML
    private TextField login;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField senha;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Label excecao;

    @FXML
    void cadastrar(ActionEvent event) { // Cadastra usuário no banco
        desativarExcecao();
        candidato = new Candidato(login.getText(), nome.getText(), senha.getText());
        if(candidato.cadastrar()){
            novaExcecao("Candidato cadastrado com sucesso!", Color.GREEN);
        }else{
            novaExcecao("Impossível cadastrar candidato!", Color.RED);
        }
    }

    private void desativarExcecao(){
        excecao.setVisible(false);
        excecao.textFillProperty().set(Color.RED);
    }

    private void novaExcecao(String text, Color color){
        excecao.setText(text);
        excecao.textFillProperty().set(color);
        excecao.setVisible(true);
    }

    @FXML
    void voltarLogin(ActionEvent event) {
        ClientApplication.trocarTela("Login");
    }

    @FXML
    void initialize() {

    }

}
