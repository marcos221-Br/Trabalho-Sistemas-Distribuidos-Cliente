package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.modelo.Login;
import com.example.trabalhosistemasdistribuidos.modelo.MensagemEmpresa;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class MensagemController {

    private ToJson json;
    private static ObservableList<MensagemEmpresa> listaEmpresas;
    
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<MensagemEmpresa, String> columnEmail;

    @FXML
    private TableColumn<MensagemEmpresa, String> columnNome;

    @FXML
    private TableColumn<MensagemEmpresa, String> columnRamo;

    @FXML
    private TableView<MensagemEmpresa> tableEmpresas;

    @FXML
    private Label mensagem;

    @FXML
    public void carregarMensagens(){
        this.desativarMensagem();
        String[] funcoes = {"token","email"};
        String[] valores = {Login.getToken(),Login.getLogin()};
        json = new ToJson("receberMensagem",funcoes,valores);
        String jsonRecebido;
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            for (int i = 0; i < ((JSONArray)json.getFuncao("empresas")).length(); i++) {
                System.out.println(((JSONArray)json.getFuncao("empresas")).get(i));
                listaEmpresas.add(new MensagemEmpresa(((JSONArray)json.getFuncao("empresas")).getJSONObject(i).getString("nome"),
                                                    ((JSONArray)json.getFuncao("empresas")).getJSONObject(i).getString("email"),
                                                    ((JSONArray)json.getFuncao("empresas")).getJSONObject(i).getString("ramo")));
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
        listaEmpresas = FXCollections.observableArrayList();
        this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        this.columnRamo.setCellValueFactory(new PropertyValueFactory<>("ramo"));
    }
}
