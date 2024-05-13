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
			
			protected Integer id_pedido;
			
			protected String nº_tarjeta;
			
			protected Date fecha_pago;
			
			
			
	// Constant used in connection.
			
			final String url = "jdbc:mysql://localhost:3306/far_far_west_inc_";

	// Builders
			
	public Pago(Integer id_pago,Integer id_pedido, String nº_tarjeta, Date fecha_pago) {
		this.id_pago = id_pago;
		this.id_pedido = id_pedido;
		this.nº_tarjeta = nº_tarjeta;
		this.fecha_pago = fecha_pago;
	}
	

	public Pago() {
		
    }


	//Getter
	
	public Integer getId_pago() {
		return id_pago;
	}

	public Integer getId_pedido() {
		return id_pedido;
	}


	public String getN_tarjeta() {
		return nº_tarjeta;
	}


	public Date getFecha_pago() {
		return fecha_pago;
	}


	//Setter
	
	public void setId_pago(Integer id_pago) {
		this.id_pago = id_pago;
	}
	
	public void setId_pedido(Integer id_pedido) {
		this.id_pedido = id_pedido;
	}

	public void setN_tarjeta(String n_tarjeta) {
		this.nº_tarjeta = n_tarjeta;
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
					ResultSet resultset = statement.executeQuery("SELECT * FROM pago")) {
				// Ahora, por cada fila el resultset, realizamos las operaciones
				// correspondientes.
				while (resultset.next()) {

					Integer id = resultset.getInt("id_pago");
					Integer id_pedido = resultset.getInt("id_pedido");
					String n_tarjeta = resultset.getString("nº_tarjeta");
					Date fecha_pago = resultset.getDate("fecha_pago");
					System.out.println(id + "\t" + id_pedido + "\t" + n_tarjeta + "\t" + fecha_pago + "\n");
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
				st.executeUpdate("INSERT INTO pago (id_pedido, nº_tarjeta, fecha_pago)" + "VALUES ('"
						+ this.id_pedido + "','" + this.nº_tarjeta + "'," + this.fecha_pago
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
				String query = "delete from pago where id=? ";
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
				String query = "delete from pago where id=? ";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setInt(1, this.id_pago);
				ps.executeUpdate();
				// Aquí empieza el insert.
				Statement st = conn.createStatement();
				st.executeUpdate("INSERT INTO pago (id_pedido, nº_tarjeta, fecha_pago)" + "VALUES ('"
						+ this.id_pedido + "','" + this.nº_tarjeta + "'," + this.fecha_pago +
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
		
		//Function that pays an order.
		public void pagar(Integer id_pedido){
			this.setId_pedido(id_pedido);
			this.setFecha_pago(new Date());
			this.insert();
		}
			
}

