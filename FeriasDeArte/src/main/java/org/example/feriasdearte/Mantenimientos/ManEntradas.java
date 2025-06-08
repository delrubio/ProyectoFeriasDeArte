package org.example.feriasdearte.Mantenimientos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.feriasdearte.Objetos.Entradas;
import org.example.feriasdearte.Objetos.Ventas;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ManEntradas {

    static ObservableList<Entradas> listaEntradas = FXCollections.observableArrayList();

    public static ObservableList<Entradas> consultar(Connection conexion){

        String query = "SELECT * FROM entradas";

        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("entradaid");
                LocalDate fecha = resultado.getDate("fechacompra").toLocalDate();
                int precio = resultado.getInt("precio");
                int feria = resultado.getInt("feriaid");
                int asistente = resultado.getInt("asistenteid");

                Entradas entradas = new Entradas(id, fecha, precio, feria, asistente);

                listaEntradas.add(entradas);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaEntradas;
    }

    public static void facturar(Entradas entradas) throws IOException {

        File file = new File("src/main/java/org/example/feriasdearte/Facturas/factura-entrada-" + entradas.getFecha() + ".txt");

        try {
            BufferedWriter facturar = new BufferedWriter(new FileWriter(file));

            facturar.write("--------------------------------------");
            facturar.write("\n Feria de Arte");
            facturar.write("\n--------------------------------------");
            facturar.write("\nId de Compra:                     " + entradas.getId());
            facturar.write("\nFecha de la compra:     " + entradas.getFecha());
            facturar.write("\nIdentificador de Feria:           " + entradas.getFeria());
            facturar.write("\nID de Comprador:                  " + entradas.getAsistente());
            facturar.write("\n                         ------------");
            facturar.write("\nSubtotal                       " + entradas.getPrecio() + "€");

            facturar.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
