package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.enums.CompetenciaEnum;
import com.example.trabalhosistemasdistribuidos.modelo.Competencia;
import com.example.trabalhosistemasdistribuidos.modelo.Login;
import com.example.trabalhosistemasdistribuidos.modelo.Vaga;
import com.example.trabalhosistemasdistribuidos.modelo.VagaId;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class CadastrarVagaController {

    private ObservableList<String> listaCompetencias;
    private ObservableList<String> listaEstados;
    private ObservableList<Competencia> competencias;
    private ObservableList<VagaId> listaVagas;
    private Vaga vaga;
    private ToJson json;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdicionar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<Competencia, String> columnCompetencia;

    @FXML
    private ComboBox<String> competenciasVaga;

    @FXML
    private TextField descricaoVaga;

    @FXML
    private ComboBox<String> estadoVaga;

    @FXML
    private TextField faixaSalarialVaga;

    @FXML
    private TextField idVaga;

    @FXML
    private TextField nomeVaga;

    @FXML
    private TableView<Competencia> tabela;

    @FXML
    private Button btnRemoverCompetencia;
    
    @FXML
    private TableView<VagaId> tabelaVagas;

    @FXML
    private TableColumn<VagaId, Integer> columnId;

    @FXML
    private TableColumn<VagaId, String> columnNome;

    @FXML
    private Button btnCarregar;

    @FXML
    private Label mensagem;

    @FXML
    void carregar(ActionEvent event) {
        desativarMensagem();
        idVaga.clear();
        nomeVaga.clear();
        descricaoVaga.clear();
        faixaSalarialVaga.clear();
        listaVagas.clear();
        competencias.clear();
        tabela.setItems(competencias);
        String jsonRecebido;
        String[] funcoes = {"email","token"};
        String[] valores = {Login.getLogin(),Login.getToken()};
        json = new ToJson("listarVagas", funcoes, valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        for(int i = 0; i<((JSONArray) json.getFuncao("vagas")).length(); i++){
            listaVagas.add(new VagaId(((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getString("nomeVaga"),((JSONArray) json.getFuncao("vagas")).getJSONObject(i).getInt("idVaga")));
        }
        tabelaVagas.setItems(listaVagas);
    }

    @FXML
    void removerCompetencia(ActionEvent event) {
        tabela.getItems().remove(tabela.getSelectionModel().getSelectedItem());
    }

    @FXML
    void adicionar(ActionEvent event) {
        competencias.add(new Competencia(competenciasVaga.getValue()));
        tabela.setItems(competencias);
    }

    @FXML
    void alterar(ActionEvent event) {
        desativarMensagem();
        vaga = new Vaga(Integer.parseInt(idVaga.getText()),nomeVaga.getText(),Float.parseFloat(faixaSalarialVaga.getText()),descricaoVaga.getText(),estadoVaga.getSelectionModel().getSelectedItem());
        for (Competencia competencia : tabela.getItems()) {
            vaga.setCompetencia(competencia.getCompetencia());
        }
        if(vaga.atualizar()){
            novaMensagem("Vaga atualizada com sucesso!", Color.GREEN);
        }else{
            novaMensagem("Impossível atualizar vaga!", Color.RED);
        }
    }

    @FXML
    void buscar(ActionEvent event) {
        desativarMensagem();
        competencias.clear();
        vaga = new Vaga(tabelaVagas.getSelectionModel().getSelectedItem().getIdVaga());
        vaga.buscar();
        nomeVaga.setText(tabelaVagas.getSelectionModel().getSelectedItem().getNomeVaga());
        idVaga.setText(tabelaVagas.getSelectionModel().getSelectedItem().getIdVaga()+"");
        faixaSalarialVaga.setText(vaga.getFaixaSalarial()+"");
        descricaoVaga.setText(vaga.getDescricao());
        estadoVaga.setValue(vaga.getEstado());
        for (String competencia : vaga.getCompetencia()) {
            competencias.add(new Competencia(competencia));
        }
        tabela.setItems(competencias);
    }

    @FXML
    void cadastrar(ActionEvent event) {
        desativarMensagem();
        vaga = new Vaga(nomeVaga.getText(),Float.parseFloat(faixaSalarialVaga.getText()),descricaoVaga.getText(),estadoVaga.getSelectionModel().getSelectedItem());
        for (Competencia competencia : tabela.getItems()) {
            vaga.setCompetencia(competencia.getCompetencia());
        }
        if(vaga.cadastrar()){
            novaMensagem("Vaga cadastrada com sucesso!", Color.GREEN);
        }else{
            novaMensagem("Impossível cadastrar vaga!", Color.RED);
        }
    }

    @FXML
    void excluir(ActionEvent event) {
        desativarMensagem();
        vaga = new Vaga(Integer.parseInt(idVaga.getText()));
        if(vaga.excluir()){
            novaMensagem("Vaga excluida com sucesso!", Color.GREEN);
            nomeVaga.clear();
            idVaga.clear();
            faixaSalarialVaga.clear();
            descricaoVaga.clear();
            competencias.clear();
            tabela.setItems(competencias);
        }else{
            novaMensagem("Impossível excluir a vaga!", Color.RED);
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
        competenciasVaga.setItems(this.listaCompetencias);

        this.listaEstados = FXCollections.observableArrayList("Disponível","Divulgável");
        estadoVaga.setItems(listaEstados);

        competencias = FXCollections.observableArrayList();

        columnCompetencia.setCellValueFactory(new PropertyValueFactory<>("competencia"));
        
        listaVagas = FXCollections.observableArrayList();
        columnId.setCellValueFactory(new PropertyValueFactory<>("idVaga"));
        columnNome.setCellValueFactory(new PropertyValueFactory<>("nomeVaga"));
    }

}
