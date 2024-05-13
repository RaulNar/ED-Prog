package restaurante;

//Importamos los inputs que vamos a utilizar.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Logica {

  // Inicializamos los inputs.
  static InputStreamReader in = new InputStreamReader(System.in);
  static BufferedReader br2 = new BufferedReader(in);

  // Las variables que utilizaremos para varios procesos lógicos.
  String comprobacion1;
  Integer comprobacion2 = -1;

  Usuario usuario1 = new Usuario();
  Persona persona1 = new Persona();
  Cliente cliente1 = new Cliente();
  Reserva reserva1 = new Reserva();
  Sucursal sucursal1 = new Sucursal();
  Trabajador trabajador1 = new Trabajador();
  Ingrediente ingrediente1 = new Ingrediente();
  LineaPedido lineaPedido1 = new LineaPedido();
  Plato plato1 = new Plato();
  Pedido pedido1 = new Pedido();
  Pago pago1 = new Pago();
  Factura factura1 = new Factura();



  // Comprueba que el valor especificado entra en el rango "S", "s", "N" o "n".
  /**
   * @Method sino: method that receives a string and makes sure that it does belong in the
   * range of (S, s, n, N).
   *
   * @param n , (sting) typically an S or an N, but it can receive any other.
   *
   * @return String of a single character that means yes or no, belongs in the
   * range of (S, s, n, N).
   */
  public static String sino(String n) throws IOException {
      while (!n.equals("s") && !n.equals("n") && !n.equals("N") && !n.equals("S")) {
          System.out.println("Por favor, introduzca una respuesta válida");
          n = br2.readLine();
      }
      return n;
  }

  // Comprueba que el valor especificado entra en el rango "S", "s", "N" o "n".

  /**
   * @Method unoCinco: method that receives a string and makes sure that it does belong in the
   * range of (S, s, n, N).
   *
   * @param m , (integer) a value between 1 and 5.
   *
   * @return Integer a value between 1 and 5.
   */
   public static Integer unoCinco(Integer m) throws IOException {
      while (m != 1 && m != 2 && m != 3 && m != 4 && m!= 5) {
          System.out.println("Por favor, introduzca un valor válido");
          m = Integer.parseInt(br2.readLine());
      }
      return m;
  }

  // Función que obtiene una String "S", "s", "N" o "n" que utilizaremos más
  // adelante.

  /**
   * @Method bienvenida: method that receives the user and takes the information needed for next
   * steps.
   *
   * @return String of a single character that means yes or no, ideally in the
   * range of (S, s, n, N).
   */
  public String bienvenida() throws IOException {
      System.out.println("Está usando la aplicación Far Far West.");
      System.out.println("¿Es usted un usuario ya registrado? (S/N)");
      comprobacion1 = br2.readLine();
      comprobacion1 = sino(comprobacion1);
      return comprobacion1;
  }

  // Le ofrece los atributos al objeto usuario desde la base de datos, o lo crea y
  // lo inserta en la base de datos.
  /**
   * @Method ingreso: method that checks out if you have a user id in the DB and if so, grants you
   * access. Else, lets you create a user and access the application in
   * accordance with the level of security assigned to the user.
   *
   * @param comprobacion1 ,(string) typically an S or an N.
   */
  public void ingreso(String comprobacion1) throws IOException {

      if (comprobacion1.equals("S") || comprobacion1.equals("s")) {//datos por los que buscar en la BD.

          String entryName;
          String entryPassword;

          System.out.println("Por favor, introduzca su nombre de usuario.");
          entryName = br2.readLine();
          System.out.println("Ahora, introduzca su contraseña");
          entryPassword = br2.readLine();

          if (usuario1.getUsuario(entryName, entryPassword)) {// caso éxito.

              System.out.println("Su usuario ha sido verificado. Bienvenido de nuevo " + usuario1.getNombre_usuario());

          } else { //caso fallo.

              System.out.println("Su usuario no ha podido ser verificado. ¿Desea intentarlo de nuevo? (S/N)");
              comprobacion1 = br2.readLine();
              sino(comprobacion1);

              if (comprobacion1.equals("S") || comprobacion1.equals("s")) {

                  ingreso(comprobacion1);

              } else { // abandono de aplicación

                  System.out.println("Gracias por su tiempo.");
                  System.exit(0);

              }
          }

      } else { //creamos el usuario y el cliente

          String ndi;
          String name;
          String surname;
          Integer age;
          String addres;
          Integer phone;
          String password;
          Integer permits;
          String usser_name;

          System.out.println("Debe registrarse para poder hacer uso de la aplicación");
          System.out.println("Por favor, introduzca su dni: ");
          ndi = br2.readLine();
          System.out.println("Por favor, introduzca su nombre real: ");
          name = br2.readLine();
          System.out.println("Por favor, introduzca su apellido: ");
          surname = br2.readLine();
          System.out.println("Por favor, introduzca su edad: ");
          age = Integer.parseInt(br2.readLine());
          System.out.println("Por favor, introduzca su dirección: ");
          addres = br2.readLine();
          System.out.println("Por favor, introduzca su número de teléfono: ");
          phone = Integer.parseInt(br2.readLine());
          System.out.println("Por favor, introduzca su contraseña: ");
          password = br2.readLine();
          System.out.println("Por favor, introduzca su nombre de usuario: ");
          usser_name = br2.readLine();
          permits = 1;

          cliente1.setDni(ndi);
          cliente1.setNombre(name);
          cliente1.setApellido(surname);
          cliente1.setEdad(age);
          cliente1.setDireccion(addres);
          cliente1.setTelefono(phone);
          cliente1.setNº_reservas(0);
          cliente1.setId_pedido_fav(0);
          usuario1.setDni(ndi);
          usuario1.setContraseña(password);
          usuario1.setNombre_usuario(usser_name);
          usuario1.setPermisos(permits);

          cliente1.insert();
          usuario1.insert();
          System.out.println("Su usuario ha sido registrado. Bienvenido " + usuario1.getNombre_usuario());

      }
      
  }
  
  /**
   * @throws IOException 
 * @throws NumberFormatException 
 * @Method pagar: method that records the placement of an order
   */
  public void pagarPedido() throws IOException {
	  Integer id_pedido;
	  String n_tarjeta;
	  
	  System.out.println("Estos son los pedidos realizados por clientes");
	  pedido1.read();
	 
	  System.out.println("Introduce el número de pedido");
	  id_pedido = Integer.parseInt(br2.readLine());
	  
	  System.out.println("Introduce el número de tu tarjeta de crédito");
	  n_tarjeta =  br2.readLine();
	  
	 pago1.setN_tarjeta(n_tarjeta);
	 pago1.pagar(id_pedido);
  }
  
  
  /**
   * @Method pedir: method that allows you to order a dish
   */
  public void pedir() throws IOException {

	    Integer servidor;
	    String direccion;
	    Float precio_pedido;
	    String fecha;
	  
	    //Solicitamos al usuario los datos del pedido
	    System.out.println("Introduzca los datos de pedido");
	      System.out.println("Por favor, introduzca el id del servidor: ");
	      servidor =Integer.parseInt(br2.readLine());
	      System.out.println("Por favor, introduzca su dirección: ");
	      direccion = br2.readLine();
	      System.out.println("Por favor, introduzca el importe total de su compra: ");
	      precio_pedido = Float.parseFloat(br2.readLine());
	      System.out.println("Por favor, introduzca la fecha de su compra ");
	      fecha = br2.readLine();
	      
	      //Asignamos los datos del usuario al pedido
	    pedido1.setId_cliente(1); //Asignamos el id cliente cuando se modifique la clase cliente
	    pedido1.setServidor(servidor);
	    pedido1.setDireccion(direccion);
	    pedido1.setPrecio_pedido(precio_pedido);
	    pedido1.setFecha(fecha);
	    
	    pedido1.insert();
  }
  /**
   * @Method rellenarDatosCliente: method that fills in a client`s data
   */
  public void rellenarDatosCliente() throws IOException {
	  String ndi;
      String name;
      String surname;
      Integer age;
      String addres;
      Integer phone;
      Integer idPedidoFav;

    //Solicitamos al usuario los datos de cliente
      System.out.println("Introduzca los datos de usuario");
      System.out.println("Por favor, introduzca su dni: ");
      ndi = br2.readLine();
      System.out.println("Por favor, introduzca su nombre real: ");
      name = br2.readLine();
      System.out.println("Por favor, introduzca su apellido: ");
      surname = br2.readLine();
      System.out.println("Por favor, introduzca su edad: ");
      age = Integer.parseInt(br2.readLine());
      System.out.println("Por favor, introduzca su dirección: ");
      addres = br2.readLine();
      System.out.println("Por favor, introduzca su número de teléfono: ");
      phone = Integer.parseInt(br2.readLine());
      System.out.println("Por favor, introduzca el id de su pedido favorito: ");
      idPedidoFav = Integer.parseInt(br2.readLine());

    //Asignamos los datos del usuario al pedido
      cliente1.setDni(ndi);
      cliente1.setNombre(name);
      cliente1.setApellido(surname);
      cliente1.setEdad(age);
      cliente1.setDireccion(addres);
      cliente1.setTelefono(phone);
      cliente1.setId_pedido_fav(idPedidoFav);

      cliente1.update();

      System.out.println("Sus datos han sido actualizados correctamente.");

  }

  /**
   * @Method menu: method that offers different options depending on the access level that the variable permisos hold.
   * It displays a menu of options.
   */
  public void menu(int permit) throws IOException {
      if (permit == 1) {
    	  while (comprobacion2 !=5) {
	          System.out.println("Menú de Cliente:");
	          System.out.println("Introduzca el número asociado a las opciones para su ejecución:");
	          System.out.println("1: Hacer un pedido: ");
	          System.out.println("2: Consultar los platos de la carta: ");
	          System.out.println("3: Rellenar sus datos: ");
	          System.out.println("4: Pagar: ");
	          System.out.println("5: Salir de la aplocación ");
	          comprobacion2 = Integer.parseInt(br2.readLine());
	          comprobacion2 = unoCinco(comprobacion2);
	
	          switch(comprobacion2) {
	        	case 1:
	        		pedir();
	        		break;
	          	case 2:
	          		plato1.consultarPlatos();
	        	  break;
	          	case 3:
	          		rellenarDatosCliente();
	        	  break;
	          	case 4:
	          		pagarPedido();
	        	  break;
	          	case 5:
	                System.exit(0);
	        	  break;
	        	default:
	        		System.out.println("Error, introduce de nuevo un valor.");
	          }
    	 }
      } else if (permit == 2) {
    	  while (comprobacion2 !=5) {
	          System.out.println("Menú de Trabajador:");
	          System.out.println("Introduzca el número asociado a las opciones para su ejecución:");
	          System.out.println("1: ver pedidos pendientes, 2: Consultar lista de clientes, 3: consultar la disponibilidad de un ingrdiente, 4:Consultar las facturas de un cliente. 5 Salir de la aplicación.");
	          comprobacion2 = Integer.parseInt(br2.readLine());
	          comprobacion2 = unoCinco(comprobacion2);
	
	          switch(comprobacion2) {
	
	              case 1:
	
	                  System.out.println("Los pedidos que todavía no han sidos asignados a un servidor son: ");
	                  pedido1.getPendientes();
	                  break;
	
	              case 2:
	
	                  System.out.println("Los clientes actuales son:");
	                  cliente1.read();
	                  break;
	
	              case 3:
	
	
	                  break;
	
	              case 4:
	
	
	                  break;
	
	              case 5:
	
	                  System.exit(0);
	          }
    	  }

      } else {
    	  
    	  while (comprobacion2 !=5) {
    		  
    	  }

      }
  }
}
