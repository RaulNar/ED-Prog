package restaurante;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Clase Pago
 * Propósito: Diseño de la clase Pago e implementación del CRUD
 * Autor: Asunción de los Ángeles Naranjo Rodríguez
 * Fecha: 2024/05/2024
 * */

public class Pago implements CRUD{

	// Attributes
	
			protected Integer id_pago;

			protected Tipo_pago tipo_pago;
			
			protected Date fecha_factura;
			
			protected String n_tarjeta;
			
			protected Date fecha_pago;
			
			
			
	// Constant used in connection.
			
			final String url = "jdbc:mysql://localhost:3306/far far west inc.";


	// Builders
			
	public Pago(Integer id_pago, Tipo_pago tipo_pago, Date fecha_factura, String n_tarjeta, Date fecha_pago) {
		this.id_pago = id_pago;
		this.tipo_pago = tipo_pago;
		this.fecha_factura = fecha_factura;
		this.n_tarjeta = n_tarjeta;
		this.fecha_pago = fecha_pago;
	}


	//Getter
	
	public Integer getId_pago() {
		return id_pago;
	}


	public Tipo_pago getTipo_pago() {
		return tipo_pago;
	}


	public Date getFecha_factura() {
		return fecha_factura;
	}


	public String getN_tarjeta() {
		return n_tarjeta;
	}


	public Date getFecha_pago() {
		return fecha_pago;
	}


	//Setter
	
	public void setId_pago(Integer id_pago) {
		this.id_pago = id_pago;
	}


	public void setTipo_pago(Tipo_pago tipo_pago) {
		this.tipo_pago = tipo_pago;
	}


	public void setFecha_factura(Date fecha_factura) {
		this.fecha_factura = fecha_factura;
	}


	public void setN_tarjeta(String n_tarjeta) {
		this.n_tarjeta = n_tarjeta;
	}


	public void setFecha_pago(Date fecha_pago) {
		this.fecha_pago = fecha_pago;
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

					Integer id = resultset.getInt("id_pago");
					String tipo_pago = resultset.getString("tipo_pago");
					Date fecha_factura  = resultset.getDate("fecha_factura");
					String n_tarjeta = resultset.getString("n_tarjeta");
					Date fecha_pago = resultset.getDate("fecha_pago");
					System.out.println(id + "\t" + tipo_pago + "\t" + fecha_factura + "\t" + n_tarjeta + "\t" + fecha_pago + "\n");
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
				st.executeUpdate("INSERT INTO plato (tipo_pago, fecha_factura, n_tarjeta, fecha_pago)" + "VALUES ('"
						+ this.tipo_pago + "','" + this.fecha_factura + "','" + this.n_tarjeta + "'," + this.fecha_pago
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
				ps.setInt(1, this.id_pago);
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
				ps.setInt(1, this.id_pago);
				ps.executeUpdate();
				// Aquí empieza el insert.
				Statement st = conn.createStatement();
				st.executeUpdate("INSERT INTO plato (tipo_pago, fecha_factura, n_tarjeta, fecha_pago)" + "VALUES ('"
						+ this.tipo_pago + "','" + this.fecha_factura + "','" + this.n_tarjeta + "'," + this.fecha_pago +
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