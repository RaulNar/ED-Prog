package restaurante;

//Imports that make the connections work
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//Attributes
public class Usuario implements CRUD {
	private Integer id_usuario;
	private String dni;
	private String contraseña;
	private Integer permisos;
	private String nombre_usuario;

	// Constant used in connection.
	final String url = "jdbc:mysql://localhost:3306/far far west inc.";

//Builder
	public Usuario(Integer id_usuario, String dni, String contraseña, Integer permisos, String nombre_usuario) {
		this.id_usuario = id_usuario;
		this.dni = dni;
		this.contraseña = contraseña;
		this.permisos = permisos;
		this.nombre_usuario = nombre_usuario;
	}

	public Usuario() {
	}

//CRUD methods

	@Override
	public void read() {// Un read mucho más simple que el de clase cliente, pero de la usuario, aquí
		// no hay left joins.
		try (Connection connection = DriverManager.getConnection(url, "root", "");
// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				ResultSet resultset = statement.executeQuery("SELECT * FROM usuario")) {
// Ahora, por cada fila el resultset, realizamos las operaciones
// correspondientes.
			while (resultset.next()) {

				Integer id = resultset.getInt("id_usuario");
				String ndi = resultset.getString("dni");
				String password = resultset.getString("contraseña");
				Integer permits = resultset.getInt("permisos");
				String usser_name = resultset.getString("nombre_usuario");
				System.out.println(id + "\t" + ndi + "\t" + password + "\t" + permits + "\t" + usser_name + "\n");
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
	public void insert() {// Un insert sobre usuario simple de un solo try catch.
		try {
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO usuario (dni, contraseña, permisos, nombre_usuario) " + "VALUES ('" + this.dni
					+ "','" + this.contraseña + "'," + this.permisos + ",'" + this.nombre_usuario + "')");
			// For security reasons, we close connections.
			conn.close();
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

	@Override
	public void delete() {// Un delete simple.
		try {
			Connection conn = DriverManager.getConnection(url, "root", "");
			String query = "delete from usuario where dni=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, this.id_usuario);
			ps.executeUpdate();
			// For security reasons, we close connections.
			conn.close();
			ps.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
		}

	}

	@Override
	public void update() {
		try {
			Connection conn = DriverManager.getConnection(url, "root", "");
			String query = "delete from usuario where dni=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, this.id_usuario);
			ps.executeUpdate();
			// For security reasons, we close connections.
			conn.close();
			ps.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			e.printStackTrace();
		}

		try {
			Connection conn = DriverManager.getConnection(url, "root", "");
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO usuario (dni, contraseña, permisos, nombre_usuario) " + "VALUES ('" + this.dni
					+ "','" + this.contraseña + "'," + this.permisos + ",'" + this.nombre_usuario + "')");
			// For security reasons, we close connections.
			conn.close();
			st.close();
		} catch (Exception e) {
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public void mostrar() {// Un método para mostrar los atributos actuales de la clase usuario. El query
		// almacena una consulta simple.
		System.out.println("id_usuario: " + this.id_usuario + ", dni: " + this.dni + ", contraseña: " + this.contraseña
				+ ", permisos: " + this.permisos + ", nombre de usuario: " + this.nombre_usuario);
	}

	public boolean getObject(String nombre, String pass) {

		boolean check = false;

		try (Connection connection = DriverManager.getConnection(url, "root", "");
				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				ResultSet resultset = statement.executeQuery("SELECT * FROM usuario where nombre_usuario = '" + nombre
						+ "' and contraseña = '" + pass + "'")) {

			Integer id = resultset.getInt("id_usuario");
			String ndi = resultset.getString("dni");
			String password = resultset.getString("contraseña");
			Integer permits = resultset.getInt("permisos");
			String usser_name = resultset.getString("nombre_usuario");

			this.id_usuario = id;
			this.dni = ndi;
			this.contraseña = password;
			this.permisos = permits;
			this.nombre_usuario = usser_name;

			// For security reasons, we close connections.
			resultset.close();
			statement.close();
			connection.close();
			check = true;

		} catch (

		SQLException e) {
			System.out.println("Error en la conexión de la base de datos");
			e.printStackTrace();

		}

		return check;
	}

//Getters and Setters
	public Integer getId_factura() {
		return id_usuario;
	}

	public void setId_factura(Integer id_usser) {
		this.id_usuario = id_usser;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public Integer getPermisos() {
		return permisos;
	}

	public void setPermisos(Integer permisos) {
		this.permisos = permisos;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

}
