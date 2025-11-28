package modelo;

import java.time.LocalDate;

public class Miembro {
    private int idMiembro;
    private String nombre;
    private String tipoMiembro;
    private LocalDate fechaNac;
    private int idCiudad;

    public Miembro(int idMiembro, String nombre, String tipoMiembro, LocalDate fechaNac, int idCiudad) {
        this.idMiembro = idMiembro;
        this.nombre = nombre;
        this.tipoMiembro = tipoMiembro;
        this.fechaNac = fechaNac;
        this.idCiudad = idCiudad;
    }

    // getters
    public int getIdMiembro() { return idMiembro; }
    public String getNombre() { return nombre; }
    public String getTipoMiembro() { return tipoMiembro; }
    public LocalDate getFechaNac() { return fechaNac; }
    public int getIdCiudad() { return idCiudad; }
}
