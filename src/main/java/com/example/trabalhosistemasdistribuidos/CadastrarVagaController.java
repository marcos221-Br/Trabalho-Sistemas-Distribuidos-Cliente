package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.trabalhosistemasdistribuidos.enums.CompetenciaEnum;
import com.example.trabalhosistemasdistribuidos.modelo.Competencia;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CadastrarVagaController {

    private ObservableList<String> listaCompetencias;
    private ObservableList<String> listaEstados;
    private ObservableList<Competencia> competencias;

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

    }

    @FXML
    void buscar(ActionEvent event) {

    }

    @FXML
    void cadastrar(ActionEvent event) {

    }

    @FXML
    void excluir(ActionEvent event) {

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
    }

}
