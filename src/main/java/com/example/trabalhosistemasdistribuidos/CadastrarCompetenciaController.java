package com.example.trabalhosistemasdistribuidos;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import org.json.JSONArray;
import org.json.JSONObject;

import com.example.trabalhosistemasdistribuidos.enums.CompetenciaEnum;
import com.example.trabalhosistemasdistribuidos.modelo.CompetenciaExperiencia;
import com.example.trabalhosistemasdistribuidos.modelo.Login;

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

public class CadastrarCompetenciaController {

    private ObservableList<String> listaCompetencias;
    private ObservableList<CompetenciaExperiencia> competenciaExperiencia;
    private ArrayList<JSONObject> lista;
    private ToJson json;

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
    private TableColumn<CompetenciaExperiencia, Integer> columnExperiencia;

    @FXML
    private ComboBox<String> competencias;

    @FXML
    private TextField experiencias;

    @FXML
    private Button btnRemover;
    
    @FXML
    private Label mensagem;

    @FXML
    private TableView<CompetenciaExperiencia> tabela;

    @FXML
    void remover(ActionEvent event) {
        tabela.getItems().remove(tabela.getSelectionModel().getSelectedItem());
    }

    @FXML
    void adicionar(ActionEvent event) {
        competenciaExperiencia.add(new CompetenciaExperiencia(competencias.getValue(), Integer.parseInt(experiencias.getText())));
        tabela.setItems(competenciaExperiencia);
    }

    @FXML
    void alterar(ActionEvent event) {
        desativarMensagem();
        lista = new ArrayList<>();
        String jsonRecebido;
        String[] funcoes = {"email","token"};
        String[] valores = {Login.getLogin(),Login.getToken()};
        json = new ToJson("atualizarCompetenciaExperiencia", funcoes, valores);
        for (CompetenciaExperiencia competenciaExperiencia : tabela.getItems()) {
            lista.add(new JSONObject(competenciaExperiencia));
        }
        json.adicionarJson("competenciaExperiencia", lista);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            novaMensagem("Competência atualizada com sucesso!", Color.GREEN);
        }else{
            novaMensagem("Ocorreu um erro durante a atualização!", Color.RED);
        }
    }

    @FXML
    void buscar(ActionEvent event) {
        desativarMensagem();
        String jsonRecebido;
        String[] funcoes = {"email","token"};
        String[] valores = {Login.getLogin(),Login.getToken()};
        json = new ToJson("visualizarCompetenciaExperiencia", funcoes, valores);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").isEmpty()){
            novaMensagem("Erro ao buscar competências!", Color.RED);
        }else{
            for (int i = 0; i < ((JSONArray) json.getFuncao("competenciaExperiencia")).length(); i++) {
                competenciaExperiencia.add(new CompetenciaExperiencia(((JSONArray) json.getFuncao("competenciaExperiencia")).getJSONObject(i).getString("competencia"),
                                        ((JSONArray) json.getFuncao("competenciaExperiencia")).getJSONObject(i).getInt("experiencia")));
            }
            tabela.setItems(competenciaExperiencia);
        }
    }

    @FXML
    void cadastrar(ActionEvent event) {
        desativarMensagem();
        lista = new ArrayList<>();
        String jsonRecebido;
        for (CompetenciaExperiencia competenciaExperiencia : tabela.getItems()) {
            lista.add(new JSONObject(competenciaExperiencia));
        }
        String[] funcoes = {"email","token"};
        String[] valores = {Login.getLogin(),Login.getToken()};
        json = new ToJson("cadastrarCompetenciaExperiencia", funcoes, valores);
        json.adicionarJson("competenciaExperiencia", lista);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            novaMensagem("Competência cadastrado com sucesso!", Color.GREEN);
        }else{
            novaMensagem("Ocorreu um erro durante o cadastro!", Color.RED);
        }
    }

    @FXML
    void excluir(ActionEvent event) {
        desativarMensagem();
        lista = new ArrayList<>();
        String jsonRecebido;
        String[] funcoes = {"email","token"};
        String[] valores = {Login.getLogin(),Login.getToken()};
        json = new ToJson("apagarCompetenciaExperiencia", funcoes, valores);
        lista.add(new JSONObject(tabela.getSelectionModel().getSelectedItem()));
        json.adicionarJson("competenciaExperiencia", lista);
        jsonRecebido = ClientApplication.enviarSocket(json.getJson());
        json.setJson(new JSONObject(jsonRecebido));
        if((json.getFuncao("status")+"").equals("201")){
            tabela.getItems().remove(tabela.getSelectionModel().getSelectedItem());
        }else{
            novaMensagem("Impossível apagar competência!", Color.RED);
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
        competencias.setItems(this.listaCompetencias);

        competenciaExperiencia = FXCollections.observableArrayList();
        columnCompetencia.setCellValueFactory(new PropertyValueFactory<>("competencia"));
        columnExperiencia.setCellValueFactory(new PropertyValueFactory<>("experiencia"));
    }

}
