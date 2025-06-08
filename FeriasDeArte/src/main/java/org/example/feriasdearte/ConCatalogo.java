package org.example.feriasdearte;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.feriasdearte.Conexion.Connect;
import org.example.feriasdearte.Mantenimientos.ManAsistentes;
import org.example.feriasdearte.Mantenimientos.ManCatalogo;
import org.example.feriasdearte.Objetos.Catalogo;

import java.io.IOException;
import java.sql.Connection;

public class ConCatalogo {

    Connection BD;

    @FXML
    private TableView<Catalogo> tablaCatalogo;

    @FXML
    private TableColumn<Catalogo, Integer> idTable;

    @FXML
    private TableColumn<Catalogo, String> nombreTable;

    @FXML
    private TableColumn<Catalogo, String> descripcionTable;

    @FXML
    private TableColumn<Catalogo, Integer> feriaTable;

    @FXML
    private TextField nombreTextField;

    @FXML
    private TextField descripcionTextField;

    @FXML
    private TextField feriaTextField;

    @FXML
    private Button guardarButton;

    @FXML
    private Button inicio;

    @FXML
    public void initialize(){
        BD = Connect.conectar();

        idTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        nombreTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getNombre()));
        descripcionTable.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getDescripcion()));
        feriaTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getFeria()).asObject());

        tablaCatalogo.setItems(ManCatalogo.consultar(BD));
    }

    @FXML
    public void buttonInicio() throws IOException {
        Application.setRoot("main-view");
    }

    @FXML
    public void onEditarButtonClickCatalogo(){

        guardarButton.setDisable(false);
        Catalogo catalogo = tablaCatalogo.getSelectionModel().getSelectedItem();

        if (catalogo != null){
            nombreTextField.setText(String.valueOf(catalogo.getNombre()));
            descripcionTextField.setText(String.valueOf(catalogo.getDescripcion()));
            feriaTextField.setText(String.valueOf(catalogo.getFeria()));
        }else {
            System.out.println("No hay filas seleccionadas");
        }

    }

    @FXML
    public void onEliminarButtonClickCatalogo(){

        Catalogo catalogo = tablaCatalogo.getSelectionModel().getSelectedItem();

        if (catalogo != null){
            ManCatalogo.borrar(BD, catalogo);
        }else {
            System.out.println("No hay filas seleccionadas");
        }

        tablaCatalogo.setItems(ManCatalogo.consultar(BD));
    }

    @FXML
    public void onAnyadirButtonClickCatalogo(){

        String nombre = nombreTextField.getText();
        String desc = descripcionTextField.getText();
        int feria = Integer.parseInt(feriaTextField.getText());

        Catalogo catalogo = new Catalogo(nombre, desc, feria);
        ManCatalogo.insertar(BD, catalogo);

        nombreTextField.clear();
        descripcionTextField.clear();
        feriaTextField.clear();

        tablaCatalogo.setItems(ManCatalogo.consultar(BD));
    }

    @FXML
    public void onGuardarButtonClickCatalogo(){

        int id = tablaCatalogo.getSelectionModel().getSelectedItem().getId();
        String nombre = nombreTextField.getText();
        String desc = descripcionTextField.getText();
        int feria = Integer.parseInt(feriaTextField.getText());

        Catalogo catalogo = new Catalogo(id, nombre, desc, feria);
        ManCatalogo.modificar(BD, catalogo, id);

        nombreTextField.clear();
        descripcionTextField.clear();
        feriaTextField.clear();

        tablaCatalogo.setItems(ManCatalogo.consultar(BD));
        guardarButton.setDisable(true);

    }

}
