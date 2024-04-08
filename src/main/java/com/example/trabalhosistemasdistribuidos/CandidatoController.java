package com.example.trabalhosistemasdistribuidos;

import com.example.trabalhosistemasdistribuidos.modelo.Candidato;
import com.example.trabalhosistemasdistribuidos.modelo.Login;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class CandidatoController {

    private Candidato candidato;
    
    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TextField login;

    @FXML
    private TextField nome;

    @FXML
    private PasswordField senha;

    @FXML
    private Label excecao;

    @FXML
    private Button dados;

    @FXML
    void alterar(ActionEvent event) { // Altera usuário no banco
        desativarExcecao();
        candidato = new Candidato(this.login.getText(),this.nome.getText(),this.senha.getText());
        if(candidato.atualizar()){
            novaExcecao("Usuário atualizado com sucesso!", Color.GREEN);
        }else{
            novaExcecao("Usuário não encontrado!", Color.RED);
        }
    }

    @FXML
    void excluir(ActionEvent event) { // Exclui um usuário no banco
        desativarExcecao();
        candidato = new Candidato(Login.getLogin());
        if(candidato.apagar()){
            this.login.setText("");
            this.nome.setText("");
            this.senha.setText("");
            novaExcecao("Usuário excluido com sucesso!", Color.GREEN);
        }else{
            novaExcecao("Usuário não encontrado!", Color.RED);
        }
        
        
    }

    @FXML
    void buscar(ActionEvent event) {
        desativarExcecao();
        candidato = new Candidato(Login.getLogin());
        if(candidato.buscar()){
            login.setText(candidato.getEmail());
            nome.setText(candidato.getNome());
            senha.setText(candidato.getSenha());
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