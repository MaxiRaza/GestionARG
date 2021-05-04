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
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Usuarios (nombre, apellido, documento, fecha_nac, direccion, id_rol, id_contacto, contrasenia, alias) VALUES (?,?,?,?,?,?,?,?,?)");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setDouble(3, u.getDocumento());
            ps.setString(4, u.getFecha_nac());
            ps.setString(5, u.getDireccion());
            ps.setInt(6, 1);
            ps.setInt(7, u.getId_contacto());
            ps.setString(8, u.getContrasenia());
            ps.setString(9, u.getAlias());
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
            ResultSet rs = st.executeQuery("SELECT id_usuario, nombre, apellido, documento, fecha_nac, direccion, id_rol, id_contacto, contrasenia, alias FROM Usuarios");
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
                u.setContrasenia(rs.getString(9));
                u.setAlias(rs.getString(10));
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
            PreparedStatement ps = conexion.prepareStatement("SELECT id_usuario, nombre, apellido, documento, fecha_nac, direccion, id_rol, id_contacto, contrasenia, alias FROM Usuarios WHERE id_usuario = ?");
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
                u.setContrasenia(rs.getString(9));
                u.setAlias(rs.getString(10));
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
            PreparedStatement ps = conexion.prepareStatement("UPDATE Usuarios SET nombre = ?, apellido = ?, documento = ?, fecha_nac = ?, direccion = ?, id_rol = ?, id_contacto = ?, contrasenia = ?, alias = ? WHERE id_usuario = ?");
            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setDouble(3, u.getDocumento());
            ps.setString(4, u.getFecha_nac());
            ps.setString(5, u.getDireccion());
            ps.setInt(6, u.getId_rol());
            ps.setInt(7, u.getId_contacto());
            ps.setString(8, u.getContrasenia());
            ps.setString(9, u.getAlias());
            ps.setInt(10, u.getId_usuario());
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

    public boolean obtenerUsuario(String alias, String contrasenia) {
        boolean existe = false;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT alias, contrasenia FROM Usuarios WHERE alias = ? AND contrasenia = ?");
            ps.setString(1, alias);
            ps.setString(2, contrasenia);
            ResultSet st = ps.executeQuery();
            if (st.next()) {
                existe = true;
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return existe;
    }

}
