package org.example.feriasdearte.Mantenimientos;

import javafx.collections.ObservableList;
import org.example.feriasdearte.Objetos.Entradas;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ManEntradas {

    static ObservableList<Entradas> listaEntradas;

    public static ObservableList<Entradas> consultar(Connection conexion){

        String query = "SELECT * FROM entradas";

        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("entradaid");
                String fecha = resultado.getString("fechacompra");
                int precio = resultado.getInt("precio");
                int feria = resultado.getInt("feriaid");
                int asistente = resultado.getInt("asistenteid");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                Entradas entradas = new Entradas(id, LocalDate.parse(fecha, formatter), precio, feria, asistente);

                listaEntradas.add(entradas);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaEntradas;
    }

}
