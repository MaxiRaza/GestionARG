package Modelo;

public class Encargo {

    private int id_encargo, id_proveedor;
    private String fecha;

    public Encargo() {
    }

    public Encargo(int id_encargo, int id_proveedor, String fecha) {
        this.id_encargo = id_encargo;
        this.id_proveedor = id_proveedor;
        this.fecha = fecha;
    }

    public int getId_encargo() {
        return id_encargo;
    }

    public void setId_encargo(int id_encargo) {
        this.id_encargo = id_encargo;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
}
