package restaurante;

//Importamos los inputs que vamos a utilizar.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    /**
     * Main will be the principal class that will execute all methods that are
     * located in Logic class.
     *
     * @Param args
     */
    public static void main(String[] args) throws IOException {

        // Inicializamos los inputs.
        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);

        // Initializing variables that will run check loops
        String bienvenida;
        Integer permiso;
        Logica logica = new Logica();                          


        
       //Serie de métodos que definen el oden de ejecución
        bienvenida = logica.bienvenida();
        // Creamos o cargamos al usuario en un objeto Usuario.
        logica.ingreso(bienvenida);
        // Conseguimos almacenar el nivel de permisos del objeto Usuario establecido.
        permiso = logica.usuario1.getPermisos();
        // Llamada a la función menú que es diferente para cada tipo de usuario.
        logica.menu(permiso);
    }
}

