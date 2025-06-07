package org.example.feriasdearte;

import com.almasb.fxgl.entity.action.Action;
import com.dlsc.formsfx.model.validators.StringLengthValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.example.feriasdearte.Mantenimientos.ManArtistas;
import org.example.feriasdearte.Objetos.*;

import java.io.IOException;
import java.sql.Connection;

public class Controller {

    @FXML
    private TableView<Asistentes> asistentesTable;

    @FXML
    private TableView<Artistas> artistasTable;

    @FXML
    private TableView<Catalogo> catalogoTable;

    @FXML
    private TableView<Entradas> entradasTable;

    @FXML
    private TableView<Obras> obrasTable;

    @FXML
    private TableView<Ventas> ventasTable;

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

    @FXML
    private TextField nombreTextField;
    private TextField biografiaTextField;
    private TextField telefonoTextField;
    private TextField emailTextField;

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
        artistasTable.setItems(ManArtistas.consultar(BD));
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

   @FXML
    public void onEditarButtonClickAsistentes(){

   }

   @FXML
    public void onEliminarButtonClickAsistentes(){

   }

   @FXML
    public void onAnyadirButtonClickAsistentes(){

   }

   @FXML
    public void onGuardarButtonClickAsistentes(){

   }

    @FXML
    public void onEditarButtonClickArtistas(ActionEvent actionEvent){

        Artistas artistas = artistasTable.getSelectionModel().getSelectedItem();

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

        Artistas artista = artistasTable.getSelectionModel().getSelectedItem();

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

        artistasTable.setItems(ManArtistas.consultar(BD));

    }

    @FXML
    public void onGuardarButtonClickArtistas(){

        int id = artistasTable.getSelectionModel().getSelectedItem().getArtistaid();
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

        artistasTable.setItems(ManArtistas.consultar(BD));
    }

    @FXML
    public void onEditarButtonClickObras(){

    }

    @FXML
    public void onEliminarButtonClickObras(){

    }

    @FXML
    public void onAnyadirButtonClickObras(){

    }

    @FXML
    public void onGuardarButtonClickObras(){

    }

    @FXML
    public void onEditarButtonClickCatalogo(){

    }

    @FXML
    public void onEliminarButtonClickCatalogo(){

    }

    @FXML
    public void onAnyadirButtonClickCatalogo(){

    }

    @FXML
    public void onGuardarButtonClickCatalogo(){

    }

    @FXML
    public void onEditarButtonClickEntradas(){

    }

    @FXML
    public void onEliminarButtonClickEntradas(){

    }

    @FXML
    public void onAnyadirButtonClickEntradas(){

    }

    @FXML
    public void onGuardarButtonClickEntradas(){

    }

    @FXML
    public void onGenerarFacturaVentas(){

    }
}