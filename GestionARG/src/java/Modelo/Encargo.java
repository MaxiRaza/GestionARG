package Modelo;

public class Encargo {

    private int id_encargo, id_estado;
    private String fecha;

    public Encargo() {
    }

    public Encargo(int id_encargo, int id_estado, String fecha) {
        this.id_encargo = id_encargo;
        this.id_estado = id_estado;
        this.fecha = fecha;
    }

    public int getId_encargo() {
        return id_encargo;
    }

    public void setId_encargo(int id_encargo) {
        this.id_encargo = id_encargo;
    }

    public int getId_estado() {
        return id_estado;
    }

    public void setId_estado(int id_estado) {
        this.id_estado = id_estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
 
}
