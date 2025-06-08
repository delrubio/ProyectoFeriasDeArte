package org.example.feriasdearte.Mantenimientos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.feriasdearte.Objetos.Asistentes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManAsistentes {

    static ObservableList<Asistentes> listaAsistentes = FXCollections.observableArrayList();

    public static ObservableList<Asistentes> consultar(Connection conexion){

        String query = "SELECT * FROM asistentes";

        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("asistenteid");
                String nombre = resultado.getString("nombre");
                String email = resultado.getString("email");
                String telfeono = resultado.getString("telefono");

                Asistentes asistente = new Asistentes(id, nombre, email, telfeono);

                listaAsistentes.add(asistente);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaAsistentes;
    }

    public static void insertar(Connection conexion, Asistentes asistente) {

        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO asistentes (nombre, email, telefono) VALUES ('");
        query.append(asistente.getNombre());
        query.append("','");
        query.append(asistente.getEmail());
        query.append("','");
        query.append(asistente.getTelefono());
        query.append("')");

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query.toString());
            System.out.println("-- ASISTENTE INSERTADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void borrar(Connection conexion, Asistentes asistente){

        String query = "DELETE FROM asistentes WHERE asistenteid ='" + asistente.getId() + "'";

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- ASISTENTE ELIMINADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void modificar (Connection conexion, Asistentes asistente, int id){
        String query;

        query = "UPDATE asistentes SET nombre = '" + asistente.getNombre()
                + "', email = '" + asistente.getEmail()
                + "', telefono = '" + asistente.getTelefono()
                + "' WHERE asistenteid = '" + id + "'";

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- ASISTENTE MODIFICADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
