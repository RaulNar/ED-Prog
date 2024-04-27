package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * cliente es la tabla hija de persona, por lo que extiende persona e implementa CRUD, ya que ejecutará los métodos 
 * de conexión a la base de datos
 */

public class Cliente extends Persona implements CRUD {

	// Attributes.
	private Integer nº_reservas;
	private Integer id_pedido_fav;

	// Constant used in connection.
	final String url = "jdbc:mysql://localhost:3306/far far west inc.";

	// Builders.
	public Cliente(String ind, String name, String surname, Integer age, String address, Integer telephone,
			Integer reserved, Integer favorite) {
		super(ind, name, surname, age, address, telephone);
		this.nº_reservas = reserved;
		this.id_pedido_fav = favorite;
	}

	@Override
	public void read() {// Método de lectura de los campos de la tabla persona y cliente.
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset.
				// He usado un left join para unir las dos tablas.
				ResultSet resultset = statement
						.executeQuery("SELECT * FROM persona left join cliente on persona.dni = cliente.dni")) {
			// Ahora, por cada fila el resultset, realizamos las operaciones
			// correspondientes.
			while (resultset.next()) {

				// Los atributos de las dos clases, sin repetir el dni.
				String ndi = resultset.getString("dni");
				String name = resultset.getString("nombre");
				String surname = resultset.getString("apellido");
				Integer age = resultset.getInt("edad");
				String address = resultset.getString("direccion");
				Integer telephone = resultset.getInt("telefono");
				Integer id = resultset.getInt("id_cliente");
				Integer reserved = resultset.getInt("nº_reservas");
				Integer favorite = resultset.getInt("id_pedido_fav");

				System.out.println(ndi + "\t" + name + "\t" + surname + "\t" + age + "\t" + address + "\t" + telephone
						+ "\t" + id + "\t" + reserved + "\t" + favorite);
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
	public void insert() {// Este método inserta dos veces con dos try catch, por que no me atrevía a
							// insertar datos en dos tablas de una.
		try {// try catch insert de persona.
			Connection conn = DriverManager.getConnection(url, "root", "");
			System.out.println(conn.isValid(0));
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO persona " + "VALUES ('" + this.dni + "','" + this.nombre + "','"
					+ this.apellido + "'," + this.edad + ",'" + this.direccion + "'," + this.telefono + ")");
			System.out.println("ha creado persona");
			st.executeUpdate("INSERT INTO cliente (dni, nº_reservas, id_pedido_fav) " + "VALUES ('" + this.dni + "',"
					+ this.nº_reservas + "," + this.id_pedido_fav + ")");
			System.out.println("ha creado cliente");
			// For security reasons, we close connections.
			conn.close();
			st.close();
			System.out.println("Ha creado cliente.");
		} catch (Exception e) {
			System.err.println("Got an exception on cliente! ");
			System.err.println(e.getMessage());
		}

	}

	@Override
	public void delete() { // Delete en dos tablas, con un try catch por cada tabla, como el método insert.
		try {// try catch delete de persona.
			Connection conn = DriverManager.getConnection(url, "root", "");
			String query = "delete from persona where dni=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.dni);
			ps.executeUpdate();
			String query2 = "delete from cliente where dni=? ";
			PreparedStatement ts = conn.prepareStatement(query2);
			ts.setString(1, this.dni);
			ts.executeUpdate();
			// For security reasons, we close connections.
			conn.close();
			ps.close();
			ts.close();
			System.out.println("Borrado correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void update() {// Básicamente es un delete seguido de un insert. Esto implica que hay 4 try
							// catch.
		try {// try catch delete de persona.
			Connection conn = DriverManager.getConnection(url, "root", "");
			String query = "delete from persona where dni=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.dni);
			ps.executeUpdate();
			String query2 = "delete from cliente where dni=? ";
			PreparedStatement ts = conn.prepareStatement(query2);
			ts.setString(1, this.dni);
			ts.executeUpdate();
			// For security reasons, we close connections.
			conn.close();
			ps.close();
			ts.close();
			System.out.println("Borrado correctamente.");
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {// try catch insert de persona.
			Connection conn = DriverManager.getConnection(url, "root", "");
			System.out.println(conn.isValid(0));
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO persona " + "VALUES ('" + this.dni + "','" + this.nombre + "','"
					+ this.apellido + "'," + this.edad + ",'" + this.direccion + "'," + this.telefono + ")");
			System.out.println("ha creado persona");
			st.executeUpdate("INSERT INTO cliente (dni, nº_reservas, id_pedido_fav) " + "VALUES ('" + this.dni + "',"
					+ this.nº_reservas + "," + this.id_pedido_fav + ")");
			System.out.println("ha creado cliente");
			// For security reasons, we close connections.
			conn.close();
			st.close();
			System.out.println("Ha creado cliente.");
		} catch (Exception e) {
			System.err.println("Got an exception on cliente! ");
			System.err.println(e.getMessage());
		}

	}

	// A method that shows attributes from object Persona through console.
	public void mostrar() {
		System.out.println("dni: " + this.dni + ", nombre: " + this.nombre + ", apellido: " + this.apellido + ", edad: "
				+ this.edad + ", dirección: " + this.direccion + ", teléfono: " + this.telefono + " número de reserva: "
				+ nº_reservas + ", pedido favorito: " + id_pedido_fav);
	}

	// Getters
	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public String getUrl() {
		return url;
	}

	// Setters
	public void setDni(String dni) {
		this.dni = dni;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

}
