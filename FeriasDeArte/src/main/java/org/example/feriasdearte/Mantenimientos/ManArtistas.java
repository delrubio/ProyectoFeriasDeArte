package org.example.feriasdearte.Mantenimientos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManArtistas {

    public static ObservableList<org.example.feriasdearte.Objetos.Artistas> consultar(Connection conexion){

        String query = "SELECT * FROM artistas";
        ObservableList<org.example.feriasdearte.Objetos.Artistas> listaArtistas = FXCollections.observableArrayList();
        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("artistaid");
                String nombre = resultado.getString("nombre");
                String biografia = resultado.getString("biografia");
                String email = resultado.getString("email");
                String telfeono = resultado.getString("telefono");

                if (biografia == null) {
                    listaArtistas.add(new org.example.feriasdearte.Objetos.Artistas(id, nombre, email, telfeono));
                }else {
                    listaArtistas.add(new org.example.feriasdearte.Objetos.Artistas(id, nombre, biografia, email, telfeono));
                }

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return listaArtistas;
    }

    public static void insertar(Connection conexion, org.example.feriasdearte.Objetos.Artistas artista) {

        StringBuilder query = new StringBuilder();

        if (artista.getBiografia() == null){
            query.append("INSERT INTO artistas (nombre, email, telefono) VALUES ('");
            query.append(artista.getNombre());
            query.append("','");
            query.append(artista.getEmail());
            query.append("','");
            query.append(artista.getTelefono());
            query.append("')");
        }else {
            query.append("INSERT INTO artistas  (nombre, biografia, email, telefono) VALUES ('");
            query.append(artista.getNombre());
            query.append("','");
            query.append(artista.getBiografia());
            query.append("','");
            query.append(artista.getEmail());
            query.append("','");
            query.append(artista.getTelefono());
            query.append("')");
        }

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query.toString());
            System.out.println("-- ARTISTA INSERTADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void borrar(Connection conexion, org.example.feriasdearte.Objetos.Artistas artista){

        String query = "DELETE FROM artistas WHERE artistaid ='" + artista.getArtistaid() + "'";

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- ARTISTA ELIMINADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void modificar (Connection conexion, org.example.feriasdearte.Objetos.Artistas artista, int id){
        String query;

        if (artista.getBiografia() == null) {
            query = "UPDATE artistas SET nombre = '" + artista.getNombre()
                    + "', telefono = '" + artista.getEmail()
                    + "', email = '" + artista.getTelefono()
                    + "' WHERE artistaid = '" + id + "'";
        }else{
            query = "UPDATE artistas SET nombre = '" + artista.getNombre()
                    + "', biografia = '" + artista.getBiografia()
                    + "', telefono = '" + artista.getEmail()
                    + "', email = '" + artista.getTelefono()
                    + "' WHERE artistaid = '" + id + "'";
        }

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- ARTISTA MODIFICADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
