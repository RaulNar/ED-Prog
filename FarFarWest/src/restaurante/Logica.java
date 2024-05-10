package restaurante;

//Importamos los inputs que vamos a utilizar.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Logica {

	// Inicializamos los inputs.
	static InputStreamReader in = new InputStreamReader(System.in);
	static BufferedReader br2 = new BufferedReader(in);

	// Loas variables que utilizaremos para varios procesos lógicos.
	String comprobacion1;

	Usuario u1 = new Usuario();

	// Función que obtiene una String "S", "s", "N" o "n" que utilizaremos más
	// adelante.
	public String bienvenida() throws IOException {
		System.out.println("Está usando la aplicación Far Far West.");
		System.out.println("¿Es usted un usuario ya registrado? (S/N)");
		comprobacion1 = br2.readLine();
		comprobacion1 = sino(comprobacion1);
		return comprobacion1;
	}

	// Comprueba que el valor especificado entra en el rango "S", "s", "N" o "n".
	public static String sino(String n) throws IOException {
		while (!n.equals("s") && !n.equals("n") && !n.equals("N") && !n.equals("S")) {
			System.out.println("Por favor, introduzca una respuesta válida");
			n = br2.readLine();
		}
		return n;
	}

	// Le ofrece los atributos al objeto usuario desde la base de datos, o lo crea y
	// lo inserta en la base de datos.
	public void ingreso(String comprobacion) throws IOException {

		if (comprobacion.equals("S") || comprobacion.equals("s")) {

			String entryName;
			String entryPassword;

			System.out.println("Por favor, introduzca su nombre de usuario.");
			entryName = br2.readLine();
			System.out.println("Ahora, introduzca su contraseña");
			entryPassword = br2.readLine();

			if (u1.getObject(entryName, entryPassword)) {

				System.out.println("Su usuario ha sido verificado. Bienvenido de nuevo " + u1.getNombre_usuario());

			} else {

				System.out.println("Su usuario no ha podido ser verificado. ¿Desea intentarlo de nuevo? (S/N)");
				comprobacion1 = br2.readLine();
				sino(comprobacion1);

				if (comprobacion.equals("S") || comprobacion.equals("s")) {

					ingreso(comprobacion1);

				} else {

					System.out.println("Gracias por su tiempo.");
					System.exit(0);

				}
			}

		} else {

			String ndi;
			String password;
			Integer permits;
			String usser_name;

			System.out.println("Debe registrarse para poder hacer uso de la aplicación");
			System.out.println("Por favor, introduzca su dni: ");
			ndi = br2.readLine();
			System.out.println("Por favor, introduzca su contraseña: ");
			password = br2.readLine();
			System.out.println("Por favor, introduzca su nombre de usuario: ");
			usser_name = br2.readLine();
			permits = 1;

			u1.setDni(ndi);
			u1.setContraseña(password);
			u1.setNombre_usuario(usser_name);
			u1.setPermisos(permits);
			u1.insert();
			System.out.println("Su usuario ha sido registrado. Bienvenido " + u1.getNombre_usuario());

		}
	}
}
