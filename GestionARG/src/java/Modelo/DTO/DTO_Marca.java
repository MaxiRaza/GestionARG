package Modelo.DTO;

public class DTO_Marca {
    
    private int id_marca;
    private String marca, categoria;

    public DTO_Marca() {
    }

    public DTO_Marca(int id_marca, String marca, String categoria) {
        this.id_marca = id_marca;
        this.marca = marca;
        this.categoria = categoria;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
