package Gestor;

import Modelo.Carrito;
import Modelo.DTO.DTO_Factura;
import Modelo.Factura;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Gestor_Facturas {

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

    public void agregarFactura(Factura f) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Facturas (fecha, descuento, id_sucursal, id_cliente, id_usuario, id_forma_de_pago, vigencia) VALUES (?,?,?,?,?,?,1)");
            ps.setString(1, f.getFecha());
            ps.setFloat(2, f.getDescuento());
            ps.setInt(3, f.getId_sucursal());
            ps.setInt(4, f.getId_cliente());
            ps.setInt(5, f.getId_usuario());
            ps.setInt(6, f.getId_forma_de_pago());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Factura> obtenerFacturas() {
        ArrayList<Factura> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_factura, fecha, descuento, id_sucursal, id_cliente, id_usuario, id_forma_de_pago FROM Facturas WHERE vigencia = 1");
            while (rs.next()) {
                Factura f = new Factura();
                f.setId_factura(rs.getInt(1));
                f.setFecha(rs.getString(2));
                f.setDescuento(rs.getFloat(3));
                f.setId_sucursal(rs.getInt(4));
                f.setId_cliente(rs.getInt(5));
                f.setId_usuario(rs.getInt(6));
                f.setId_forma_de_pago(rs.getInt(7));
                lista.add(f);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }

    public Factura obtenerFactura(int id_factura) {
        Factura f = null;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT id_factura, fecha, descuento, id_sucursal, id_cliente, id_usuario, id_forma_de_pago FROM Facturas WHERE id_factura = ?");
            ps.setInt(1, id_factura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                f = new Factura();
                f.setId_factura(rs.getInt(1));
                f.setFecha(rs.getString(2));
                f.setDescuento(rs.getFloat(3));
                f.setId_sucursal(rs.getInt(4));
                f.setId_cliente(rs.getInt(5));
                f.setId_usuario(rs.getInt(6));
                f.setId_forma_de_pago(rs.getInt(7));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return f;
    }

    public void actualizarFactura(Factura f) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Facturas SET fecha = ?, descuento = ?, id_sucursal = ?, id_cliente = ?, id_usuario = ?, id_forma_de_pago = ?  WHERE id_factura = ?");
            ps.setString(1, f.getFecha());
            ps.setFloat(2, f.getDescuento());
            ps.setInt(3, f.getId_sucursal());
            ps.setInt(4, f.getId_cliente());
            ps.setInt(5, f.getId_usuario());
            ps.setInt(6, f.getId_forma_de_pago());
            ps.setInt(6, f.getId_factura());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarFactura(int id_factura) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Facturas SET vigencia = 0 WHERE id_factura = ?");
            ps.setInt(1, id_factura);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<DTO_Factura> obtenerFacturasDTO() {
        ArrayList<DTO_Factura> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT f.id_factura, fecha, descuento, s.nombre, c.nombre +' '+ c.apellido, u.nombre +' '+ u.apellido, fp.nombre, SUM (d.importe * d.cantidad) FROM Facturas f JOIN Sucursales s ON f.id_sucursal = s.id_sucursal JOIN Clientes c ON f.id_cliente = c.id_cliente JOIN Formas_de_Pagos fp ON f.id_forma_de_pago = fp.id_forma_de_pago JOIN Usuarios u ON f.id_usuario = u.id_usuario JOIN Detalle_Facturas d ON f.id_factura = d.id_factura WHERE f.vigencia = 1 GROUP BY f.id_factura, fecha, descuento, s.nombre, c.nombre +' '+ c.apellido, u.nombre +' '+ u.apellido, fp.nombre");
            while (rs.next()) {
                DTO_Factura f = new DTO_Factura();
                f.setId_factura(rs.getInt(1));
                f.setFecha(rs.getString(2));
                f.setDescuento(rs.getFloat(3));
                f.setSucursal(rs.getString(4));
                f.setCliente(rs.getString(5));
                f.setUsuario(rs.getString(6));
                f.setForma_de_pago(rs.getString(7));
                f.setTotal(rs.getString(8));
                lista.add(f);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public int obtenerUltimoIdFactura() {
        int id = 0;
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("SELECT TOP 1 id_factura FROM Facturas ORDER BY 1 DESC");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return id;
    }

    public void agregarDetalleCarrito(Carrito c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO Listas (id_producto, cantidad) VALUES (?,?)");
            ps.setInt(1, c.getId_producto());
            ps.setInt(2, c.getCantidad());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public ArrayList<Carrito> obtenerCarrito() {
        ArrayList<Carrito> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT id_lista, id_producto, cantidad FROM Listas");
            while (rs.next()) {
                Carrito c = new Carrito();
                c.setId_compra(rs.getInt(1));
                c.setId_producto(rs.getInt(2));
                c.setCantidad(rs.getInt(3));
                lista.add(c);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public ArrayList<Carrito> obtenerCarritoDTO() {
        ArrayList<Carrito> lista = new ArrayList<>();
        try {
            abrirConexion();
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT l.id_lista, p.id_producto, p.nombre, l.cantidad, p.precio FROM Listas l JOIN Productos p ON l.id_producto = p.id_producto");
            while (rs.next()) {
                Carrito c = new Carrito();
                c.setId_compra(rs.getInt(1));
                c.setId_producto(rs.getInt(2));
                c.setNombre(rs.getString(3));
                c.setCantidad(rs.getInt(4));
                c.setPrecio(rs.getFloat(5));
                lista.add(c);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
        return lista;
    }
    
    public void actualizarDetalleCarrito(Carrito c) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("UPDATE Listas SET cantidad = ?  WHERE id_lista = ?");
            ps.setInt(1, c.getCantidad());
            ps.setInt(2, c.getId_compra());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
    
    public void eliminarDetalleCarrito(int id_producto) {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("DELETE Listas WHERE id_producto = ?");
            ps.setInt(1, id_producto);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }

    public void eliminarCarrito() {
        try {
            abrirConexion();
            PreparedStatement ps = conexion.prepareStatement("TRUNCATE TABLE Listas");
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(Gestor_Facturas.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexion();
        }
    }
    
}
