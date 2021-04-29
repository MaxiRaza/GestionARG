package Gestor;

import Modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Usuarios {

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

    public void agregarUsuario(Usuario u) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Usuarios (nombre, apellido, documento, fecha_nac, direccion, id_rol, id_contacto) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setDouble(3, u.getDocumento());
            ps.setString(4, u.getFecha_nac());
            ps.setString(5, u.getDireccion());
            ps.setInt(6, u.getId_rol());
            ps.setInt(7, u.getId_contacto());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Usuario> obtenerUsuarios() {
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_usuario, nombre, apellido, documento, fecha_nac, direccion, id_rol, id_contacto FROM Usuarios");
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId_usuario(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setDocumento(rs.getDouble(4));
                u.setFecha_nac(rs.getString(5));
                u.setDireccion(rs.getString(6));
                u.setId_rol(rs.getInt(7));
                u.setId_contacto(rs.getInt(8));
                lista.add(u);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Usuario obtenerUsuario(int id_usuario) {
        Usuario u = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_usuario, nombre, apellido, documento, fecha_nac, direccion, id_rol, id_contacto FROM Usuarios WHERE id_usuario = ?");
            ps.setInt(1, id_usuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u = new Usuario();
                u.setId_usuario(rs.getInt(1));
                u.setNombre(rs.getString(2));
                u.setApellido(rs.getString(3));
                u.setDocumento(rs.getDouble(4));
                u.setFecha_nac(rs.getString(5));
                u.setDireccion(rs.getString(6));
                u.setId_rol(rs.getInt(7));
                u.setId_contacto(rs.getInt(8));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return u;
    }

    public void actualizarUsuario(Usuario u) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Usuarios SET nombre = ?, apellido = ?, documento = ?, fecha_nac = ?, direccion = ?, id_rol = ?, id_contacto = ? WHERE id_usuario = ?");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setDouble(3, u.getDocumento());
            ps.setString(4, u.getFecha_nac());
            ps.setString(5, u.getDireccion());
            ps.setInt(6, u.getId_rol());
            ps.setInt(7, u.getId_contacto());
            ps.setInt(8, u.getId_usuario());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarUsuario(int id_usuario) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Usuarios WHERE id_usuario = ?");
            ps.setInt(1, id_usuario);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

}
