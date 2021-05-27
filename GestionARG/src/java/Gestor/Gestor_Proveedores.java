package Gestor;

import Modelo.DTO.DTO_Proveedor;
import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Proveedores {

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

    public void agregarProveedor(Proveedor p) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Proveedores (nombre, cuit, direccion, id_contacto, id_tipo_proveedor, id_clasificacion, id_marca) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCuit());
            ps.setString(3, p.getDireccion());
            ps.setInt(4, p.getId_contacto());
            ps.setInt(5, p.getId_tipo_proveedor());
            ps.setInt(6, p.getId_clasificacion());
            ps.setInt(7, p.getId_marca());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Proveedor> obtenerProveedores() {
        ArrayList<Proveedor> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_proveedor, nombre, cuit, direccion, id_contacto, id_tipo_proveedor, id_clasificacion, id_marca FROM Proveedores");
            while (rs.next()) {
                Proveedor p = new Proveedor();
                p.setId_proveedor(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCuit(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setId_contacto(rs.getInt(5));
                p.setId_tipo_proveedor(rs.getInt(6));
                p.setId_clasificacion(rs.getInt(7));
                p.setId_marca(rs.getInt(8));
                lista.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Proveedor obtenerProveedor(int id_proveedor) {
        Proveedor p = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_proveedor, nombre, cuit, direccion, id_contacto, id_tipo_proveedor, id_clasificacion, id_marca FROM Proveedores WHERE id_proveedor = ?");
            ps.setInt(1, id_proveedor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Proveedor();
                p.setId_proveedor(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCuit(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setId_contacto(rs.getInt(5));
                p.setId_tipo_proveedor(rs.getInt(6));
                p.setId_clasificacion(rs.getInt(7));
                p.setId_marca(rs.getInt(8));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return p;
    }

    public void actualizarProveedor(Proveedor p) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Proveedores SET nombre = ?, cuit = ?, direccion = ?, id_contacto = ?, id_tipo_proveedor = ?, id_clasificacion = ?, id_marca = ? WHERE id_proveedor = ?");
            ps.setString(1, p.getNombre());
            ps.setString(2, p.getCuit());
            ps.setString(3, p.getDireccion());
            ps.setInt(4, p.getId_contacto());
            ps.setInt(5, p.getId_tipo_proveedor());
            ps.setInt(6, p.getId_clasificacion());
            ps.setInt(7, p.getId_marca());
            ps.setInt(8, p.getId_proveedor());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarProveedor(int id_proveedor) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Proveedores WHERE id_proveedor = ?");
            ps.setInt(1, id_proveedor);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public DTO_Proveedor obtenerProveedorDTO(int id_proveedor) {
        DTO_Proveedor p = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_proveedor, p.nombre, p.cuit, p.direccion, p.id_contacto, c.correo, c.telefono, t.id_tipo_proveedor, p.id_clasificacion, p.id_marca, ca.id_categoria FROM Proveedores p JOIN Contactos c ON p.id_contacto = c.id_contacto JOIN Tipo_Proveedores t ON p.id_tipo_proveedor = t.id_tipo_proveedor JOIN Clasificaciones a ON p.id_clasificacion = a.id_clasificacion JOIN Marcas m ON p.id_marca = m.id_marca JOIN Categorias ca ON m.id_categoria = ca.id_categoria WHERE p.id_proveedor = ?");
            ps.setInt(1, id_proveedor);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new DTO_Proveedor();
                p.setId_proveedor(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCuit(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setId_contacto(rs.getInt(5));
                p.setCorreo(rs.getString(6));
                p.setTelefono(rs.getString(7));
                p.setId_tipo_proveedor(rs.getInt(8));
                p.setId_clasificacion(rs.getInt(9));
                p.setId_marca(rs.getInt(10));
                p.setId_categoria(rs.getInt(11));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return p;
    }

    public ArrayList<DTO_Proveedor> obtenerProveedoresDTO() {
        ArrayList<DTO_Proveedor> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_proveedor, p.nombre, p.cuit, p.direccion, c.correo, c.telefono, t.nombre, a.nombre, m.nombre, ca.nombre FROM Proveedores p JOIN Contactos c ON p.id_contacto = c.id_contacto JOIN Tipo_Proveedores t ON p.id_tipo_proveedor = t.id_tipo_proveedor JOIN Clasificaciones a ON p.id_clasificacion = a.id_clasificacion JOIN Marcas m ON p.id_marca = m.id_marca JOIN Categorias ca ON m.id_categoria = ca.id_categoria");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Proveedor p = new DTO_Proveedor();
                p.setId_proveedor(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCuit(rs.getString(3));
                p.setDireccion(rs.getString(4));
                p.setCorreo(rs.getString(5));
                p.setTelefono(rs.getString(6));
                p.setClasificacion(rs.getString(7));
                p.setTipo(rs.getString(8));
                p.setMarca(rs.getString(9));
                p.setCategoria(rs.getString(10));
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Proveedores.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

}
