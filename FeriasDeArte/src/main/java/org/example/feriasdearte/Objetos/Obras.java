package org.example.feriasdearte.Objetos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Obras {

    private int id;
    private String titulo;
    private String descripcion;
    private int precio;
    private String disponible;
    private int artista;


    public Obras(String titulo, String descripcion, int precio, String disponible, int artista) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.precio = precio;
        this.artista = artista;
    }

    public Obras(int id, String titulo, String descripcion, int precio, String disponible, int artista) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.disponible = disponible;
        this.precio = precio;
        this.artista = artista;
    }
}
