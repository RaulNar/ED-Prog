package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Clase Factura
 * Propósito: Diseño de la clase Factura e implementación del CRUD
 * Autor: Asunción de los Ángeles Naranjo Rodríguez
 * Fecha: 2024/05/2024
 * */


public class Factura implements CRUD{
	
	// Attributes
	
		protected Integer id_factura;

		protected Float precio;
		
		protected Date fecha_factura;
		
		protected String mesa;
		
	// Constant used in connection.
		
		final String url = "jdbc:mysql://localhost:3306/far far west inc.";

	
	// Builders
		
	public Factura(Integer id_factura, Float precio, Date fecha_factura, String mesa) {
		this.id_factura = id_factura;
		this.precio = precio;
		this.fecha_factura = fecha_factura;
		this.mesa = mesa;
	}


	//Getter
	
	public Integer getId_factura() {
		return id_factura;
	}


	public Float getPrecio() {
		return precio;
	}


	public Date getFecha_factura() {
		return fecha_factura;
	}


	public String getMesa() {
		return mesa;
	}


	
	//Setter
	
	public void setId_factura(Integer id_factura) {
		this.id_factura = id_factura;
	}


	public void setPrecio(Float precio) {
		this.precio = precio;
	}


	public void setFecha_factura(Date fecha_factura) {
		this.fecha_factura = fecha_factura;
	}


	public void setMesa(String mesa) {
		this.mesa = mesa;
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

				Integer id = resultset.getInt("id_factura");
				Float precio = resultset.getFloat("precio");
				Date fecha_factura  = resultset.getDate("fecha_factura");
				String mesa = resultset.getString("mesa");
				System.out.println(id + "\t" + precio + "\t" + fecha_factura + "\t" + mesa + "\n");
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
			st.executeUpdate("INSERT INTO plato (precio, fecha_factura, mesa)" + "VALUES ('"
					+ this.precio + "','" + this.fecha_factura + "','" + this.mesa + "'"
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
			ps.setInt(1, this.id_factura);
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
			ps.setInt(1, this.id_factura);
			ps.executeUpdate();
			// Aquí empieza el insert.
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO plato (precio, fecha_factura, mesa)" + "VALUES ('"
					+ this.precio + "','" + this.fecha_factura + "','" + this.mesa + "'" +
					")");
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