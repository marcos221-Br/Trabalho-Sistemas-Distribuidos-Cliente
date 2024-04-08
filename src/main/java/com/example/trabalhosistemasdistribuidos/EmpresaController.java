package com.example.trabalhosistemasdistribuidos;

import com.example.trabalhosistemasdistribuidos.modelo.Candidato;
import com.example.trabalhosistemasdistribuidos.modelo.Empresa;
import com.example.trabalhosistemasdistribuidos.modelo.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EmpresaController {

    private Empresa empresa;
    
    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnBuscar;

    @FXML
    private TextField login;

    @FXML
    private PasswordField senha;

    @FXML
    private TextField nome;

    @FXML
    private Label excecao;

    @FXML
    private TextField cnpj;

    @FXML
    private TextField ramo;

    @FXML
    private TextArea descricao;

    @FXML
    void alterar(ActionEvent event) { // Altera usuário no banco
        desativarExcecao();
        empresa = new Empresa(this.login.getText(),this.nome.getText(), this.ramo.getText(),this.descricao.getText(),this.senha.getText(),Integer.parseInt(this.cnpj.getText()));
        if(empresa.atualizar()){
            novaExcecao("Usuário atualizado com sucesso!", Color.GREEN);
        }else{
            novaExcecao("Usuário não encontrado!", Color.RED);
        }
    }

    @FXML
    void excluir(ActionEvent event) { // Exclui um usuário no banco
        desativarExcecao();
        empresa = new Empresa(Login.getLogin());
        if(empresa.apagar()){
            this.login.setText("");
            this.nome.setText("");
            this.senha.setText("");
            this.descricao.setText("");
            this.ramo.setText("");
            this.cnpj.setText("");
            novaExcecao("Usuário excluido com sucesso!", Color.GREEN);
        }else{
            novaExcecao("Usuário não encontrado!", Color.RED);
        }
        
        
    }

    @FXML
    void buscar(ActionEvent event) {
        desativarExcecao();
        empresa = new Empresa(Login.getLogin());
        if(empresa.buscar()){
            login.setText(empresa.getEmail());
            nome.setText(empresa.getRazaoSocial());
            senha.setText(empresa.getSenha());
            descricao.setText(empresa.getDescricao());
            ramo.setText(empresa.getRamo());
            cnpj.setText(empresa.getCnpj() + "");
        }else{
            novaExcecao("Usuário não encontrado", Color.RED);
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
    void initialize() {

    }
}