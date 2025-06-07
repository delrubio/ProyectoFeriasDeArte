package org.example.feriasdearte.Objetos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Asistentes {

    private int id;
    private String nombre;
    private String email;
    private String telefono;

    public Asistentes(String nombre, String email, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }

    public Asistentes(int id, String nombre, String email, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
    }
}
