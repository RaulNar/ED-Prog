package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Plato implements CRUD {// Una clase que opera sobre la tabla plato.

	// Attributes
	protected Integer id_plato;

	protected String tipo_plato;

	protected String nombre_plato;

	protected String descripcion;

	protected Float precio_plato;

	// Constant used in connection.
	final String url = "jdbc:mysql://localhost:3306/far far west inc.";

	// Builders
	public Plato(Integer id, String type, String name, String description, Float price) {
		this.id_plato = id;
		this.tipo_plato = type;
		this.nombre_plato = name;
		this.descripcion = description;
		this.precio_plato = price;

	}

	@Override
	public void read() {// Un try catch simple con un query que almacena una consulta simple.
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				ResultSet resultset = statement.executeQuery("SELECT * FROM plato")) {
			// Ahora, por cada fila el resultset, realizamos las operaciones
			// correspondientes.
			while (resultset.next()) {

				Integer id = resultset.getInt("id_plato");
				String type = resultset.getString("tipo_plato");
				String name = resultset.getString("nombre_plato");
				String description = resultset.getString("descripcion");
				Float price = resultset.getFloat("precio_plato");
				System.out.println(id + "\t" + type + "\t" + name + "\t" + description + "\t" + price);
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
			st.executeUpdate("INSERT INTO plato " + "VALUES ('" + this.id_plato + "','" + this.tipo_plato + "','"
					+ this.nombre_plato + "'," + this.descripcion + ",'" + this.precio_plato + ")");
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
			String query = "delete from plato where id=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, this.id_plato);
			ps.executeUpdate();
			// For security reasons, we close connections.
			conn.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update() {// Un try catch doble, uno borra y otro inserta.
		try {
			Connection conn = DriverManager.getConnection(url, "root", "");
			String query = "delete from plato where id=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, this.id_plato);
			ps.executeUpdate();
			// Aquí empieza el insert.
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO plato " + "VALUES ('" + this.id_plato + "','" + this.tipo_plato + "','"
					+ this.nombre_plato + "'," + this.descripcion + ",'" + this.precio_plato + ")");
			// For security reasons, we close connections.
			conn.close();
			ps.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// A method that shows attributes from object Plato through console.
	public void mostrar() {// un método para mostrar los atributos actuales de la clase plato.
		System.out.println("id: " + this.id_plato + ", tipo: " + this.tipo_plato + ", nombre: " + this.nombre_plato
				+ ", descripción: " + this.descripcion + ", precio: " + this.precio_plato);
	}

	// Getters
	public Integer getId_plato() {
		return this.id_plato;
	}

	public String getTipo() {
		return this.tipo_plato;
	}

	public String getNombre() {
		return this.nombre_plato;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public Float getPrecio() {
		return this.precio_plato;
	}

	public String getUrl() {
		return url;
	}

// Setters
	public void setId_plato(Integer id) {
		this.id_plato = id;
	}

	public void setTipo(String type) {
		this.tipo_plato = type;
	}

	public void setNombre(String nombre) {
		this.nombre_plato = nombre;
	}

	public void setDescripcion(String description) {
		this.descripcion = description;
	}

	public void setPrecio(Float price) {
		this.precio_plato = price;
	}

}
