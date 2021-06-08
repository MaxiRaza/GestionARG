package Modelo.DTO;

public class DTO_Deposito {

    private int id_deposito;
    private String deposito, sucursal;

    public DTO_Deposito() {
    }

    public DTO_Deposito(int id_deposito, String deposito, String sucursal) {
        this.id_deposito = id_deposito;
        this.deposito = deposito;
        this.sucursal = sucursal;
    }

    public int getId_deposito() {
        return id_deposito;
    }

    public void setId_deposito(int id_deposito) {
        this.id_deposito = id_deposito;
    }

    public String getDeposito() {
        return deposito;
    }

    public void setDeposito(String deposito) {
        this.deposito = deposito;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }
    
}
