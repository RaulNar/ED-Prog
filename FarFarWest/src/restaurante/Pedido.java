package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Clase Pedido
 * Propósito: Diseño de la clase Pedido e implementación del CRUD
 * Autor: Asunción de los Ángeles Naranjo Rodríguez
 * Fecha: 2024/05/2024
 * */

public class Pedido implements CRUD{
	
	// Attributes
	protected Integer id_pedido;

	protected String direccion;
	
	protected Float precio_pedido;
	
	protected Integer cantidad;
	
	protected Date fecha;
	
	// Constant used in connection.
		final String url = "jdbc:mysql://localhost:3306/far far west inc.";

	// Builders
	public Pedido(Integer id_pedido, String direccion, Float precio_pedido, Integer cantidad, Date fecha) {
		
		this.id_pedido = id_pedido;
		this.direccion = direccion;
		this.precio_pedido = precio_pedido;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}
	

	//Getter
	public Integer getId_pedido() {
		return id_pedido;
	}

	public String getDireccion() {
		return direccion;
	}

	public Float getPrecio_pedido() {
		return precio_pedido;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public Date getFecha() {
		return fecha;
	}
	//Setter

	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setPrecio_pedido(Float precio_pedido) {
		this.precio_pedido = precio_pedido;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	//CRUD
	public void read() {// Un try catch simple con un query que almacena una consulta simple.
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				ResultSet resultset = statement.executeQuery("SELECT * FROM plato")) {
			// Ahora, por cada fila el resultset, realizamos las operaciones
			// correspondientes.
			while (resultset.next()) {

				Integer id = resultset.getInt("id_pedido");
				String direccion = resultset.getString("direccion");
				Float precio  = resultset.getFloat("precio_pedido");
				Integer cantidad= resultset.getInt("cantidad");
				Date fecha = resultset.getDate("fecha");
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
			st.executeUpdate("INSERT INTO plato (direccion, precio_pedido, cantidad, fecha)" + "VALUES ('"
					+ this.direccion + "','" + this.precio_pedido + "','" + this.cantidad + "'," + this.fecha
					+ ")");
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
			String query = "delete from plato where id=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, this.id_pedido);
			ps.executeUpdate();
			// Aquí empieza el insert.
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO plato (direccion, precio_pedido, cantidad, fecha)" + "VALUES ('"
					+ this.direccion + "','" + this.precio_pedido + "','" + this.cantidad + "'," + this.fecha
					+ ")");
			// For security reasons, we close connections.
			conn.close();
			ps.close();
			st.close();
		} catch (Exception e) {
			System.out.println("No se ha podido updatear la tabla.");
			e.printStackTrace();
		}
	}
			

}