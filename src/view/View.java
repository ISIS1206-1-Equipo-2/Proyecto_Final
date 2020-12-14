/**
 * 2020-10-17
 * Clase View.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package view;
/**
 * Se encarga de mostrar el menú de opciones en el programa.
 */
public class View 
{
	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------
	
	/**
	 * Representa el separador al momento de mostrar un encabezado en el visor.
	 */
	public static final String SEPARADOR = "---------------------------";
	
	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------
	
	/**
	 * Se encarga de crear el visor de opciones.
	 */
	public View()
	{

	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------
	
	/**
	 * Muestra las opciones que tiene el usuario para escoger en el programa.
	 */
	public void printMenu()
	{
		System.out.println("\n"+SEPARADOR + " BIENVENIDO AL SISTEMA DE SERVICIOS DE TAXI EN CHICAGO " + SEPARADOR + "\n" );
		System.out.println("1. Realizar la carga de datos en el sistema TaxiTrips");
		System.out.println("2. Crear un reporte");
		System.out.println("3. Identificar Taxis con más puntos para una fecha determinada");
		System.out.println("4. Identificar Taxis con más puntos para un rango entre dos fechas");
		System.out.println("5. Consultar el mejor horario para desplazarce entre dos Community Area");
	}

	/**
	 * Muestra un mensaje en el visor del programa.
	 * @param String mensaje. Mensaje a mostrar en consola.
	 */
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}		
	
	/**
	 * Muestra un mensaje en el visor del programa con las opciones de cargar archivos.
	 */
	public void printData( ){
		System.out.println(SEPARADOR + "--- CARGA DE DATOS EN EL SISTEMA ----------------------" + SEPARADOR + "\n" );

		System.out.println("1. Cargar Small data");
		System.out.println("2. Cargar Medium data");
		System.out.println("3. Cargar Large data");
		System.out.println("4. Salir.");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}
}
