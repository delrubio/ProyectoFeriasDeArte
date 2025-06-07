package org.example.feriasdearte.Controladores;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.feriasdearte.Application;
import org.example.feriasdearte.Conexion.Connect;
import org.example.feriasdearte.Mantenimientos.ManArtistas;
import org.example.feriasdearte.Objetos.Artistas;

import java.io.IOException;
import java.sql.Connection;

public class ConArtistas {

    Connection BD;

    @FXML
    private TableView<Artistas> tablaArtistas;

    @FXML
    private TableColumn<Artistas, Integer> idTable;

    @FXML
    private TableColumn<Artistas, String> nombreTable;

    @FXML
    private TableColumn<Artistas, String> biografiaTable;

    @FXML
    private TableColumn<Artistas, String> emailTable;

    @FXML
    private TableColumn<Artistas, String> telefonoTable;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField biografiaTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button inicio;

    @FXML
    public void initialize(){
        BD = Connect.conectar();

        idTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getArtistaid()).asObject());
        nombreTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        biografiaTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getBiografia()));
        emailTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        telefonoTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));

        tablaArtistas.setItems(ManArtistas.consultar(BD));
    }

    @FXML
    public void buttonInicio() throws IOException {
        Application.setRoot("main-view.fxml");
    }

    @FXML
    public void onEditarButtonClickArtistas(ActionEvent actionEvent){

        Artistas artistas = tablaArtistas.getSelectionModel().getSelectedItem();

        if (artistas !=  null){
            nombreTextField.setText(String.valueOf(artistas.getNombre()));
            biografiaTextField.setText(String.valueOf(artistas.getBiografia()));
            emailTextField.setText(String.valueOf(artistas.getEmail()));
            telefonoTextField.setText(String.valueOf(artistas.getTelefono()));
        }else {
            System.out.println("No hay filas seleccionadas");
        }

    }

    @FXML
    public void onEliminarButtonClickArtistas(){

        Artistas artista = tablaArtistas.getSelectionModel().getSelectedItem();

        if (artista != null){
            ManArtistas.borrar(BD, artista);
        }else {
            System.out.println("No hay filas seleccionadas");
        }

    }

    @FXML
    public void onAnyadirButtonClickArtistas(){

        String nombre = nombreTextField.getText();
        String bio = biografiaTextField.getText();
        String email = emailTextField.getText();
        String telefono = telefonoTextField.getText();

        Artistas artista = new Artistas(nombre, bio, email, telefono);
        ManArtistas.insertar(BD, artista);

        nombreTextField.clear();
        biografiaTextField.clear();
        emailTextField.clear();
        telefonoTextField.clear();

        tablaArtistas.setItems(ManArtistas.consultar(BD));

    }

    @FXML
    public void onGuardarButtonClickArtistas(){

        int id = tablaArtistas.getSelectionModel().getSelectedItem().getArtistaid();
        String nombre = nombreTextField.getText();
        String bio = biografiaTextField.getText();
        String email = emailTextField.getText();
        String telefono = telefonoTextField.getText();

        Artistas artista = new Artistas(id, nombre, bio, email, telefono);
        ManArtistas.modificar(BD, artista, id);

        nombreTextField.clear();
        biografiaTextField.clear();
        emailTextField.clear();
        telefonoTextField.clear();

        tablaArtistas.setItems(ManArtistas.consultar(BD));
    }

}
