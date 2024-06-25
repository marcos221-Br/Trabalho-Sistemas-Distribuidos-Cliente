package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.enums.CompetenciaEnum;
import com.example.trabalhosistemasdistribuidos.modelo.Filtro;
import com.example.trabalhosistemasdistribuidos.modelo.FiltroVaga;
import com.example.trabalhosistemasdistribuidos.modelo.Login;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class VisualizarVagasController {

    private ObservableList<String> listaCompetencias;
    private ObservableList<String> tipoConsulta;
    private ObservableList<String> filtro;
    private ObservableList<FiltroVaga> filtroVagaArray;
    private ToJson json;
    private Filtro filtroClass;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<FiltroVaga, ArrayList<String>> columnCompetencias;

    @FXML
    private TableColumn<FiltroVaga, String> columnDescricao;

    @FXML
    private TableColumn<FiltroVaga, Float> columnFaixaSalarial;

    @FXML
    private TableColumn<String, String> columnFiltros;

    @FXML
    private TableColumn<FiltroVaga, String> columnNome;

    @FXML
    private ComboBox<String> filtroBox;

    @FXML
    private TableView<String> tabelaFiltro;

    @FXML
    private TableView<FiltroVaga> tabelaVagas;

    @FXML
    private ComboBox<String> tipoBox;

    @FXML
    private Label mensagem;

    @FXML
    void adicionar(ActionEvent event) {
        filtro.add(filtroBox.getValue());
        tabelaFiltro.setItems(filtro);
    }

    @FXML
    void remover(ActionEvent event) {
        tabelaFiltro.getItems().remove(tabelaFiltro.getSelectionModel().getSelectedItem());
    }

    @FXML
    void buscar(ActionEvent event) {
        desativarMensagem();
        String[] funcoes = {"token"};
        String[] valores = {Login.getToken()};
        String jsonRecebido;
        json = new ToJson("filtrarVagas", funcoes, valores);
        filtroClass = new Filtro(tipoBox.getValue().toUpperCase());
        for (String competencia : tabelaFiltro.getItems()) {
            filtroClass.setCompetencias(competencia);
        }
        json.adicionarJson("filtros", filtroClass.getJson());
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        filtroVagaArray.clear();
        if((json.getFuncao("status")+"").equals("201")){
            for (int i = 0; i < ((JSONArray) json.getFuncao("vagas")).length(); i++) {
                FiltroVaga filtroVaga = new FiltroVaga(((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getInt("idVaga"),
                                                        ((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getString("email"),
                                                        ((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getFloat("faixaSalarial"),
                                                        ((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getString("descricao"),
                                                        ((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getString("estado"),
                                                        ((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getString("nome"));
                for (int j = 0; j < ((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getJSONArray("competencias").length(); j++) {
                    filtroVaga.setCompetencias(((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getJSONArray("competencias").getString(j));
                }
                filtroVagaArray.add(filtroVaga);
            }
            tabelaVagas.setItems(filtroVagaArray);
        }else{
            novaMensagem("Erro ao encontrar Vagas!", Color.RED);
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
        this.listaCompetencias = FXCollections.observableArrayList();
        for (CompetenciaEnum competencias : CompetenciaEnum.values()) {
            this.listaCompetencias.add(competencias.getCompetencia());
        }
        filtroBox.setItems(this.listaCompetencias);

        this.tipoConsulta = FXCollections.observableArrayList("and","or","all");
        tipoBox.setItems(this.tipoConsulta);

        this.filtro = FXCollections.observableArrayList();

        this.columnFiltros.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));

        this.filtroVagaArray = FXCollections.observableArrayList();
        this.columnCompetencias.setCellValueFactory(new PropertyValueFactory<>("competencias"));
        this.columnDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        this.columnFaixaSalarial.setCellValueFactory(new PropertyValueFactory<>("faixaSalarial"));
        this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
    }

}