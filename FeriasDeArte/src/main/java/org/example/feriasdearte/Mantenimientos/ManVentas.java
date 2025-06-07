package org.example.feriasdearte.Mantenimientos;

import javafx.collections.ObservableList;
import org.example.feriasdearte.Objetos.Entradas;
import org.example.feriasdearte.Objetos.Ventas;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManVentas {

    static ObservableList<Ventas> listaVentas;

    public static ObservableList<Ventas> consultar(Connection conexion){

        String query = "SELECT * FROM ventas";

        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("ventaid");
                String fecha = resultado.getString("fechaventa");
                int precio = resultado.getInt("precioventa");
                int obra = resultado.getInt("obraid");
                int asistente = resultado.getInt("asistenteid");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                Ventas venta = new Ventas(id, LocalDate.parse(fecha, formatter), precio, obra, asistente);

                listaVentas.add(venta);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaVentas;
    }

}
