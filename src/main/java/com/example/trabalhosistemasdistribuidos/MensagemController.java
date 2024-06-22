package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.modelo.Login;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.paint.Color;

public class MensagemController {

    private ToJson json;
    private ObservableList<String> listaEmpresas;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<String, String> columnEmpresas;

    @FXML
    private TableView<String> tableEmpresas;

    @FXML
    private Label mensagem;

    @FXML
    void carregarMensagens(){
        this.desativarMensagem();
        String[] funcoes = {"token","email"};
        String[] valores = {Login.getToken(),Login.getLogin()};
        json = new ToJson("receberMensagem",funcoes,valores);
        String jsonRecebido;
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            for (int i = 0; i < ((JSONArray)json.getFuncao("empresas")).length(); i++) {
                listaEmpresas.add(((JSONArray)json.getFuncao("empresas")).getString(i));
            }
            this.tableEmpresas.setItems(listaEmpresas);
        }else{
            novaMensagem("Impossóvel carregar mensagens!",Color.RED);
        }
    }

    private void desativarMensagem(){
        mensagem.setVisible(false);
        mensagem.textFillProperty().set(Color.RED);
    }

    private void novaMensagem(String text, Color color){
        mensagem.setText(text);
        mensagem.textFillProperty().set(color);
        mensagem.setVisible(true);
    }

    @FXML
    void initialize() {
        this.listaEmpresas = FXCollections.observableArrayList();
        this.columnEmpresas.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
    }
}
