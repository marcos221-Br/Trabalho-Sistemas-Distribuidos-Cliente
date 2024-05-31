package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.trabalhosistemasdistribuidos.enums.CompetenciaEnum;
import com.example.trabalhosistemasdistribuidos.modelo.CompetenciaExperiencia;

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

public class CadastrarCompetenciaController {

    private ObservableList<String> listaCompetencias;
    private ObservableList<CompetenciaExperiencia> competenciaExperiencia;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button adicionar;

    @FXML
    private Button btnAlterar;

    @FXML
    private Button btnBusca;

    @FXML
    private Button btnCadastrar;

    @FXML
    private Button btnExcluir;

    @FXML
    private TableColumn<CompetenciaExperiencia, String> columnCompetencia;

    @FXML
    private TableColumn<CompetenciaExperiencia, String> columnExperiencia;

    @FXML
    private ComboBox<String> competencias;

    @FXML
    private TextField experiencias;

    @FXML
    private Button btnRemover;

    @FXML
    private TableView<CompetenciaExperiencia> tabela;

    @FXML
    void remover(ActionEvent event) {
        tabela.getItems().remove(tabela.getSelectionModel().getSelectedItem());
    }

    @FXML
    void adicionar(ActionEvent event) {
        competenciaExperiencia.add(new CompetenciaExperiencia(competencias.getValue(), experiencias.getText()));
        tabela.setItems(competenciaExperiencia);
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
        competencias.setItems(this.listaCompetencias);

        competenciaExperiencia = FXCollections.observableArrayList();
        columnCompetencia.setCellValueFactory(new PropertyValueFactory<>("competencia"));
        columnExperiencia.setCellValueFactory(new PropertyValueFactory<>("experiencia"));
    }

}
