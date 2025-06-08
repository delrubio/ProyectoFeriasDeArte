package org.example.feriasdearte.Mantenimientos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.feriasdearte.Objetos.Obras;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManObras {

    static ObservableList<Obras> listaObras = FXCollections.observableArrayList();

    public static ObservableList<Obras> consultar(Connection conexion){

        String query = "SELECT * FROM obras";

        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("obraid");
                String titulo = resultado.getString("titulo");
                String desc = resultado.getString("descripcion");
                int precio = resultado.getInt("precio");
                String dispo = resultado.getString("disponible");
                int artista = resultado.getInt("artistaid");

                Obras obra = new Obras(id, titulo, desc, precio, dispo, artista);

                listaObras.add(obra);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaObras;
    }

    public static void insertar(Connection conexion, Obras obras) {

        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO obras (titulo, descripcion, precio, disponible, artistaid) VALUES ('");
        query.append(obras.getTitulo());
        query.append("','");
        query.append(obras.getDescripcion());
        query.append("','");
        query.append(obras.getPrecio());
        query.append("','");
        query.append(obras.getDisponible());
        query.append("','");
        query.append(obras.getArtista());
        query.append("')");

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query.toString());
            System.out.println("-- OBRA INSERTADA --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void borrar(Connection conexion, Obras obras){

        String query = "DELETE FROM obras WHERE obraid ='" + obras.getId() + "'";

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- OBRA ELIMINADA --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void modificar (Connection conexion, Obras obras, int id){
        String query;

        query = "UPDATE obras SET titulo = '" + obras.getTitulo()
                + "', descripcion = '" + obras.getDescripcion()
                + "', precio = '" + obras.getPrecio()
                + "', disponible = '" + obras.getDisponible()
                + "', artistaid = '" + obras.getArtista()
                + "' WHERE obraid = '" + id + "'";

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- OBRA MODIFICADA --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
