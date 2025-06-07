package org.example.feriasdearte.Objetos;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class Entradas {

    private int id;
    private LocalDate fecha;
    private int precio;
    private int feria;
    private int asistente;

    public Entradas(LocalDate fecha, int precio, int feria, int asistente) {
        this.fecha = fecha;
        this.precio = precio;
        this.feria = feria;
        this.asistente = asistente;
    }

    public Entradas(int id, LocalDate fecha, int precio, int feria, int asistente) {
        this.id = id;
        this.fecha = fecha;
        this.precio = precio;
        this.feria = feria;
        this.asistente = asistente;
    }

}
