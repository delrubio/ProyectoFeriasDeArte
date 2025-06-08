package org.example.feriasdearte.Mantenimientos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.feriasdearte.Objetos.Entradas;
import org.example.feriasdearte.Objetos.Ventas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ManVentas {

    static ObservableList<Ventas> listaVentas = FXCollections.observableArrayList();

    public static ObservableList<Ventas> consultar(Connection conexion){

        String query = "SELECT * FROM ventas";

        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("ventaid");
                LocalDate fecha = resultado.getDate("fechaventa").toLocalDate();
                int precio = resultado.getInt("precioventa");
                int obra = resultado.getInt("obraid");
                int asistente = resultado.getInt("asistenteid");

                Ventas venta = new Ventas(id, fecha, precio, obra, asistente);

                listaVentas.add(venta);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaVentas;
    }

    public static void factura(Ventas venta){

        File file = new File("src/main/java/org/example/feriasdearte/Facturas/factura-venta-" + venta.getFecha() + ".txt");

        try {
            BufferedWriter facturar = new BufferedWriter(new FileWriter(file));

            facturar.write("--------------------------------------");
            facturar.write("\n Feria de Arte");
            facturar.write("\n--------------------------------------");
            facturar.write("\nId de Compra:                   " + venta.getId());
            facturar.write("\nFecha de la compra:    " + venta.getFecha());
            facturar.write("\nIdentificador de Obra:          " + venta.getObra());
            facturar.write("\nID de Comprador:                " + venta.getAsistente());
            facturar.write("\n                         ------------");
            facturar.write("\nSubtotal                       " + venta.getPrecio() + "€");

            facturar.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
