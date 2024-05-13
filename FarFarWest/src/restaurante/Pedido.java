package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Clase Pedido
 * Propósito: Diseño de la clase Pedido e implementación del CRUD
 * Autor: Asunción de los Ángeles Naranjo Rodríguez
 * Fecha: 2024/05/2024
 * */

public class Pedido implements CRUD{

    // Attributes
    protected Integer id_pedido;

    protected Integer id_cliente;

    protected Integer servidor;

    protected String direccion;

    protected Float precio_pedido;

    protected String fecha;

    // Constant used in connection.
    final String url = "jdbc:mysql://localhost:3306/far_far_west_inc_";

    // Builders
    public Pedido(Integer id_pedido, Integer id_cliente, String direccion, Float precio_pedido, String fecha) {

        this.id_pedido = id_pedido;
        this.id_cliente = id_cliente;
        this.direccion = direccion;
        this.precio_pedido = precio_pedido;
        this.fecha = fecha;
    }

    public Pedido() {
    }

    //CRUD
    public void read() {// Un try catch simple con un query que almacena una consulta simple.
        try (Connection connection = DriverManager.getConnection(url, "root", "");
             // Creamos el objeto Statement que nos permitirá realizar Querys
             Statement statement = connection.createStatement();
             // A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
             ResultSet resultset = statement.executeQuery("SELECT * FROM pedido")) {
            // Ahora, por cada fila el resultset, realizamos las operaciones
            // correspondientes.
            while (resultset.next()) {

                Integer id = resultset.getInt("id_pedido");
                Integer id_client = resultset.getInt("id_cliente");
                Integer server = resultset.getInt("servidor");
                String direccion = resultset.getString("direccion");
                Float precio  = resultset.getFloat("precio_pedido");
                Integer cantidad= resultset.getInt("cantidad");
                String fecha = resultset.getString("fecha");
                System.out.println(id + "\t" + direccion + "\t" + precio + "\t" + cantidad + "\t" + fecha + "\n");
            }
            // For security reasons, we close connections.
            resultset.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos");
            e.printStackTrace();
        }

    }

    @Override
    public void insert() {// Un try catch simple de insert en plato.
        try {
            Connection conn = DriverManager.getConnection(url, "root", "");
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO pedido (id_cliente, servidor, direccion, precio_pedido, fecha)" + "VALUES (" +
                    this.id_cliente + "," + this.servidor + ",'" + this.direccion + "'," + this.precio_pedido + ",'" + this.fecha + "')");
            // For security reasons, we close connections.
            conn.close();
            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    @Override
    public void delete() {// un try catch simple con una query de delete.
        try {
            Connection conn = DriverManager.getConnection(url, "root", "");
            String query = "delete from pedido where id=? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, this.id_pedido);
            ps.executeUpdate();
            // For security reasons, we close connections.
            conn.close();
            ps.close();
        } catch (Exception e) {
            System.out.println("No se puede realizar el borrado de la tabla.");
            e.printStackTrace();
        }

    }

    @Override
    public void update() {// Un try catch doble, uno borra y otro inserta.
        try {
            Connection conn = DriverManager.getConnection(url, "root", "");
            String query = "delete from pedido where id=? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, this.id_pedido);
            ps.executeUpdate();
            // Aquí empieza el insert.
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO pedido (id_pedido, id_cliente, servidor, direccion, precio_pedido, fecha)" + "VALUES (" +
                    this.id_pedido + ","+ this.id_cliente + "," + this.servidor + ",'" + this.direccion + "'," + this.precio_pedido + ",'" + this.fecha + "')");
            // For security reasons, we close connections.
            conn.close();
            ps.close();
            st.close();
        } catch (Exception e) {
            System.out.println("No se ha podido updatear la tabla.");
            e.printStackTrace();
        }
    }

    public void getPendientes() {
        try (Connection connection = DriverManager.getConnection(url, "root", "");
             // Creamos el objeto Statement que nos permitirá realizar Querys
             Statement statement = connection.createStatement();
             // A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
             ResultSet resultset = statement.executeQuery("SELECT * FROM pedido where servidor is null"))
        {
            while (resultset.next()) {

                Integer id = resultset.getInt("id_pedido");
                Integer id_client = resultset.getInt("id_cliente");
                Integer server = resultset.getInt("servidor");
                String direccion = resultset.getString("direccion");
                Float precio  = resultset.getFloat("precio_pedido");
                String fecha = resultset.getString("fecha");
                System.out.println(id_pedido + "\t"+ id_cliente + "\t" + servidor + "\t" + direccion + "\t" + precio_pedido + "\t" + fecha + "\n");

            }
            // For security reasons, we close connections.
            resultset.close();
            statement.close();
            connection.close();

        } catch (

                SQLException e) {
            System.out.println("Error en la conexión de la base de datos");
            System.out.println("Es posible que no queden pedidos pendientes sin servir.");
            e.printStackTrace();

        }
    }

    public Integer getId_pedido() {
        return id_pedido;
    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public Integer getServidor() {
        return servidor;
    }

    public String getDireccion() {
        return direccion;
    }

    public Float getPrecio_pedido() {
        return precio_pedido;
    }

    public String getFecha() {
        return fecha;
    }

    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
    }

    public void setServidor(Integer servidor) {
        this.servidor = servidor;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setPrecio_pedido(Float precio_pedido) {
        this.precio_pedido = precio_pedido;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    //Function that does order
    public void pedido() {
    	this.insert();
    }
}


