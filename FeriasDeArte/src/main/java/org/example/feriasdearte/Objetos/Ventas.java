package org.example.feriasdearte.Objetos;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
public class Ventas {

    private int id;
    private LocalDate fecha;
    private int precio;
    private int obra;
    private int asistente;

    public Ventas(LocalDate fecha, int precio, int obra, int asistente) {
        this.precio = precio;
        this.fecha = fecha;
        this.obra = obra;
        this.asistente = asistente;
    }

    public Ventas(int id, LocalDate fecha, int precio, int obra, int asistente) {
        this.id = id;
        this.precio = precio;
        this.fecha = fecha;
        this.obra = obra;
        this.asistente = asistente;
    }
}
