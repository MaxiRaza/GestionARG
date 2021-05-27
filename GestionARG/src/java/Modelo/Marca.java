package Modelo;

public class Marca {

    private int id_marca, id_categoria;
    private String nombre;

    public Marca() {
    }

    public Marca(int id_marca, int id_categoria, String nombre) {
        this.id_marca = id_marca;
        this.id_categoria = id_categoria;
        this.nombre = nombre;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
