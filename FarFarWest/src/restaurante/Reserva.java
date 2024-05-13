package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reserva implements CRUD {

    private Integer id_reserva;
    private Integer id_cliente;
    private Integer id_sucursal;
    private Integer fecha_hora;
    private Integer nombre_reserva;

    // Constant used in connection.
    final String url = "jdbc:mysql://localhost:3306/far_far_west_inc_";

    // Builders
    public Reserva(Integer id_reserva, Integer id_cliente, Integer id_sucursal, Integer fecha_hora, Integer nombre_reserva) {
        this.id_reserva = id_reserva;
        this.id_cliente = id_cliente;
        this.id_sucursal = id_sucursal;
        this.fecha_hora = fecha_hora;
        this.nombre_reserva = nombre_reserva;
    }

    public Reserva() {
    }

    @Override
    public void read() {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement st = conn.createStatement();
             ResultSet resultSet = st.executeQuery("SELECT * FROM ingrediente")) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_reserva");
                Integer clienteId = resultSet.getInt("id_cliente");
                Integer sucursalId = resultSet.getInt("id_sucursal");
                Integer fechaHora = resultSet.getInt("fecha_hora");
                Integer nombreReserva = resultSet.getInt("nombre_reserva");
                System.out.println(id + "\t" + clienteId + "\t" + sucursalId + "\t" + fechaHora + "\t" + nombreReserva);
            }
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos");
            e.printStackTrace();
        }
    }

    @Override
    public void insert() {
        String query = "INSERT INTO ingrediente (id_cliente, id_sucursal, fecha_hora, nombre_reserva) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.id_cliente);
            ps.setInt(2, this.id_sucursal);
            ps.setInt(3, this.fecha_hora);
            ps.setInt(4, this.nombre_reserva);
            ps.executeUpdate();
            System.out.println("Reserva creada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al insertar reserva: " + e.getMessage());
        }
    }

    @Override
    public void delete() {
        String query = "DELETE FROM ingrediente WHERE id_reserva=?";
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.id_reserva);
            ps.executeUpdate();
            System.out.println("Reserva eliminada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al eliminar reserva: " + e.getMessage());
        }
    }

    @Override
    public void update() {
        String query = "UPDATE ingrediente SET id_cliente=?, id_sucursal=?, fecha_hora=?, nombre_reserva=? WHERE id_reserva=?";
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, this.id_cliente);
            ps.setInt(2, this.id_sucursal);
            ps.setInt(3, this.fecha_hora);
            ps.setInt(4, this.nombre_reserva);
            ps.setInt(5, this.id_reserva);
            ps.executeUpdate();
            System.out.println("Reserva actualizada correctamente.");
        } catch (SQLException e) {
            System.err.println("Error al actualizar reserva: " + e.getMessage());
        }
    }

    // Getters and setters
    public Integer getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(Integer id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Integer getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(Integer fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public Integer getNombre_reserva() {
        return nombre_reserva;
    }

    public void setNombre_reserva(Integer nombre_reserva) {
        this.nombre_reserva = nombre_reserva;
    }
}
