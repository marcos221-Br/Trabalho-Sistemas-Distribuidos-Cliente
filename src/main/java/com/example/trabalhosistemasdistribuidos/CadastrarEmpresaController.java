package com.example.trabalhosistemasdistribuidos;

import java.util.ResourceBundle;

import com.example.trabalhosistemasdistribuidos.modelo.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CadastrarEmpresaController {

    @FXML
    private Hyperlink voltar;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField nome;

    @FXML
    private Label excecao;

    @FXML
    void cadastrar(ActionEvent event) { // Cadastra o Idoso
        desativarExcecao();
        try{
            int totalIdoso = 0;
            novaExcecao("Idoso criado com sucesso!", Color.GREEN);
            ClientApplication.escreverLog(Login.getLogin() + " cadastrou idoso " + this.nome.getText());
        }catch(Exception ex){
            System.out.println(ex);
            novaExcecao("Impossível cadastrar idoso!", Color.RED);
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
