/**
 * 2020-10-17
 * Clase Controller.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package controller;
import model.logic.TaxiTrips;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import com.opencsv.exceptions.CsvValidationException;
import view.View;

/**
 * Se encarga de controlar la entrada y salida de datos por consola.
 */
public class Controller {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Representa el separador al momento de mostrar un encabezado en el visor.
	 */
	public static final String SEPARADOR = "----------------------------------------------------------------------------------------------";

	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/** 
	 * Instancia del sistema de TaxiTrips CitiBike
	 */
	private TaxiTrips taxiTrips;

	/**
	 * Instancia de la Vista
	 */
	private View view;

	// -----------------------------------------------------------------
	// Construtor
	// -----------------------------------------------------------------
	/**
	 * Crea la vista y el modelo del proyecto.
	 */
	public Controller ()
	{
		view = new View();
		taxiTrips = new TaxiTrips();
	}

	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------	

	/**
	 * Se encarga de ejecutar la entrada y salida de datos por consola.
	 * @throws ParseException 
	 * @throws CsvValidationException. Se presenta una excepción si ocurre un error durante el proceso.
	 */
	public void run() throws CsvValidationException, ParseException 
	{
		Scanner lectorStr = new Scanner(System.in);
		Scanner lectorInt = new Scanner(System.in);
		boolean fin = false;
		while( !fin ){
			view.printMenu();
			int option = lectorInt.nextInt();
			switch(option){
			case 1: 
				view.printData();
				int data = lectorInt.nextInt();
				while( !(data>0 && data<=4) )
				{
					view.printMessage(SEPARADOR + "\n Opcion Invalida !! \n");
					data = lectorInt.nextInt();
				}
				
				if( data!= 4){
					taxiTrips.establecerFuenteDatos(data);
					view.printMessage( taxiTrips.cargarDatos() );
				}
				break;

			case 2: 
				view.printMessage( "Digite el número de compañías que desea conocer del TOP de taxis afiliados" );
				int M = lectorInt.nextInt();
				view.printMessage( "Digite el número de compañías que desea conocer del TOP de más servicios prestados" );
				int N = lectorInt.nextInt();
				view.printMessage(taxiTrips.R2());
				view.printMessage(taxiTrips.R3());
				view.printMessage(taxiTrips.R4(N));
				view.printMessage(taxiTrips.R5(M));
				break; 

			case 3: 
				view.printMessage("Ingrese el número de Taxis que desea identificar");
				int N2 = lectorInt.nextInt();
				view.printMessage("Ingrese una fecha válida. Formato AAAA-MM-DD (e.g. 2001-03-11):");
				String fecha = lectorStr.nextLine();
				DateFormat fechaHora2 = new SimpleDateFormat("yyyy-MM-dd");
				view.printMessage(taxiTrips.R6(fechaHora2.parse(fecha), N2));
				break; 

			case 4: 
				view.printMessage("Ingrese el número de Taxis que desea identificar");
				int M2 = lectorInt.nextInt();
				view.printMessage("Ingrese el rango inicial de una fecha válida. Formato AAAA-MM-DD (e.g. 2001-03-11):");
				String fechaInicial = lectorStr.nextLine();
				view.printMessage("Ingrese el rango inicial de una fecha válida. Formato AAAA-MM-DD (e.g. 2001-03-11):");
				String fechaFinal = lectorStr.nextLine();
				DateFormat fechaHora3 = new SimpleDateFormat("yyyy-MM-dd");
				view.printMessage(taxiTrips.R(fechaHora3.parse(fechaInicial), fechaHora3.parse(fechaFinal), M2));
				break;
				
			case 5:
				view.printMessage("Ingrese el area origen del viaje");
				int origen = lectorInt.nextInt();
				view.printMessage("Ingrese el area destino del viaje");
				int destino = lectorInt.nextInt();
				view.printMessage("Ingrese el rango de tiempo en el día en el que se quiere inicial el viaje");
				String tiempo = lectorStr.nextLine();
				view.printMessage(taxiTrips.consultarMejorHorario(origen, destino, tiempo));
				break; 
			}
		}
		lectorStr.close();
		lectorInt.close();
	}	
}