package Gestor;

import Modelo.DTO.DTO_Producto;
import Modelo.Producto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Productos {

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

    public void agregarProducto(Producto p) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Productos (codigo, nombre, fecha_fab, fecha_ven, precio, descripcion, stock, id_marca, id_deposito, vigencia) VALUES (?,?,?,?,?,?,?,?,?,1)");
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getFecha_fab());
            ps.setString(4, p.getFecha_ven());
            ps.setFloat(5, p.getPrecio());
            ps.setString(6, p.getDescripcion());
            ps.setFloat(7, p.getStock());
            ps.setInt(8, p.getId_marca());
            ps.setInt(9, p.getId_deposito());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_producto, codigo, nombre, fecha_fab, fecha_ven, precio, descripcion, stock, id_marca, id_deposito FROM Productos WHERE vigencia = 1");
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setId_marca(rs.getInt(9));
                p.setId_deposito(rs.getInt(10));
                lista.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Producto obtenerProducto(int id_producto) {
        Producto p = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_producto, codigo, nombre, fecha_fab, fecha_ven, precio, descripcion, stock, id_marca, id_deposito FROM Productos WHERE id_producto = ?");
            ps.setInt(1, id_producto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setId_marca(rs.getInt(9));
                p.setId_deposito(rs.getInt(10));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return p;
    }

    public void actualizarProducto(Producto p) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Productos SET codigo = ?, nombre = ?, fecha_fab = ?, fecha_ven = ?, precio = ?, descripcion = ?, stock = ?, id_marca = ?, id_deposito = ? WHERE id_producto = ?");
            ps.setString(1, p.getCodigo());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getFecha_fab());
            ps.setString(4, p.getFecha_ven());
            ps.setFloat(5, p.getPrecio());
            ps.setString(6, p.getDescripcion());
            ps.setFloat(7, p.getStock());
            ps.setInt(8, p.getId_marca());
            ps.setInt(9, p.getId_deposito());
            ps.setInt(10, p.getId_producto());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarProducto(int id_producto) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Productos SET vigencia = 0 WHERE id_producto = ?");
            ps.setInt(1, id_producto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
    
    public DTO_Producto obtenerProductoDTO(int id_producto) {
        DTO_Producto p = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.codigo, p.nombre, p.fecha_fab, p.fecha_ven, p.precio, p.descripcion, p.stock, m.id_categoria, p.id_marca, p.id_deposito FROM Productos p JOIN Marcas m ON p.id_marca = m.id_marca JOIN Depositos d ON p.id_deposito = d.id_deposito JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p.id_producto = ?");
            ps.setInt(1, id_producto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));                
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setId_categoria(rs.getInt(9));
                p.setId_marca(rs.getInt(10));
                p.setId_deposito(rs.getInt(11));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return p;
    }

    public ArrayList<DTO_Producto> obtenerProductosDTO() {
        ArrayList<DTO_Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.codigo, p.nombre, p.fecha_fab, p.fecha_ven, p.precio, p.descripcion, p.stock, c.nombre, m.nombre, d.ubicacion FROM Productos p JOIN Marcas m ON p.id_marca = m.id_marca JOIN Depositos d ON p.id_deposito = d.id_deposito JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p. vigencia = 1");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Producto p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));                
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setCategoria(rs.getString(9));
                p.setMarca(rs.getString(10));
                p.setDeposito(rs.getString(11));
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public ArrayList<DTO_Producto> obtenerProductosStockDTO() {
        ArrayList<DTO_Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.codigo, p.nombre, p.fecha_fab, p.fecha_ven, p.precio, p.descripcion, p.stock, c.nombre, m.nombre, d.ubicacion FROM Productos p JOIN Marcas m ON p.id_marca = m.id_marca JOIN Depositos d ON p.id_deposito = d.id_deposito JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p. vigencia = 1 AND p.stock > 0");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Producto p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));                
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setCategoria(rs.getString(9));
                p.setMarca(rs.getString(10));
                p.setDeposito(rs.getString(11));
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public ArrayList<DTO_Producto> obtenerProductosCategoriaDTO(int id_categoria) {
        ArrayList<DTO_Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.codigo, p.nombre, p.fecha_fab, p.fecha_ven, p.precio, p.descripcion, p.stock, c.nombre, m.nombre, d.ubicacion FROM Productos p JOIN Marcas m ON p.id_marca = m.id_marca JOIN Depositos d ON p.id_deposito = d.id_deposito JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p. vigencia = 1 AND m.id_categoria = ?");
           ps.setInt(1, id_categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Producto p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));                
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setCategoria(rs.getString(9));
                p.setMarca(rs.getString(10));
                p.setDeposito(rs.getString(11));
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public ArrayList<DTO_Producto> obtenerProductosCategoriaStockDTO(int id_categoria) {
        ArrayList<DTO_Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.codigo, p.nombre, p.fecha_fab, p.fecha_ven, p.precio, p.descripcion, p.stock, c.nombre, m.nombre, d.ubicacion FROM Productos p JOIN Marcas m ON p.id_marca = m.id_marca JOIN Depositos d ON p.id_deposito = d.id_deposito JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p. vigencia = 1 AND m.id_categoria = ? AND p.stock > 0");
           ps.setInt(1, id_categoria);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Producto p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));                
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setCategoria(rs.getString(9));
                p.setMarca(rs.getString(10));
                p.setDeposito(rs.getString(11));
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
        public ArrayList<DTO_Producto> obtenerProductosMarcaDTO(int id_marca) {
        ArrayList<DTO_Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.codigo, p.nombre, p.fecha_fab, p.fecha_ven, p.precio, p.descripcion, p.stock, c.nombre, m.nombre, d.ubicacion FROM Productos p JOIN Marcas m ON p.id_marca = m.id_marca JOIN Depositos d ON p.id_deposito = d.id_deposito JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p. vigencia = 1 AND m.id_marca = ?");
           ps.setInt(1, id_marca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Producto p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));                
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setCategoria(rs.getString(9));
                p.setMarca(rs.getString(10));
                p.setDeposito(rs.getString(11));
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
        
        public ArrayList<DTO_Producto> obtenerProductosMarcaStockDTO(int id_marca) {
        ArrayList<DTO_Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.codigo, p.nombre, p.fecha_fab, p.fecha_ven, p.precio, p.descripcion, p.stock, c.nombre, m.nombre, d.ubicacion FROM Productos p JOIN Marcas m ON p.id_marca = m.id_marca JOIN Depositos d ON p.id_deposito = d.id_deposito JOIN Categorias c ON m.id_categoria = c.id_categoria WHERE p. vigencia = 1 AND m.id_marca = ? AND p.stock > 0");
           ps.setInt(1, id_marca);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DTO_Producto p = new DTO_Producto();
                p.setId_producto(rs.getInt(1));
                p.setCodigo(rs.getString(2));
                p.setNombre(rs.getString(3));                
                p.setFecha_fab(rs.getString(4));
                p.setFecha_ven(rs.getString(5));
                p.setPrecio(rs.getFloat(6));
                p.setDescripcion(rs.getString(7));
                p.setStock(rs.getFloat(8));
                p.setCategoria(rs.getString(9));
                p.setMarca(rs.getString(10));
                p.setDeposito(rs.getString(11));
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
        
        public ArrayList<Producto> obtenerProductosID(int id_producto) {
        ArrayList<Producto> lista = new ArrayList<>();
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT p.id_producto, p.nombre FROM Productos p WHERE p. vigencia = 1 AND p.id_producto = ?");
           ps.setInt(1, id_producto);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt(1));
                p.setNombre(rs.getString(2));                
                lista.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Productos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
}
