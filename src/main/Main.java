/**
 * 2020-10-17
 * Clase Controller.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */

package main;
import java.text.ParseException;

import com.opencsv.exceptions.CsvValidationException;
import controller.Controller;

/**
 * Permite la ejecución del programa en el compilador de Java.
 */
public class Main {
	
	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------	

	/**
	 * Ejecuta el programa.
	 * @param String[] args. Cadena de caracteres que permite la ejecución del programa.
	 * @throws ParseException 
	 * @throws CsvValidationException 
	 */
	public static void main(String[] args) throws CsvValidationException, ParseException 
	{
		Controller controler = new Controller();
		controler.run();

	}
}