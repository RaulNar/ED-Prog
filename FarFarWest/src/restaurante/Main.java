package restaurante;

//Importamos los inputs que vamos a utilizar.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws IOException {

		// Inicializamos los inputs.
		InputStreamReader in = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(in);

		// Initializing variables that will run check loops
		String bienvenida;
		Integer permiso;
		Logica logica = new Logica();

		// Cliente p1 = new Cliente("12345677b", "Fulanito", "Ferrera", 22, "C/Mendala",
		// 954744444, 3, 1);
		// Cliente p2 = new Cliente("12345666c", "Mengana", "Carola", 22, "C/Margarita",
		// 954733333, 4, 1);
		// p1.insert();
		// p2.insert();
		/*
		 * Sucursal s1 = new Sucursal(1, "Los pollos hermanos", "C/Ramales", 954744444,
		 * "cosas@gmail.com", "09:00"); s1.mostrar();
		 * 
		 * Plato pl1 = new Plato(1, "Pasta", "Spagetti Western",
		 * "Spaguetti con chille y carne, aderezado con queso Fiordo di bufalla",
		 * 15.25F); pl1.mostrar();
		 */

		// Damos la bieenvenida y preguntamos si tiene un usuario o no, almacenamos un
		// String (S/N);
		bienvenida = logica.bienvenida();
		// Creamos o cargamos al usuario en un objeto Usuario.
		logica.ingreso(bienvenida);
		// Conseguimos almacenar el nivel de permisos del objeto Usuario establecido.
		permiso = logica.u1.getPermisos();

	}
}
