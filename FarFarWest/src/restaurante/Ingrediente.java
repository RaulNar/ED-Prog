package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Ingrediente implements CRUD {

    // Attributes
    private enum Productos {
        Patatas, Tomates, Carne,
    }

    ;
    private Integer id_ingrediente;
    private Integer id_plato;
    private Integer id_sucursal;
    private Productos tipo_producto;
    private Integer stock;

    // Constant used in connection.
    final String url = "jdbc:mysql://localhost:3306/far_far_west_inc_";

    // Builders
    public Ingrediente(Integer id_ingrediente, Integer id_plato, Integer id_sucursal, Productos tipo_producto, Integer stock) {
        this.id_ingrediente = id_ingrediente;
        this.id_plato = id_plato;
        this.id_sucursal = id_sucursal;
        this.tipo_producto = tipo_producto;
        this.stock = stock;
    }
    public Ingrediente() {
	
	}
	@Override
    public void read() {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement st = conn.createStatement();
             ResultSet resultset = st.executeQuery("SELECT * FROM ingrediente")) {
            while (resultset.next()) {
                Integer id = resultset.getInt("id_ingrediente");
                Integer idPlato = resultset.getInt("id_plato");
                Integer idSucursal = resultset.getInt("id_sucursal");
                String tipoProducto = resultset.getString("tipo_producto");
                Integer stock = resultset.getInt("stock");
                System.out.println(id + "\t" + idPlato + "\t" + idSucursal + "\t" + tipoProducto + "\t" + stock);
            }
            // For security reasons, we close connections.
            resultset.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("Error en la conexión de la base de datos");
            e.printStackTrace();
        }
    }

    @Override
    public void insert() {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement st = conn.createStatement()) {
            String query = "INSERT INTO ingrediente (id_plato, id_sucursal, tipo_producto, stock)" + "VALUES ('" +
                    this.id_plato + ", " + this.id_sucursal + ", '" + this.tipo_producto + "', " + this.stock + "')";
            // For security reasons, we close connections.
            conn.close();
            st.close();
            System.out.println("Ha creado trabajador.");
        } catch (Exception e) {
            System.err.println("Got an exception on trabajador! ");
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void delete() {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement st = conn.createStatement()) {
            String query = "DELETE FROM ingrediente WHERE id_ingrediente=? ";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, this.id_ingrediente);
            ps.executeUpdate();
            // For security reasons, we close connections.
            st.close();
            conn.close();
            System.out.println("Borrado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update() {
        try (Connection conn = DriverManager.getConnection(url, "root", "");
             Statement st = conn.createStatement()) {
            String query = "UPDATE ingrediente SET id_plato = " + this.id_plato + ", id_sucursal = " + this.id_sucursal +
                    ", tipo_producto = '" + this.tipo_producto + "', stock = " + this.stock +
                    " WHERE id_ingrediente = " + this.id_ingrediente;
            PreparedStatement ts = conn.prepareStatement(query);
            ts.executeUpdate();
            // For security reasons, we close connections.
            conn.close();
            st.close();
            ts.close();
            System.out.println("Borrado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // A method that shows attributes from object Plato through console.
    public void mostrar() {// un método para mostrar los atributos actuales de la clase plato.
        System.out.println("id: " + this.id_ingrediente + ", tipo: " + this.id_plato + ", nombre: " + this.id_sucursal
                + ", descripción: " + this.tipo_producto + ", precio: " + this.stock);
    }
    
    /**
    * @Method getCuantity: a method that retrieves the stock of an id of an item. 
    * @param ingrediente
    * @param sucursal
    */
    public void getCuantity(String nombreIngrediente, Integer sucursal){


       try (Connection conn = DriverManager.getConnection(url, "root", "");
            Statement st = conn.createStatement();
            ResultSet resultset = st.executeQuery("SELECT stock FROM ingrediente where nombre_ingrediente = '" + nombreIngrediente
                    + "' and id_sucursal = " + sucursal)) {
           if(resultset.next()) {


               Integer cuantity = resultset.getInt("stock");


               System.out.println("La cantidad de tipo de ingrediente seleccionado en la sucursal seleccionada es de: "
                       + cuantity + " Kilogramos");


           }
           // For security reasons, we close connections.
           resultset.close();
           st.close();
           conn.close();
       } catch (SQLException e) {
           System.out.println("Error en la conexión de la base de datos");
           e.printStackTrace();
       }
    }

    // Getters and setters
    public Integer getId_ingrdiente() {
        return id_ingrediente;
    }

    public void setId_ingrdiente(Integer id_ingrediente) {
        this.id_ingrediente = id_ingrediente;
    }

    public Integer getId_plato() {
        return id_plato;
    }

    public void setId_plato(Integer id_plato) {
        this.id_plato = id_plato;
    }

    public Integer getId_sucursal() {
        return id_sucursal;
    }

    public void setId_sucursal(Integer id_sucursal) {
        this.id_sucursal = id_sucursal;
    }

    public Productos getTipo_producto() {
        return tipo_producto;
    }

    public void setTipo_producto(Productos tipo_producto) {
        this.tipo_producto = tipo_producto;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}