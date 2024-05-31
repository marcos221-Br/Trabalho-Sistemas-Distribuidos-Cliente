package com.example.trabalhosistemasdistribuidos;

import java.util.ResourceBundle;

import com.example.trabalhosistemasdistribuidos.modelo.Empresa;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CadastrarEmpresaController {

    private Empresa empresa;
    
    @FXML
    private Hyperlink voltar;

    @FXML
    private ResourceBundle resources;

    @FXML
    private Button cadastrar;

    @FXML
    private TextField cnpj;

    @FXML
    private TextArea descricao;

    @FXML
    private TextField email;

    @FXML
    private TextField ramo;

    @FXML
    private TextField razaoSocial;

    @FXML
    private PasswordField senha;

    @FXML
    private Label excecao;

    @FXML
    void cadastrar(ActionEvent event) { // Cadastra empresa no banco
        desativarExcecao();
        empresa = new Empresa(email.getText(),razaoSocial.getText(),ramo.getText(),descricao.getText(),senha.getText(),cnpj.getText());
        if(empresa.cadastrar()){
            novaExcecao("Empresa cadastrado com sucesso!", Color.GREEN);
            ClientApplication.trocarTela("PrincipalEmpresa");
        }else{
            novaExcecao("Impossível cadastrar empresa!", Color.RED);
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
