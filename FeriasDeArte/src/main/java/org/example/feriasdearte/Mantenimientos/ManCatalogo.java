package org.example.feriasdearte.Mantenimientos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.example.feriasdearte.Objetos.Artistas;
import org.example.feriasdearte.Objetos.Asistentes;
import org.example.feriasdearte.Objetos.Catalogo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ManCatalogo {

    static ObservableList<Catalogo> catalogoList = FXCollections.observableArrayList();

    public static ObservableList<Catalogo> consultar(Connection conexion){

        String query = "SELECT * FROM catalogos";

        Statement stmt;
        ResultSet resultado;
        try {
            stmt = conexion.createStatement();
            resultado = stmt.executeQuery(query);
            while (resultado.next()) {
                int id = resultado.getInt("catalogoid");
                String nombre = resultado.getString("nombre");
                String desc = resultado.getString("descripcion");
                int feria = resultado.getInt("feriaid");

                Catalogo catalogo = new Catalogo(id, nombre, desc, feria);

                catalogoList.add(catalogo);

            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return catalogoList;
    }

    public static void insertar(Connection conexion, Catalogo catalogo) {

        StringBuilder query = new StringBuilder();

        query.append("INSERT INTO catalogos (nombre, descripcion, feriaid) VALUES ('");
        query.append(catalogo.getNombre());
        query.append("','");
        query.append(catalogo.getDescripcion());
        query.append("','");
        query.append(catalogo.getFeria());
        query.append("')");

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query.toString());
            System.out.println("-- CATALOGO INSERTADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void borrar(Connection conexion, Catalogo catalogo){

        String query = "DELETE FROM catalogos WHERE catalogoid ='" + catalogo.getId() + "'";

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- CATALOGO ELIMINADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void modificar (Connection conexion, Catalogo catalogo, int id){
        String query;

        query = "UPDATE catalogos SET nombre = '" + catalogo.getNombre()
                + "', descripcion = '" + catalogo.getDescripcion()
                + "', feriaid = '" + catalogo.getFeria()
                + "' WHERE catalogoid = '" + id + "'";

        Statement stmt;
        try {
            stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            System.out.println("-- CATALOGO MODIFICADO --");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}
