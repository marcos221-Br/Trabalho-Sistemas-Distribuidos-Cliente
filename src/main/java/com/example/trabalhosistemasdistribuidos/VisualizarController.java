package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class VisualizarController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField nome;

    @FXML
    private Label excecao;

    @FXML
    void buscar(ActionEvent event) {
        excecao.setVisible(false);
    }

    @FXML
    void buscarTeclado(KeyEvent event) {
        if(event.getCode() == KeyCode.ENTER){
            buscar(null);
        }
    }

    @FXML
    void initialize() {
        
    }
}
