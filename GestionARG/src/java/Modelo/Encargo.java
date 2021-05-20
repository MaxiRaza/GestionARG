package Modelo;

public class Encargo {

    private int id_encargo;
    private String fecha;

    public Encargo() {
    }

    public Encargo(int id_encargo, String fecha) {
        this.id_encargo = id_encargo;
        this.fecha = fecha;
    }

    public int getId_encargo() {
        return id_encargo;
    }

    public void setId_encargo(int id_encargo) {
        this.id_encargo = id_encargo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
