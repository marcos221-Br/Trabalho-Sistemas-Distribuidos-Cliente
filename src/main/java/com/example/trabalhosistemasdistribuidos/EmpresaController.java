package com.example.trabalhosistemasdistribuidos;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class EmpresaController {

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
    void alterar(ActionEvent event) { // Altera usuário no banco
        desativarExcecao();
        try {
            novaExcecao("Usuário alterado com sucesso!", Color.GREEN);
        } catch (NumberFormatException NFE) {
            System.out.println("A mátricula deve conter apenas números");
            novaExcecao("A mátricula deve conter apenas números!", Color.RED);
        } catch(Exception ex){
            System.out.println(ex);
        }
    }

    @FXML
    void excluir(ActionEvent event) { // Exclui um usuário no banco
        desativarExcecao();
        try {
            this.login.setText("");
            this.nome.setText("");
            this.senha.setText("");
            novaExcecao("Usuário excluido com sucesso!", Color.GREEN);
        } catch (NumberFormatException NFE) {
            System.out.println("A mátricula deve conter apenas números");
            novaExcecao("A mátricula deve conter apenas números!", Color.RED);
        } catch(Exception ex){
            System.out.println(ex);
        }
    }

    @FXML
    void buscar(ActionEvent event) {

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

}