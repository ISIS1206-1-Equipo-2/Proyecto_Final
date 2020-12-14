/**
 * 2020-10-17
 * Clase Controller.
 * @author Juli�n Andr�s M�ndez
 * @author Juan Miguel Vega Caro
 */

package main;
import java.text.ParseException;

import com.opencsv.exceptions.CsvValidationException;
import controller.Controller;

/**
 * Permite la ejecuci�n del programa en el compilador de Java.
 */
public class Main {
	
	// -----------------------------------------------------------------
	// M�todos
	// -----------------------------------------------------------------	

	/**
	 * Ejecuta el programa.
	 * @param String[] args. Cadena de caracteres que permite la ejecuci�n del programa.
	 * @throws ParseException 
	 * @throws CsvValidationException 
	 */
	public static void main(String[] args) throws CsvValidationException, ParseException 
	{
		Controller controler = new Controller();
		controler.run();

	}
}