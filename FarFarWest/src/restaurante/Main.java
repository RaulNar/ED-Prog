package restaurante;

public class Main {
	public static void main(String[] args) {

		Cliente p1 = new Cliente("12345677b", "Fulanito", "Ferrera", 22, "C/Mendala", 954744444, 3, 1);
		Cliente p2 = new Cliente("12345666c", "Mengana", "Carola", 22, "C/Margarita", 954733333, 4, 1);
		p1.insert();
		p2.insert();
		/*
		 * Sucursal s1 = new Sucursal(1, "Los pollos hermanos", "C/Ramales", 954744444,
		 * "cosas@gmail.com", "09:00"); s1.mostrar();
		 * 
		 * Plato pl1 = new Plato(1, "Pasta", "Spagetti Western",
		 * "Spaguetti con chille y carne, aderezado con queso Fiordo di bufalla",
		 * 15.25F); pl1.mostrar();
		 */
	}

}
