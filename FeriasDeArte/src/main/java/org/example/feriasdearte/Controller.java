package org.example.feriasdearte;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.sql.Connection;

public class Controller {

    @FXML
    private Button asistentesButton;

    @FXML
    private Button artistasButton;

    @FXML
    private Button obrasButton;

    @FXML
    private Button catalogoButton;

    @FXML
    private Button entradasButton;

    @FXML
    private Button ventasButton;

    @FXML
    private Button inicio;


    Connection BD;
    @FXML
    public void initialize(){
//        BD = Connect.conectar();
    }

    @FXML
    public void buttonInicio() throws IOException {
        Application.setRoot("main-view.fxml");
    }

    @FXML
    public void onasistentesButton() throws IOException {
        Application.setRoot("asistentes-view.fxml");
    }

    @FXML
    public void onartistasButton() throws IOException {
        Application.setRoot("artistas-view.fxml");
    }

    @FXML
    public void onobrasButton() throws IOException {
        Application.setRoot("obras-view.fxml");
    }

    @FXML
    public void oncatalogoButton() throws IOException {
        Application.setRoot("catalogo-view.fxml");
    }

    @FXML
    public void onentradasButton() throws IOException {
        Application.setRoot("entradas-view.fxml.fxml");
    }

    @FXML
    public void onventasButton() throws IOException {
        Application.setRoot("ventas-view.fxml");
   }
}