package org.example.feriasdearte;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.feriasdearte.Conexion.Connect;
import org.example.feriasdearte.Mantenimientos.ManAsistentes;
import org.example.feriasdearte.Objetos.Asistentes;

import java.io.IOException;
import java.sql.Connection;

public class ConAsistentes {

    Connection BD;

    @FXML
    private TableView<Asistentes> tablaAsistentes;

    @FXML
    private TableColumn<Asistentes, Integer> idTable;

    @FXML
    private TableColumn<Asistentes, String> nombreTable;

    @FXML
    private TableColumn<Asistentes, String> emailTable;

    @FXML
    private TableColumn<Asistentes, String> telefonoTable;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField telefonoTextField;

    @FXML
    private TextField emailTextField;

    @FXML
    private Button guardarButton;

    @FXML
    private Button inicio;

    @FXML
    public void initialize(){
        BD = Connect.conectar();

        idTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nombreTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        emailTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));
        telefonoTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getTelefono()));

        tablaAsistentes.setItems(ManAsistentes.consultar(BD));
    }

    @FXML
    public void buttonInicio() throws IOException {
        Application.setRoot("main-view");
    }

    @FXML
    public void onEditarButtonClickAsistentes(ActionEvent actionEvent){

        guardarButton.setDisable(false);
        Asistentes asistente = tablaAsistentes.getSelectionModel().getSelectedItem();

        if (asistente !=  null){
            nombreTextField.setText(String.valueOf(asistente.getNombre()));
            emailTextField.setText(String.valueOf(asistente.getEmail()));
            telefonoTextField.setText(String.valueOf(asistente.getTelefono()));
        }else {
            System.out.println("No hay filas seleccionadas");
        }

    }

    @FXML
    public void onEliminarButtonClickAsistentes(){

        Asistentes asistente = tablaAsistentes.getSelectionModel().getSelectedItem();

        if (asistente != null){
            ManAsistentes.borrar(BD, asistente);
        }else {
            System.out.println("No hay filas seleccionadas");
        }

        tablaAsistentes.setItems(ManAsistentes.consultar(BD));

    }

    @FXML
    public void onAnyadirButtonClickAsistentes(){

        String nombre = nombreTextField.getText();
        String email = emailTextField.getText();
        String telefono = telefonoTextField.getText();

        Asistentes artista = new Asistentes(nombre, email, telefono);
        ManAsistentes.insertar(BD, artista);

        nombreTextField.clear();
        emailTextField.clear();
        telefonoTextField.clear();

        tablaAsistentes.setItems(ManAsistentes.consultar(BD));

    }

    @FXML
    public void onGuardarButtonClickAsistentes(){

        int id = tablaAsistentes.getSelectionModel().getSelectedItem().getId();
        String nombre = nombreTextField.getText();
        String email = emailTextField.getText();
        String telefono = telefonoTextField.getText();

        Asistentes artista = new Asistentes(id, nombre, email, telefono);
        ManAsistentes.modificar(BD, artista, id);

        nombreTextField.clear();
        emailTextField.clear();
        telefonoTextField.clear();

        tablaAsistentes.setItems(ManAsistentes.consultar(BD));
        guardarButton.setDisable(true);
    }

}
