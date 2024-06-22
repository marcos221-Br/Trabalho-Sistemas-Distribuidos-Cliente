package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.enums.CompetenciaEnum;
import com.example.trabalhosistemasdistribuidos.modelo.CandidatoFiltro;
import com.example.trabalhosistemasdistribuidos.modelo.CompetenciaExperiencia;
import com.example.trabalhosistemasdistribuidos.modelo.FiltroCandidato;
import com.example.trabalhosistemasdistribuidos.modelo.Login;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class VisualizarCandidatosController {

    private ObservableList<String> listaCompetencias;
    private ObservableList<String> tipoConsulta;
    private ObservableList<CompetenciaExperiencia> filtro;
    private ObservableList<FiltroCandidato> filtroCandidatoArray;
    private ToJson json;
    private CandidatoFiltro filtroClass;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<FiltroCandidato, ArrayList<String>> columnCompetencias;

    @FXML
    private TableColumn<CompetenciaExperiencia, String> columnCompetenciasFiltro;

    @FXML
    private TableColumn<FiltroCandidato, ArrayList<Integer>> columnExperiencias;

    @FXML
    private TableColumn<CompetenciaExperiencia, Integer> columnExperienciasFiltro;

    @FXML
    private TableColumn<FiltroCandidato, String> columnNome;

    @FXML
    private ComboBox<String> filtroBox;

    @FXML
    private TableView<CompetenciaExperiencia> tabelaFiltro;

    @FXML
    private TableView<FiltroCandidato> tabelaCandidatos;

    @FXML
    private ComboBox<String> tipoBox;

    @FXML
    private TextField filtroExperiencia;

    @FXML
    private Label mensagem;

    @FXML
    void adicionar(ActionEvent event) {
        filtro.add(new CompetenciaExperiencia(filtroBox.getValue(),Integer.parseInt(filtroExperiencia.getText())));
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
        json = new ToJson("filtrarCandidatos", funcoes, valores);
        filtroClass = new CandidatoFiltro(tipoBox.getValue().toUpperCase());
        for (CompetenciaExperiencia competencia : tabelaFiltro.getItems()) {
            filtroClass.setCompetenciaExperiencia(competencia);
        }
        json.adicionarJson("filtros", filtroClass.getJson());
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        filtroCandidatoArray.clear();
        if((json.getFuncao("status")+"").equals("201")){
            for (int i = 0; i < ((JSONArray) json.getFuncao("candidatos")).length(); i++) {
                FiltroCandidato filtroCandidato = new FiltroCandidato(((JSONArray) json.getFuncao("candidatos")).getJSONObject(i).getInt("idCandidato"),
                                                        ((JSONArray) json.getFuncao("candidatos")).getJSONObject(i).getString("email"),
                                                        ((JSONArray) json.getFuncao("candidatos")).getJSONObject(i).getString("nome"));
                for (int j = 0; j < ((JSONArray) json.getFuncao("candidatos")).getJSONObject(i).getJSONArray("competenciasExperiencias").length(); j++) {
                    filtroCandidato.setCompetencias(((JSONArray) json.getFuncao("candidatos")).getJSONObject(i).getJSONArray("competenciasExperiencias").getJSONObject(j).getString("competencia"));
                    filtroCandidato.setExperiencias(((JSONArray) json.getFuncao("candidatos")).getJSONObject(i).getJSONArray("competenciasExperiencias").getJSONObject(j).getInt("experiencia"));
                }
                filtroCandidatoArray.add(filtroCandidato);
            }
            tabelaCandidatos.setItems(filtroCandidatoArray);
        }else{
            novaMensagem("Erro ao encontrar Candidatos!", Color.RED);
        }
    }

    @FXML
    void enviar(ActionEvent event) {
        desativarMensagem();
        ArrayList<Integer> candidatos = new ArrayList<>();
        String[] funcoes = {"token","email"};
        String[] valores = {Login.getToken(),Login.getLogin()};
        String jsonRecebido;
        json = new ToJson("enviarMensagem", funcoes, valores);
        for (FiltroCandidato candidato : tabelaCandidatos.getSelectionModel().getSelectedItems()) {
            candidatos.add(candidato.getIdCandidato());
        }
        json.adicionarJson("candidatos", candidatos);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            novaMensagem("Mensagens enviadas com sucesso!", Color.GREEN);
        }else{
            novaMensagem("Erro ao encontrar Candidatos!", Color.RED);
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

        this.columnCompetenciasFiltro.setCellValueFactory(new PropertyValueFactory<>("competencia"));
        this.columnExperienciasFiltro.setCellValueFactory(new PropertyValueFactory<>("experiencia"));

        this.filtroCandidatoArray = FXCollections.observableArrayList();
        this.columnCompetencias.setCellValueFactory(new PropertyValueFactory<>("competencias"));
        this.columnExperiencias.setCellValueFactory(new PropertyValueFactory<>("experiencias"));
        this.columnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        this.tabelaCandidatos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

}