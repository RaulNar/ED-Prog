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
		
		protected Integer id_pedido;
		
		protected Integer id_pago;
		
		protected Integer id_sucursal;
		
		protected Date fecha_factura;
		
		protected Float monto_total;
		
	// Constant used in connection.
		
		final String url = "jdbc:mysql://localhost:3306/far_far_west_inc_";

	
	// Builders
	
		public Factura(Integer id_factura, Integer id_pedido, Integer id_pago, Integer id_sucursal, Date fecha_factura,
				Float monto_total) {
			this.id_factura = id_factura;
			this.id_pedido = id_pedido;
			this.id_pago = id_pago;
			this.id_sucursal = id_sucursal;
			this.fecha_factura = fecha_factura;
			this.monto_total = monto_total;
		}

	   public Factura() {
		   
		   
	    }

	//Getter
	
		public Integer getId_factura() {
			return id_factura;
		}

		public Integer getId_pedido() {
			return id_pedido;
		}

		public Integer getId_pago() {
			return id_pago;
		}

		public Integer getId_sucursal() {
			return id_sucursal;
		}

		public Date getFecha_factura() {
			return fecha_factura;
		}

		public Float getMonto_total() {
			return monto_total;
		}
		
		//Setter

		public void setId_factura(Integer id_factura) {
			this.id_factura = id_factura;
		}

		public void setId_pedido(Integer id_pedido) {
			this.id_pedido = id_pedido;
		}

		public void setId_pago(Integer id_pago) {
			this.id_pago = id_pago;
		}

		public void setId_sucursal(Integer id_sucursal) {
			this.id_sucursal = id_sucursal;
		}

		public void setFecha_factura(Date fecha_factura) {
			this.fecha_factura = fecha_factura;
		}

		public void setMonto_total(Float monto_total) {
			this.monto_total = monto_total;
		}
	

	//CRUD
	public void read() {// Un try catch simple con un query que almacena una consulta simple.
		try (Connection connection = DriverManager.getConnection(url, "root", "");
				// Creamos el objeto Statement que nos permitirá realizar Querys
				Statement statement = connection.createStatement();
				// A raiz del Statemet, obtenemos el resultado del executeQuery en un resultset
				ResultSet resultset = statement.executeQuery("SELECT * FROM factura")) {
			// Ahora, por cada fila el resultset, realizamos las operaciones
			// correspondientes.
			while (resultset.next()) {

				Integer id = resultset.getInt("id_factura");
				Integer id_pedido = resultset.getInt("id_pedido");
				Integer id_pago = resultset.getInt("id_pago");
				Integer id_sucursal = resultset.getInt("id_sucursal");
				Float precio = resultset.getFloat("monto_total");
				Date fecha_factura  = resultset.getDate("fecha_factura");
				System.out.println(id + "\t" +id_pedido + "\t" +id_pago + "\t" +id_sucursal+ "\t" + fecha_factura + "\t" + precio + "\n");
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
			st.executeUpdate("INSERT INTO factura (id_factura,id_pedido, id_pago, id_sucursal, monto_total, fecha_factura)" + "VALUES ('"
					+ this.id_factura + "','"+ this.id_pedido + "','" + this.id_pago + "','" + this.id_sucursal + "','"+ this.monto_total + "','" + this.fecha_factura
					+ "')");
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
			String query = "delete from factura where id=? ";
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
			String query = "delete from factura where id=? ";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, this.id_factura);
			ps.executeUpdate();
			// Aquí empieza el insert.
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO factura (id_factura,id_pedido, id_pago, id_sucursal, monto_total, fecha_factura)" + "VALUES ('"
					+ this.id_factura + "','"+ this.id_pedido + "','" + this.id_pago + "','" + this.id_sucursal + "','"+ this.monto_total + "','" + this.fecha_factura
					+ "')");
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