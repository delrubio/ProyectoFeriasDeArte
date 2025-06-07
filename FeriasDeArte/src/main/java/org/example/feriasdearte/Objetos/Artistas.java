package org.example.feriasdearte.Objetos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Artistas {

    private int artistaid;
    private String nombre ;
    private String biografia;
    private String email;
    private String telefono ;

    public Artistas(int id, String nombre, String email, String telefono) {
        this.artistaid = id;
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(int id, String nombre, String biografia, String email, String telefono) {
        this.artistaid = id;
        this.nombre = nombre;
        this.biografia = biografia;
        this.email = email;
        this.telefono = telefono;
    }

    public Artistas(String nombre, String biografia, String email, String telefono) {
        this.nombre = nombre;
        this.biografia = biografia;
        this.email = email;
        this.telefono = telefono;
    }

}
