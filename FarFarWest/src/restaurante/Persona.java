package restaurante;

/*
 * Esta clase tiene poco contenido, dado que: son las clases hijas, cliente y trabajador, los que llevan toda la lógica a ejecución.
 * No se conecta nunca a la base de datos, puesto que es sólo un contenedor de atributos y son las clases hijas las que ejecutan los
 * métodos que generan cambios tanto en la tabla persona como en la propia de la clase hija.
 */

public class Persona {

	// Attributes
	protected String dni;

	protected String nombre;

	protected String apellido;

	protected Integer edad;

	protected String direccion;

	protected Integer telefono;

	// Builders
	public Persona(String ind, String name, String surname, Integer age, String address, Integer telephone) {
		this.dni = ind;
		this.nombre = name;
		this.apellido = surname;
		this.edad = age;
		this.direccion = address;
		this.telefono = telephone;
	}
}