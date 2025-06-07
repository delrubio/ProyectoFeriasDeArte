package org.example.feriasdearte.Objetos;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Catalogo {

    private int id;
    private String nombre;
    private String descripcion;
    private int feria;

    public Catalogo(String nombre, String descripcion, int feria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.feria = feria;
    }

    public Catalogo(int id, String nombre, String descripcion, int feria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.feria = feria;
    }
}
