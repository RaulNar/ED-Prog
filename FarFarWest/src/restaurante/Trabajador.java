package restaurante;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class Trabajador extends Persona implements CRUD {

	// Attributes.
	private enum Cargo {
		Jefe, Chef, Camarero, Repartidor
	};

	private Integer id_trabajador;
	private Integer id_sucursal;
	private Double salario;
	private Cargo cargo;

	// Constant used in connection.
	final String url = "jdbc:mysql://localhost:3306/far_far_west_inc_";

	// Builders.
	public Trabajador(Integer id_trabajador, Integer id_sucursal, Double salario, Cargo cargo, String ind, String name,
			String surname, Integer age, String address, Integer telephone) {
		super(ind, name, surname, age, address, telephone);
		this.id_trabajador = id_trabajador;
		this.id_sucursal = id_sucursal;
		this.salario = salario;
		this.cargo = cargo;

	}

	public Trabajador() {
		
	}

	@Override
	public void read() {// Método de lectura de los campos de la tabla persona y trabajador.
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset.
				// He usado un left join para unir las dos tablas.
				ResultSet resultset = statement
						.executeQuery("SELECT * FROM persona left join trabajador on persona.dni = trabajador.dni")) {
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
				Integer id = resultset.getInt("id_trabajador");
				Integer sucursal = resultset.getInt("id_sucursal");
				Double cobro = resultset.getDouble("salario");
				String cargoStr = resultset.getString("cargo");

				System.out.println("\t" + id + "\t" + sucursal + "\t" + cobro + "\t" + cargoStr + "\n" + ndi + "\t"
						+ name + "\t" + surname + "\t" + age + "\t" + address + "\t" + telephone);
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
			st.executeUpdate("INSERT INTO persona " + "VALUES ('" + this.DNI+ "','" + this.nombre + "','"
					+ this.apellido + "'," + this.edad + "' ," + this.direccion + "'," + this.telefono + ")");
			System.out.println("ha creado persona");
			st.executeUpdate("INSERT INTO trabajador (id_trabajador, id_sucursal, salario, cargo) " + "VALUES ('"
					+ this.id_trabajador + "'," + this.id_sucursal + "' ," + this.salario + "," + this.cargo + ")");
			System.out.println("ha creado trabajador");
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
	public void delete() { // Delete en dos tablas, con un try catch por cada tabla, como el método insert.
		try {// try catch delete de persona.
			Connection conn = DriverManager.getConnection(url, "root", "");
			String query = "delete from persona where dni=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, this.DNI);
			ps.executeUpdate();
			String query2 = "delete from trabajador where dni=? ";
			PreparedStatement ts = conn.prepareStatement(query2);
			ts.setString(1, this.DNI);
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
			ps.setString(1, this.DNI);
			ps.executeUpdate();
			String query2 = "delete from trabajador where dni=? ";
			PreparedStatement ts = conn.prepareStatement(query2);
			ts.setString(1, this.DNI);
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
			st.executeUpdate("INSERT INTO persona " + "VALUES ('" + this.DNI + "','" + this.nombre + "','"
					+ this.apellido + "'," + this.edad + ",'" + this.direccion + "'," + this.telefono + ")");
			System.out.println("ha creado persona");
			st.executeUpdate("INSERT INTO trabajador (id_trabajador, id_sucursal, salario, cargo) " + "VALUES ('"
					+ this.id_trabajador + "'," + this.id_sucursal + "'," + this.salario + "," + this.cargo + ")");
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
		System.out.println("ID: " + this.id_trabajador + "ID: " + this.id_sucursal + ", Cargo: " + this.cargo
				+ ", Salario: " + this.salario + "dni: " + this.DNI + ", nombre: " + this.nombre + ", apellido: "
				+ this.apellido + ", edad: " + this.edad + ", dirección: " + this.direccion + ", teléfono: "
				+ this.telefono);
	}

	/* A method that change Cargo from worker.*/
	public boolean cambiarCargo(Integer id_trabajador, String cargo, String nuevoCargo){
		boolean check = false;
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				ResultSet resultset = statement.executeQuery("SELECT * FROM trabajador left join persona on persona.DNI=trabajador.dni where id_trabajador = '"
						+ id_trabajador + "' and cargo = '" + cargo + "'")) {
			if (resultset.next()) {
				
				String ndi = resultset.getString("dni");
				String name = resultset.getString("nombre");
				String surname = resultset.getString("apellido");
				Integer age = resultset.getInt("edad");
				String address = resultset.getString("direccion");
				Integer telephone = resultset.getInt("telefono");
				Integer id = resultset.getInt("id_trabajador");
				Integer sucursal = resultset.getInt("id_sucursal");
				Double cobro = resultset.getDouble("salario");
				String cargoStr = resultset.getString("cargo");
				this.DNI = ndi;
				this.nombre = name;
				this.apellido = surname;
				this.edad = age;
				this.direccion = address;
				this.telefono = telephone;
				this.id_trabajador = id;
				this.id_sucursal = sucursal;
				this.salario = cobro;
				this.cargo = Cargo.valueOf(nuevoCargo);
			}
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
	// A method that compare attributes from two workers.
	public int compareTo(Trabajador otroTrabajador) {
		// Comparar por nombre
		int comparacionNombre = this.getid_trabajador().compareTo(otroTrabajador.getid_trabajador());
		if (comparacionNombre != 0) {
			return comparacionNombre;
		} else {
			// Comparar por salario si los nombres son distintos
			if (!Objects.equals(this.getSalario(), otroTrabajador.getSalario())) {
				return Double.compare(this.getSalario(), otroTrabajador.getSalario());
			} else {
				return 0; // Los nombres y salarios son iguales
			}
		}
	}

	public boolean validarEdad() {
		int edad = this.getEdad();
		return edad >= 18 && edad <= 65; // Validar que la edad esté en un rango apropiado
	}

	// Getters and Setters
	public String getDni() {
		return DNI;
	}

	public void setDni(String dni) {
		this.DNI = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Integer getTelefono() {
		return telefono;
	}

	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}

	public String getUrl() {
		return url;
	}

	// Getters and Setters (Trabajador)
	public Integer getid_trabajador() {
		return id_trabajador;
	}

	public void setid_trabajador(Integer id_trabajador) {
		this.id_trabajador = id_trabajador;
	}

	public Double getSalario() {
		return salario;
	}

	public void setSalario(Double salario) {
		this.salario = salario;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

}