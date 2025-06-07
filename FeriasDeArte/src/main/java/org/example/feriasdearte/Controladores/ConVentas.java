package org.example.feriasdearte.Controladores;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.example.feriasdearte.Application;
import org.example.feriasdearte.Conexion.Connect;
import org.example.feriasdearte.Mantenimientos.ManVentas;
import org.example.feriasdearte.Objetos.Ventas;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;

public class ConVentas {

    Connection BD;

    @FXML
    TableView<Ventas> tablaVentas;

    @FXML
    TableColumn<Ventas, Integer> idTable;

    @FXML
    TableColumn<Ventas, LocalDate> fechaTable;

    @FXML
    TableColumn<Ventas, Integer> precioTable;

    @FXML
    TableColumn<Ventas, Integer> obraTable;

    @FXML
    TableColumn<Ventas, Integer> asistenteTable;

    @FXML
    private Button inicio;

    @FXML
    public void initialize(){
        BD = Connect.conectar();

        idTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getId()).asObject());
        fechaTable.setCellValueFactory(data -> new javafx.beans.property.ReadOnlyObjectWrapper<>(data.getValue().getFecha()));
        precioTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getPrecio()).asObject());
        obraTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getObra()).asObject());
        asistenteTable.setCellValueFactory(data -> new javafx.beans.property.SimpleIntegerProperty(data.getValue().getAsistente()).asObject());

        tablaVentas.setItems(ManVentas.consultar(BD));
    }

    @FXML
    public void buttonInicio() throws IOException {
        Application.setRoot("main-view.fxml");
    }

    @FXML
    public void generarFactura(){

    }


}
