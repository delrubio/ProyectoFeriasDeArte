package org.example.feriasdearte.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.feriasdearte.Application;
import org.example.feriasdearte.Conexion.Connect;
import org.example.feriasdearte.Mantenimientos.ManArtistas;
import org.example.feriasdearte.Mantenimientos.ManObras;
import org.example.feriasdearte.Objetos.Obras;

import java.io.IOException;
import java.sql.Connection;

public class ConObras {

    Connection BD;

    @FXML
    private TableView<Obras> tablaObras;

    @FXML
    private TableColumn<Obras, Integer> idObra;

    @FXML
    private TableColumn<Obras, String> tituloTable;

    @FXML
    private TableColumn<Obras, String> descripcionTable;

    @FXML
    private TableColumn<Obras, Integer> precioTable;

    @FXML
    private TableColumn<Obras, String> disponibleTable;

    @FXML
    private TableColumn<Obras, Integer> artistaTable;

    @FXML
    private TextField tituloTextField;

    @FXML
    private TextField descripcionTextField;

    @FXML
    private TextField disponibleTextField;

    @FXML
    private TextField precioTextField;

    @FXML
    private TextField artistaTextField;

    @FXML
    private Button inicio;

    @FXML
    public void initialize(){
        BD = Connect.conectar();

        idObra.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        tituloTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTitulo()));
        descripcionTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        precioTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrecio()).asObject());
        disponibleTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDisponible()));
        artistaTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getArtista()).asObject());

        tablaObras.setItems(ManObras.consultar(BD));
    }

    @FXML
    public void buttonInicio() throws IOException {
        Application.setRoot("main-view.fxml");
    }

    @FXML
    public void onEditarButtonClickObras(){

        Obras obras = tablaObras.getSelectionModel().getSelectedItem();

        if (obras != null){
            tituloTextField.setText(String.valueOf(obras.getTitulo()));
            descripcionTextField.setText(String.valueOf(obras.getDescripcion()));
            precioTextField.setText(String.valueOf(obras.getPrecio()));
            disponibleTextField.setText(String.valueOf(obras.getDisponible()));
            artistaTextField.setText(String.valueOf(obras.getArtista()));
        }else {
            System.out.println("No hay filas seleccionadas");
        }



    }

    @FXML
    public void onEliminarButtonClickObras(){

        Obras obras = tablaObras.getSelectionModel().getSelectedItem();

        if (obras != null){
            ManObras.borrar(BD, obras);
        }else {
            System.out.println("No hay filas seleccionadas");
        }

    }

    @FXML
    public void onAnyadirButtonClickObras(){

        String titulo = tituloTextField.getText();
        String desc = descripcionTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        String dispo = disponibleTextField.getText();
        int artista = Integer.parseInt(artistaTextField.getText());

        Obras obras = new Obras(titulo, desc, precio, dispo, artista);
        ManObras.insertar(BD, obras);

        tituloTextField.clear();
        descripcionTextField.clear();
        precioTextField.clear();
        disponibleTextField.clear();
        artistaTextField.clear();

        tablaObras.setItems(ManObras.consultar(BD));

    }

    @FXML
    public void onGuardarButtonClickObras(){

        int id = tablaObras.getSelectionModel().getSelectedItem().getId();
        String titulo = tituloTextField.getText();
        String desc = descripcionTextField.getText();
        int precio = Integer.parseInt(precioTextField.getText());
        String dispo = disponibleTextField.getText();
        int artista = Integer.parseInt(artistaTextField.getText());

        Obras obras = new Obras(titulo, desc, precio, dispo, artista);
        ManObras.modificar(BD, obras, id);

        tituloTextField.clear();
        descripcionTextField.clear();
        precioTextField.clear();
        disponibleTextField.clear();
        artistaTextField.clear();

        tablaObras.setItems(ManObras.consultar(BD));
    }

}
