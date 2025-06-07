package org.example.feriasdearte.Controladores;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.feriasdearte.Application;
import org.example.feriasdearte.Conexion.Connect;
import org.example.feriasdearte.Mantenimientos.ManEntradas;
import org.example.feriasdearte.Objetos.Entradas;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class ConEntradas {

    Connection BD;

    @FXML
    TableView<Entradas> tablaEntradas;

    @FXML
    TableColumn<Entradas, Integer> idTable;

    @FXML
    TableColumn<Entradas, LocalDate> fechaTable;

    @FXML
    TableColumn<Entradas, Integer> precioTable;

    @FXML
    TableColumn<Entradas, Integer> feriaTable;

    @FXML
    TableColumn<Entradas, Integer> asistenteTable;

    @FXML
    private Button inicio;

    @FXML
    public void initialize(){
        BD = Connect.conectar();

        idTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        fechaTable.setCellValueFactory(data -> new javafx.beans.property.ReadOnlyObjectWrapper<>(data.getValue().getFecha()));
        precioTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrecio()).asObject());
        feriaTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getFeria()).asObject());
        asistenteTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAsistente()).asObject());

        tablaEntradas.setItems(ManEntradas.consultar(BD));
    }

    @FXML
    public void buttonInicio() throws IOException {
        Application.setRoot("main-view.fxml");
    }

    @FXML
    public void generarFactura(){

    }

}
