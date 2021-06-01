package Gestor;

import Modelo.DTO.DTO_Encargo;
import Modelo.Detalle_Encargo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Detalle_Encargos {

    private Connection conexion;
    private String CONN = "jdbc:sqlserver://localhost:1433;databaseName=GestionARG";
    private String USER = "sa";
    private String PASS = "1234";

    private void abrirConexion() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conexion = DriverManager.getConnection(CONN, USER, PASS);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void cerrarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                conexion.close();
            }
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    public void agregarDetalleEncargo(Detalle_Encargo de) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Detalle_Encargos (cantidad, importe, id_producto, id_encargo, id_proveedor, vigencia) VALUES (?,?,?,?,?,1)");
            ps.setFloat(1, de.getCantidad());
            ps.setFloat(2, de.getImporte());
            ps.setInt(3, de.getId_producto());
            ps.setInt(4, de.getId_encargo());
            ps.setInt(5, de.getId_proveedor());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Detalle_Encargo> obtenerDetalleEncargos() {
        ArrayList<Detalle_Encargo> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_detalle_encargo, cantidad, importe, id_producto, id_encargo, id_proveedor FROM Detalle_Encargos WHERE vigencia = 1");
            while (rs.next()) {
                Detalle_Encargo de = new Detalle_Encargo();
                de.setId_detalle_encargo(rs.getInt(1));
                de.setCantidad(rs.getFloat(2));
                de.setImporte(rs.getFloat(3));
                de.setId_producto(rs.getInt(4));
                de.setId_encargo(rs.getInt(5));
                de.setId_proveedor(rs.getInt(6));
                lista.add(de);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Detalle_Encargo obtenerDetalleEncargo(int id_detalle_encargo) {
        Detalle_Encargo de = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_detalle_encargo, cantidad, importe, id_producto, id_proveedor, id_encargo  FROM Detalle_Encargos WHERE id_detalle_encargo = ?");
            ps.setInt(1, id_detalle_encargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                de = new Detalle_Encargo();
                de.setId_detalle_encargo(rs.getInt(1));
                de.setCantidad(rs.getFloat(2));
                de.setImporte(rs.getFloat(3));
                de.setId_producto(rs.getInt(4));
                de.setId_proveedor(rs.getInt(5));
                de.setId_encargo(rs.getInt(6));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return de;
    }

    public void actualizarDetalleEncargo(Detalle_Encargo de) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Detalle_Encargos SET cantidad = ?, importe = ?, id_producto = ?, id_encargo = ?, id_proveedor = ?  WHERE id_detalle_encargo = ?");
            ps.setFloat(1, de.getCantidad());
            ps.setFloat(2, de.getImporte());
            ps.setInt(3, de.getId_producto());
            ps.setInt(4, de.getId_encargo());
            ps.setInt(5, de.getId_proveedor());
            ps.setInt(6, de.getId_detalle_encargo());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarDetalleEncargo(int id_detalle_encargo) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Detalle_Encargos SET vigencia = 0 WHERE id_detalle_encargo = ?");
            ps.setInt(1, id_detalle_encargo);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<DTO_Encargo> obtenerDetalleEncargosDTO() {
        ArrayList<DTO_Encargo> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT d.id_encargo, d.id_detalle_encargo, m.nombre, p.nombre, d.cantidad, pr.nombre  FROM Detalle_Encargos d JOIN Productos p ON p.id_producto = d.id_producto JOIN Proveedores pr ON pr.id_proveedor = d.id_proveedor JOIN Marcas m ON p.id_marca = m.id_marca JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE d.vigencia = 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Encargo de = new DTO_Encargo();
                de.setId_encargo(rs.getInt(1));
                de.setId_detalle_encargo(rs.getInt(2));
                de.setMarca(rs.getString(3));
                de.setProducto(rs.getString(4));
                de.setCantidad(rs.getFloat(5));
                de.setProveedor(rs.getString(6));

                lista.add(de);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public DTO_Encargo obtenerProductoDTO(int id_encargo) {
        DTO_Encargo de = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT d.id_encargo, d.id_detalle_encargo, c.id_categoria, m.id_marca, p.id_producto, d.cantidad, pr.id_proveedor FROM Detalle_Encargos d JOIN Productos p ON p.id_producto = d.id_producto JOIN Proveedores pr ON pr.id_proveedor = d.id_proveedor JOIN Marcas m ON p.id_marca = m.id_marca JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p.id_encargo = ?");
            ps.setInt(1, id_encargo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                de = new DTO_Encargo();
                de.setId_encargo(rs.getInt(1));
                de.setId_detalle_encargo(rs.getInt(2));
                de.setId_categoria(rs.getInt(3));
                de.setMarca(rs.getString(4));
                de.setProducto(rs.getString(5));
                de.setCantidad(rs.getFloat(6));
                de.setProveedor(rs.getString(7));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Detalle_Encargos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return de;
    }

}
