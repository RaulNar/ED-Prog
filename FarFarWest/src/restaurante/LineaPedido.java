package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LineaPedido implements CRUD {

    private Integer id_linea_pedido;
    private Integer id_cliente;
    private Integer id_plato;
    private Integer id_pedido;
    private Integer cantidad;
    private Float precioTotal;

    // Constant used in connection.
    final String url = "jdbc:mysql://localhost:3306/far_far_west_inc_";

    // Builders
    public LineaPedido(Integer id_linea_pedido, Integer id_cliente, Integer id_plato, Integer id_pedido, Integer cantidad, Float precioTotal) {
        this.id_linea_pedido = id_linea_pedido;
        this.id_cliente = id_cliente;
        this.id_plato = id_plato;
        this.id_pedido = id_pedido;
        this.cantidad = cantidad;
        this.precioTotal = precioTotal;
    }

    public LineaPedido() {
    }

    @Override
    public void read() {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM ingrediente")) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_linea_pedido");
                Integer clienteId = resultSet.getInt("id_cliente");
                Integer platoId = resultSet.getInt("id_plato");
                Integer pedidoId = resultSet.getInt("id_pedido");
                Integer cantidad = resultSet.getInt("cantidad");
                Float precioTotal = resultSet.getFloat("precio_total");
                System.out.println(id + "\t" + clienteId + "\t" + platoId + "\t" + pedidoId + "\t" + cantidad + "\t" + precioTotal);
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos");
            e.printStackTrace();
        }
    }

    @Override
    public void insert() {
        String query = "INSERT INTO ingrediente (id_cliente, id_plato, id_pedido, cantidad, precio_total) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.id_cliente);
            ps.setInt(2, this.id_plato);
            ps.setInt(3, this.id_pedido);
            ps.setInt(4, this.cantidad);
            ps.setFloat(5, this.precioTotal);
            ps.executeUpdate();
            System.out.println("Ingrediente creado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar ingrediente: " + e.getMessage());
        }
    }

    @Override
    public void delete() {
        String query = "DELETE FROM ingrediente WHERE id_linea_pedido=?";
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.id_linea_pedido);
            ps.executeUpdate();
            System.out.println("Ingrediente eliminado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar ingrediente: " + e.getMessage());
        }
    }

    @Override
    public void update() {
        String query = "UPDATE ingrediente SET id_cliente=?, id_plato=?, id_pedido=?, cantidad=?, precio_total=? WHERE id_linea_pedido=?";
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.id_cliente);
            ps.setInt(2, this.id_plato);
            ps.setInt(3, this.id_pedido);
            ps.setInt(4, this.cantidad);
            ps.setFloat(5, this.precioTotal);
            ps.setInt(6, this.id_linea_pedido);
            ps.executeUpdate();
            System.out.println("Ingrediente actualizado correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar ingrediente: " + e.getMessage());
        }
    }

    // Getters and setters
    public Integer getId_linea_pedido() {
        return id_linea_pedido;
    }

    public void setId_linea_pedido(Integer id_linea_pedido) {
        this.id_linea_pedido = id_linea_pedido;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_plato() {
        return id_plato;
    }

    public void setId_plato(Integer id_plato) {
        this.id_plato = id_plato;
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal = precioTotal;
    }
}
