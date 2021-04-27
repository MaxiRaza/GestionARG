package Modelo;

public class Clasificacion {

    private int id_clasificacion;
    private String nombre;

    public Clasificacion() {
    }

    public Clasificacion(int id_clasificacion, String nombre) {
        this.id_clasificacion = id_clasificacion;
        this.nombre = nombre;
    }

    public int getId_clasificacion() {
        return id_clasificacion;
    }

    public void setId_clasificacion(int id_clasificacion) {
        this.id_clasificacion = id_clasificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}