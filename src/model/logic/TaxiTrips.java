package model.logic;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;
import com.sun.javafx.scene.traversal.WeightedClosestCorner;

import model.structures.algorithms.DijkstraSP;
import model.structures.binarySearchTree.RedBlackTree;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;
import model.structures.binarySearchTree.*;
import model.structures.listaComparable.*;

/**
 * Representa la clase de servicios de Taxis en Chicago. 
 */
public class TaxiTrips {

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------

	/**
	 * Representa el separador al momento de mostrar un encabezado en el visor.
	 */
	public static final String SEPARADOR = "-------------------------------------------------------------------------------------------------------------";

	/**
	 * Representa la ruta donde se encuentra el excel que contiene small.csv
	 */
	public static final String DATA_SMALL = "data/taxi-trips-wrvz-psew-subset-small.csv";

	/**
	 * Representa la ruta donde se encuentra el excel que contiene medium.csv
	 */
	public static final String DATA_MEDIUM = "data/taxi-trips-wrvz-psew-subset-medium.csv";

	/**
	 * Representa la ruta donde se encuentra el excel que contiene large.csv
	 */
	public static final String DATA_LARGE = "data/taxi-trips-wrvz-psew-subset-large.csv";


	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------

	/**
	 * Arbol rojo-negro que representa el TAD utilizado en la parte A con llave compañía y valor arreglo de ID's 
	 */
	private RedBlackTree<String, ArregloDinamicoGenerico<String>> redBlackThreeParteA;
	private RedBlackBST<String, listaDoble<tax>> arbolR2;
	private RedBlackBST<String, listaDoble<tax>> arbolR3;
	private RedBlackBST<Date, listaDoble<tax2>> arbolR4;

	/**
	 * Arbol rojo-negro que representa el TAD utilizado en la parte B con llave fecha y valor arreglo de ID's junto con su puntaje.  
	 */
	private RedBlackTree redBlackThreeParteB;

	/**
	 * Grafo que representa los viajes de los Taxis en la parte C.
	 */
	private DiGraph<String, String, Integer> diGraphParteC;	


	/**
	 * Representa los datos fuentes a ser cargados en el sistema.
	 */
	public String data;

	// -----------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------

	/**
	 * Crea una organización de TaxiTrips en el programa. 
	 */

	public TaxiTrips(){
		redBlackThreeParteA = new RedBlackTree<String, ArregloDinamicoGenerico<String>>();
		redBlackThreeParteB = new RedBlackTree();
		diGraphParteC = new DiGraph<String, String, Integer>();
		data = "None";
		arbolR2 = new RedBlackBST<String, listaDoble<tax>>();
		arbolR3 = new RedBlackBST<String, listaDoble<tax>>();
		arbolR4 = new RedBlackBST<Date, listaDoble<tax2>>();

	}



	// -----------------------------------------------------------------
	// Métodos
	// -----------------------------------------------------------------

	/**
	 * Establece el archivo que el usario desea revisar. 
	 * @param int data. número de archivo a cargar.
	 */
	public void establecerFuenteDatos( int data ){
		switch(data){
		case 1:
			this.data = "DATA_SMALL";
			break;

		case 2:
			this.data = "DATA_MEDIUM";
			break;

		case 3:
			this.data = "DATA_LARGE";
			break;
		}
	}

	/**
	 * Establece el archivo en el bufferReader.
	 * @param Reader reader. Objeto Reader para leer.
	 */
	private Reader reader(Reader reader, String data)
	{
		try{
			switch(data){
			case "DATA_SMALL":
				reader = Files.newBufferedReader(Paths.get(DATA_SMALL));
				break;

			case "DATA_MEDIUM":
				reader = Files.newBufferedReader(Paths.get(DATA_MEDIUM));
				break;

			case "DATA_LARGE":
				reader = Files.newBufferedReader(Paths.get(DATA_LARGE));
				break;
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		return reader;
	}

	// -----------------------------------------------------------------
	// CARGA DE INFORMACIÓN. 
	// -----------------------------------------------------------------

	/**
	 * Se encarga de cargar los datos a la estructura de datos de grafo dirigido.
	 * @throws CsvValidationException Lanza excepción si se presenta un problema al leer el archivo CSV.
	 * @return Retorna el número de viajes leídos de los archivos.
	 * Retorna el número total de estaciones (vértices).
	 * Retorna el número total de arcos entre estaciones (arcos totales del grafo).
	 * Retorna el arco con peso mínimo, los vértices que conecta y su valor de peso.
	 * Retorna el arco con el peso máximo, los vértices que conecta y su valor de peso.
	 * @throws ParseException 
	 */
	public String cargarDatos( ) throws CsvValidationException, ParseException{
		long inicio = System.currentTimeMillis();
		long fin = System.currentTimeMillis();
		try{
			//Create csv reader
			Reader reader =null; 
			reader = reader(reader, data);

			CSVParser parser = new CSVParserBuilder()
					.withSeparator(',')
					.build();

			CSVReader csvReader = new CSVReaderBuilder(reader)
					.withSkipLines(1)
					.withCSVParser(parser)
					.build();

			//read one record at a time.
			String[] line = csvReader.readNext();
			ArregloDinamicoGenerico<String> act;
			if(data.equals( "DATA_SMALL" )) {
				while( line != null){


					//Parte A
					String trip_id=line[0];
					String taxi_id = line[1];
					String company = line[14];
					tax taxis = new tax(trip_id);
					taxis.setTaxiId(line[1]);

					if(company.isEmpty()) company = "Independent Owner";
					taxis.setCompany(company);

					////////////////////////////
					listaDoble<tax> t = arbolR2.get(company);
					if (t == null) {
						t = new listaDoble<tax>();
					}						
					t.agregarInicio(taxis);
					arbolR2.put(company, t);
					///////////////////////
					listaDoble<tax> t2 = arbolR3.get(taxi_id);
					if (t2 == null) {
						t2 = new listaDoble<tax>();
					}						
					t2.agregarInicio(taxis);
					arbolR3.put(taxi_id, t2);
					///////////////////////
					
					//parte B
					
					tax2 taxis2 = new tax2(trip_id);
					taxis2.setTaxiId(taxi_id);
					String Trip_miles=line[5];
					String fecha =line[2];
					String Trip_cash=line[12];
					DateFormat fechaHora2 = new SimpleDateFormat("yyyy-MM-dd");
					if(!Trip_miles.isEmpty())
						taxis2.setTrip_miles(Double.parseDouble(Trip_miles));
					if(!fecha.isEmpty())
						taxis2.setTrip_start_timestamp(fechaHora2.parse(fecha));
					if(!Trip_cash.isEmpty())
						taxis2.setTrip_total(Double.parseDouble(Trip_cash));
					
                ////////////////////////////
				listaDoble<tax2> t3 = arbolR4.get(fechaHora2.parse(fecha));
				if (t3 == null) {
					t3 = new listaDoble<tax2>();
				}						
				t3.agregarInicio(taxis2);
				arbolR4.put(fechaHora2.parse(fecha), t3);
				/////////////////////// 

					//Parte C
					if(!line[4].isEmpty() && !line[2].isEmpty() && !line[3].isEmpty() && !line[19].equals(line[7])){
						String origenStr = line[19] +"-"+line[2].substring(11,16);
						String destinoStr = line[7] +"-"+line[3].substring(11,16);
						double duracion = Double.parseDouble(line[4]);

						diGraphParteC.insertVertex(origenStr, taxi_id);
						diGraphParteC.insertVertex(destinoStr, taxi_id);

						Edge<String, String, Integer> edge = diGraphParteC.getEdge(origenStr, destinoStr);

						if(edge!=null){
							edge.setWeight( ( ( edge.getWeight() * edge.getInfo())+ duracion) / (edge.getInfo()+1) );
							edge.setInfo( edge.getInfo()+1 );
						}
						else diGraphParteC.addEdge(origenStr, destinoStr, duracion, 1);
					}

					line = csvReader.readNext();
				}
			}

			else if(data.equals( "DATA_MEDIUM" )) {
				while( line != null){


					//Parte A
					String trip_id=line[0];
					String taxi_id = line[1];
					String company = line[13];
					tax taxis = new tax(trip_id);
					taxis.setTaxiId(line[1]);

					if(company.isEmpty()) company = "Independent Owner";
					taxis.setCompany(company);

					////////////////////////////
					listaDoble<tax> t = arbolR2.get(company);
					if (t == null) {
						t = new listaDoble<tax>();
					}						
					t.agregarInicio(taxis);
					arbolR2.put(company, t);
					///////////////////////
					listaDoble<tax> t2 = arbolR3.get(taxi_id);
					if (t2 == null) {
						t2 = new listaDoble<tax>();
					}						
					t2.agregarInicio(taxis);
					arbolR3.put(taxi_id, t2);
					///////////////////////`
					
					//Parte B
					
					tax2 taxis2 = new tax2(trip_id);
					taxis2.setTaxiId(taxi_id);
					String Trip_miles=line[5];
					String fecha =line[2];
					String Trip_cash=line[11];
					DateFormat fechaHora2 = new SimpleDateFormat("yyyy-MM-dd");
					if(!Trip_miles.isEmpty())
						taxis2.setTrip_miles(Double.parseDouble(Trip_miles));
					if(!fecha.isEmpty())
						taxis2.setTrip_start_timestamp(fechaHora2.parse(fecha));
					if(!Trip_cash.isEmpty())
						taxis2.setTrip_total(Double.parseDouble(Trip_cash));
					
                ////////////////////////////
				listaDoble<tax2> t3 = arbolR4.get(fechaHora2.parse(fecha));
				if (t3 == null) {
					t3 = new listaDoble<tax2>();
				}						
				t3.agregarInicio(taxis2);
				arbolR4.put(fechaHora2.parse(fecha), t3);
				/////////////////////// 


					//Parte C
					if(!line[4].isEmpty() && !line[2].isEmpty() && !line[3].isEmpty() && !line[6].equals(line[7])){
						String origenStr = line[6] +"-"+line[2].substring(11,16);
						String destinoStr = line[7] +"-"+line[3].substring(11,16);
						double duracion = Double.parseDouble(line[4]);

						diGraphParteC.insertVertex(origenStr, taxi_id);
						diGraphParteC.insertVertex(destinoStr, taxi_id);

						Edge<String, String, Integer> edge = diGraphParteC.getEdge(origenStr, destinoStr);

						if(edge!=null){
							edge.setWeight( ( ( edge.getWeight() * edge.getInfo())+ duracion) / (edge.getInfo()+1) );
							edge.setInfo( edge.getInfo()+1 );

						}
						else diGraphParteC.addEdge(origenStr, destinoStr, duracion, 1);
					}

					line = csvReader.readNext();
				}
			}

			else{
				while( line != null){


					//Parte A
					String trip_id=line[0];
					String taxi_id = line[1];
					String company = line[12];

					tax taxis = new tax(trip_id);
					taxis.setTaxiId(line[1]);

					if(company.isEmpty()) company = "Independent Owner";
					taxis.setCompany(company);

					////////////////////////////
					listaDoble<tax> t = arbolR2.get(company);
					if (t == null) {
						t = new listaDoble<tax>();
					}						
					t.agregarInicio(taxis);
					arbolR2.put(company, t);
					///////////////////////
					listaDoble<tax> t2 = arbolR3.get(taxi_id);
					if (t2 == null) {
						t2 = new listaDoble<tax>();
					}						
					t2.agregarInicio(taxis);
					arbolR3.put(taxi_id, t2);
					///////////////////////
					
					// Parte B
					
					tax2 taxis2 = new tax2(trip_id);
					taxis2.setTaxiId(taxi_id);
					String Trip_miles=line[5];
					String fecha =line[2];
					String Trip_cash=line[110];
					DateFormat fechaHora2 = new SimpleDateFormat("yyyy-MM-dd");
					if(!Trip_miles.isEmpty())
						taxis2.setTrip_miles(Double.parseDouble(Trip_miles));
					if(!fecha.isEmpty())
						taxis2.setTrip_start_timestamp(fechaHora2.parse(fecha));
					if(!Trip_cash.isEmpty())
						taxis2.setTrip_total(Double.parseDouble(Trip_cash));
					
                ////////////////////////////
				listaDoble<tax2> t3 = arbolR4.get(fechaHora2.parse(fecha));
				if (t3 == null) {
					t3 = new listaDoble<tax2>();
				}						
				t3.agregarInicio(taxis2);
				arbolR4.put(fechaHora2.parse(fecha), t3);
				/////////////////////// 

					//Parte C
					if(!line[4].isEmpty() && !line[2].isEmpty() && !line[3].isEmpty() && !line[15].equals(line[16])){
						String origenStr = line[15] +"-"+line[2].substring(11,16);
						String destinoStr = line[16] +"-"+line[3].substring(11,16);
						double duracion = Double.parseDouble(line[4]);

						diGraphParteC.insertVertex(origenStr, taxi_id);
						diGraphParteC.insertVertex(destinoStr, taxi_id);

						Edge<String, String, Integer> edge = diGraphParteC.getEdge(origenStr, destinoStr);

						if(edge!=null){
							edge.setWeight( ( ( edge.getWeight() * edge.getInfo())+ duracion) / (edge.getInfo()+1) );
							edge.setInfo( edge.getInfo()+1 );
						}
						else diGraphParteC.addEdge(origenStr, destinoStr, duracion, 1);
					}

					line = csvReader.readNext();
				}
			}

			csvReader.close();
			reader.close();	
			fin = System.currentTimeMillis();
		}
		catch(IOException e) {e.printStackTrace();}

		return Long.toString( fin-inicio);
	}

	// -----------------------------------------------------------------
	// PARTE A
	// -----------------------------------------------------------------

	/**
	 * Se encarga de mostrar un reporte del servicio de taxis TaxiTrips.
	 * @param Integer M. Entero que representa el Top M de compañías con al menos un taxi inscrito. 
	 * @param Integer M. Entero que representa el Top N de compañías que más servicios prestaron. 
	 * @return Retorna el número total de taxis en los servicios reportados.
	 * Retorna el número total de compañías que tienen al menos un taxi inscrito.
	 * Retorna el top M de compañías ordenada por la cantidad de taxis afiliados. 
	 * Retorna el top N de compañías que más servicios prestaron, el ranking se ordenará por el número servicios de mayor a menor.
	 */
	public String R2() {
		int contador =0;
		listaDoble<tax>lista=new listaDoble<tax>();
		for (String key3 : arbolR2.keys(arbolR2.min(), arbolR2.max())) {
			lista = arbolR2.get(key3);
			contador++;

		}
		return "número total de compañías que tienen al menos un taxi inscrito es de:"+contador;
	}

	public String R3() {
		int contador =0;
		listaDoble<tax>lista=new listaDoble<tax>();
		for (String key3 : arbolR3.keys(arbolR3.min(), arbolR3.max())) {
			lista = arbolR3.get(key3);
			contador++;

		}
		return "número total de taxis en los servicios reportados es de:"+contador;
	}

	public String R4(int n) {
		int contador =0;
		RedBlackBST<String, listaDoble<tax>> aux = new RedBlackBST<String, listaDoble<tax>>();
		listaDoble<tax>listaId = new listaDoble<tax>();
		ArrayList<String> res = new ArrayList<>();
		listaDoble<REQ4> listaFinal = new listaDoble<>();
		ArrayList<REQ4> listaFinal2 = new ArrayList<REQ4>();
		// Recorrido por compania
		for (String key3 : arbolR2.keys(arbolR2.min(), arbolR2.max())) {
			// Lista de los accidentes de la comapnia = key
			listaDoble<tax>lista = arbolR2.get(key3);
			// Recorrido de accidentes de la fecha actual (key)
			for(tax accidente: lista) {
				// Lista de accidentes de la severidad del accidente actual
				listaId = aux.get(accidente.getTaxiId());
				// Si la lista no es nula, agregamos el accidente
				if (listaId != null) {
					listaId.agregarInicio(accidente);
				} else { //si la lista es nula, inicializamos la lista y luego añadimos el accidente
					listaId = new listaDoble<tax>();
					listaId.agregarInicio(accidente);
				}
				// Añadimos al arbol aux la lista actualizada
				aux.put(accidente.getTaxiId(), listaId);
				// Contamos la cantidad total de accidentes en el rango de fechas
				res.add(accidente.getTaxiId());
			}
			//System.out.println(key3);
			Set<String> hashSet = new HashSet<String>(res);
			res.clear();
			res.addAll(hashSet);
			//System.out.println(res.size());
			REQ4 req = new REQ4(key3, res.size());
			listaFinal.agregarInicio(req);
			listaFinal2.add(req);
			res.clear();
			hashSet.clear(); 

		}
		Collections.sort(listaFinal2);
		for (int i = 0; i < n; i++) {
			System.out.println(listaFinal2.get(i).getNombreCompania()+":"+listaFinal2.get(i).getNumTaxis());
		}
		/*
		for (REQ4 i:listaFinal2) {
			System.out.println(i.getNombreCompania()+":"+i.getNumTaxis());
			}
		 */
		return "";
	}

	public String R5(int n) {

		RedBlackBST<String, listaDoble<tax>> aux = new RedBlackBST<String, listaDoble<tax>>();
		listaDoble<tax>listaId = new listaDoble<tax>();
		ArrayList<String> res = new ArrayList<>();
		ArrayList<REQ5> listaFinal2 = new ArrayList<REQ5>();
		// Recorrido por compania
		for (String key3 : arbolR2.keys(arbolR2.min(), arbolR2.max())) {
			// Lista de los accidentes de la comapnia = key
			listaDoble<tax>lista = arbolR2.get(key3);
			// Recorrido de accidentes de la fecha actual (key)
			for(tax accidente: lista) {
				// Lista de accidentes de la severidad del accidente actual
				listaId = aux.get(accidente.getTaxiId());
				// Si la lista no es nula, agregamos el accidente
				if (listaId != null) {
					listaId.agregarInicio(accidente);
				} else { //si la lista es nula, inicializamos la lista y luego añadimos el accidente
					listaId = new listaDoble<tax>();
					listaId.agregarInicio(accidente);
				}
				// Añadimos al arbol aux la lista actualizada
				aux.put(accidente.getTaxiId(), listaId);
				// Contamos la cantidad total de accidentes en el rango de fechas
				res.add(accidente.getTripId());
			}
			//System.out.println(key3);
			//System.out.println(res.size());
			REQ5 req = new REQ5(key3, res.size());
			listaFinal2.add(req);
			res.clear();

		}
		Collections.sort(listaFinal2);
		for (int i = 0; i < n; i++) {
			System.out.println(listaFinal2.get(i).getNombreCompania()+":"+listaFinal2.get(i).getNumServicios());
		}
		/*
		for (REQ4 i:listaFinal2) {
			System.out.println(i.getNombreCompania()+":"+i.getNumTaxis());
			}
		 */
		return "";
	}

	// -----------------------------------------------------------------
	// PARTE B
	// -----------------------------------------------------------------

	/**
	 * Muestra el número, dado por parámetro, de taxis con más puntos en TaxiTrips en una fecha determinada.
	 * @param int N. Número de taxis a identificar.
	 * @param String fecha. Fecha de los N taxis con más puntos.
	 * @return Retorna la lista de taxis con más puntos en una fecha determinada.
	 */
	public String darTaxiFecha(int N, String fecha){
		return "Incompleto";
	}

	/**
	 * Muestra el número, dado por parámetro, de taxis con más puntos en TaxiTrips en un rango de fechas.
	 * @param int N. Número de taxis a identificar.
	 * @param String fechaInicial. Fecha inicial de los M taxis con más puntos.
	 * @param String fechaFinal. Fecha final de los M taxis con más puntos.
	 * @return Retorna la lista de taxis con más puntos en un rango de fechas.
	 */
	public String R6(Date a, int n) {
		int contador=0;
		int actual=0;
		String s=null;
		Date fecha=null;
		int actualFecha=0;
		RedBlackBST<String, listaDoble<tax2>> aux = new RedBlackBST<String, listaDoble<tax2>>();
		ArrayList<R6> listaFinal2 = new ArrayList<R6>();
		// Recorrido por fechas
		for (Date key : arbolR4.keys(a,a)) {
			// Lista de los accidentes de la fecha = key
			listaDoble<tax2>lista = arbolR4.get(key);
			// Recorrido de accidentes de la fecha actual (key)
			for(tax2 accidente: lista) {
				// Lista de accidentes de la severidad del accidente actual
				listaDoble<tax2> listaEstado = aux.get(accidente.getTaxiId());
				// Si la lista no es nula, agregamos el accidente
				if (listaEstado != null) {
					listaEstado.agregarInicio(accidente);
				} else { //si la lista es nula, inicializamos la lista y luego añadimos el accidente
					listaEstado = new listaDoble<tax2>();
					listaEstado.agregarInicio(accidente);
				}
				// Añadimos al arbol aux la lista actualizada
				aux.put(accidente.getTaxiId(), listaEstado);
				// Contamos la cantidad total de accidentes en el rango de fechas
				contador++;
			
			}

			
		}
		// Recorrido por severidad
		double contadorMillas=0;
		double contadorDinero=0;
		double contadorServicios=0;
		double respuesta=0;
		for(String taxi: aux.keys(aux.min(), aux.max())) {
			// Lista de todos los accidentes con la severidad actual 
			listaDoble<tax2> lista = aux.get(taxi);
			// Encontrar la severidad con mayor número de accidentes
			for (tax2 tax2 : lista) {
				if (tax2.getTrip_miles()!=0 && tax2.getTrip_total()!=0) {
					contadorMillas+=tax2.getTrip_miles();
					contadorDinero+=tax2.getTrip_total();
					contadorServicios++;
				}else {
					contadorMillas+=0;
					contadorDinero+=0;
					contadorServicios+=0;
				}

			}
			if (contadorMillas==0 && contadorDinero==0 ) {
				respuesta=0;
			}else {
			respuesta=(contadorMillas/contadorDinero)*contadorServicios;
			}
			R6 req = new R6(taxi, respuesta);
			listaFinal2.add(req);
			 contadorMillas=0;
			 contadorDinero=0;
			 contadorServicios=0;
			 respuesta=0;
			
			
		}
		Collections.sort(listaFinal2);
		for (int i = 0; i < n; i++) {
			System.out.println(listaFinal2.get(i).getIdTaxi()+":"+listaFinal2.get(i).getPuntos());
		}
		return "";
	}
	
	public String R(Date a, Date b, int n) {
		int contador=0;
		int actual=0;
		String s=null;
		Date fecha=null;
		int actualFecha=0;
		RedBlackBST<String, listaDoble<tax2>> aux = new RedBlackBST<String, listaDoble<tax2>>();
		ArrayList<R6> listaFinal2 = new ArrayList<R6>();
		// Recorrido por fechas
		for (Date key : arbolR4.keys(a,b)) {
			// Lista de los accidentes de la fecha = key
			listaDoble<tax2>lista = arbolR4.get(key);
			// Recorrido de accidentes de la fecha actual (key)
			for(tax2 accidente: lista) {
				// Lista de accidentes de la severidad del accidente actual
				listaDoble<tax2> listaEstado = aux.get(accidente.getTaxiId());
				// Si la lista no es nula, agregamos el accidente
				if (listaEstado != null) {
					listaEstado.agregarInicio(accidente);
				} else { //si la lista es nula, inicializamos la lista y luego añadimos el accidente
					listaEstado = new listaDoble<tax2>();
					listaEstado.agregarInicio(accidente);
				}
				// Añadimos al arbol aux la lista actualizada
				aux.put(accidente.getTaxiId(), listaEstado);
				// Contamos la cantidad total de accidentes en el rango de fechas
				contador++;
			
			}

			
		}
		// Recorrido por severidad
		double contadorMillas=0;
		double contadorDinero=0;
		double contadorServicios=0;
		double respuesta=0;
		for(String taxi: aux.keys(aux.min(), aux.max())) {
			// Lista de todos los accidentes con la severidad actual 
			listaDoble<tax2> lista = aux.get(taxi);
			// Encontrar la severidad con mayor número de accidentes
			for (tax2 tax2 : lista) {
				contadorMillas+=tax2.getTrip_miles();
				contadorDinero+=tax2.getTrip_total();
				contadorServicios++;
				//System.out.println(taxi);
				//System.out.println(tax2.getTrip_total());
				//System.out.println(tax2.getTripId());
			}
			respuesta=(contadorMillas/contadorDinero)*contadorServicios;
			R6 req = new R6(taxi, respuesta);
			listaFinal2.add(req);
			 contadorMillas=0;
			 contadorDinero=0;
			 contadorServicios=0;
			 respuesta=0;
			
			
		}
		Collections.sort(listaFinal2);
		for (int i = 0; i < n; i++) {
			System.out.println(listaFinal2.get(i).getIdTaxi()+":"+listaFinal2.get(i).getPuntos());
		}
		return "";
	}

	// -----------------------------------------------------------------
	// PARTE C
	// -----------------------------------------------------------------

	/**
	 * Se encarga de mostrar un reporte de la consulta del mejor horario hecha por el usuario. 
	 * @param int origen. Area de origen.
	 * @param int destino. Area de destino. 
	 * @param String rangoTiempo. Rango de tiempo en el día en el que se quiere inicial el viaje. 
	 * @return Retorna el mejor horario en hora y minutos del inicio del viaje en el rango de tiempo dado para tener la menor duración posible. 
	 * Retorna la ruta del viaje.
	 * Retorna el tiempo estimado del viaje en segundos. 
	 */
	public String consultarMejorHorario(int origen, int destino, String rangoTiempo){
		try{

			long inicio = System.currentTimeMillis();
			ArregloDinamicoGenerico<Edge <String, String, Integer> > minPath = new ArregloDinamicoGenerico< Edge<String, String, Integer> >(30);
			double maxWeight = Double.POSITIVE_INFINITY;

			String v_inicio = Integer.toString( origen )+".0";
			String v_final= Integer.toString( destino )+".0";
			ArregloDinamicoGenerico< String > vertexSource = darTiempoAux(v_inicio, rangoTiempo.substring(0,5), rangoTiempo.substring(6,11));
			ArregloDinamicoGenerico< Vertex<String, String, Integer> > vertexDest = darCommunityDestinoAux(v_final);

			for( int i= 0; i<vertexSource.size(); i++){
				Vertex<String, String, Integer> vertex = diGraphParteC.getVertex( vertexSource.getElemento(i+1) );
				if(vertex!=null){
					DijkstraSP<String, String, Integer> dijkstraSP = new DijkstraSP<String, String, Integer>(diGraphParteC, vertex );
					for( int j=0; j< vertexDest.size(); j++){
						ArregloDinamicoGenerico<Edge <String, String, Integer> > rutas = dijkstraSP.minPath(vertex, vertexDest.getElemento(j+1));
						double rutaWeight = dijkstraSP.darWeightTotal();
						if( rutaWeight < maxWeight && rutas.size()!=0){ minPath = rutas; maxWeight =rutaWeight; }
					}
				}
			}

			if(!Double.isInfinite( maxWeight )){
				String lista = "\n"; 
				for(int i = 0; i<minPath.size(); i++){
					String area1 = minPath.getElemento(i+1).getSource().getId().split("-")[0];
					String area2 = minPath.getElemento(i+1).getDest().getId().split("-")[0];
					if(area1.isEmpty()) area1="F";
					else area1 = area1.split("\\.")[0];
					if(area2.isEmpty()) area2="F";
					else area2 = area2.split("\\.")[0];
					lista+= "\n"+Integer.toString(i+1)+". "+ area1 + " ----> " + area2;
				}
				long fin=System.currentTimeMillis();
				return SEPARADOR +
						"\n- El mejor horario de inicio es: " + minPath.firstElement().getSource().getId().split("-")[1] +
						"\n- Lista de ComminityArea en la ruta mínima:" + lista + 
						"\n\n- Tiempo estimado: " + maxWeight + " segundo." + 
						"\n- Tiempo requerimiento: "+ (fin-inicio)+" segundos." + 
						"\n" + SEPARADOR;
			}
			else{
				return "No se encontro una ruta para el viaje";
			}

		}
		catch(StackOverflowError e){
			return "El rango dado en parámetro es muy grande. Intente disminuirlo.";
		}
	}

	/**
	 * Se encarga de calcular el tiempo en intervalos de 15 minutos en el rango dado. 
	 * @param String v_inicio.Area de inicio.
	 * @param String tiempo_inicio. Tiempo de inicio.
	 * @param String tiempo_final. Tiempo final.
	 * @return Retorna un arreglo con los tiempos en con intervalos de 15 minutos en el rango dado.
	 */
	private ArregloDinamicoGenerico< String > darTiempoAux(String v, String tiempo_inicio, String tiempo_final ){
		ArregloDinamicoGenerico< String > tiempos = new ArregloDinamicoGenerico<>(10);
		tiempos.addLast( v+"-"+tiempo_inicio );
		for(int i = 15; !tiempo_inicio.equals(tiempo_final);){
			Integer hora = Integer.parseInt(tiempo_inicio.substring(0,2));
			Integer mins = Integer.parseInt(tiempo_inicio.substring(3,5));
			String horaStr = tiempo_inicio.substring(0,2);
			String minsStr = tiempo_inicio.substring(3,5);

			if( mins==45 && hora ==23) {tiempo_inicio = "00:00"; tiempos.addLast( v+"-"+tiempo_inicio );}
			else{
				if(mins==45) {hora++; mins=00;}
				else mins+=i;
				if( !(hora.toString().length()==2) ) horaStr = "0"+Integer.toString(hora); 
				else horaStr = Integer.toString( hora );
				if( !(mins.toString().length()==2) ) minsStr = "0"+Integer.toString(mins);
				else minsStr = Integer.toString( mins );
				tiempo_inicio = horaStr+":"+minsStr;
				tiempos.addLast( v+"-"+tiempo_inicio );
			}
		}
		return tiempos;
	}

	/**
	 * Calcula todas las comunnityArea de llegada.
	 * @param String v. ComunnityArea de destino.
	 * @return Retorna una lista de las comunnityArea de llegada.
	 */
	private ArregloDinamicoGenerico<Vertex<String, String, Integer>> darCommunityDestinoAux(String v){
		ArregloDinamicoGenerico<Vertex<String, String, Integer>> aux = diGraphParteC.vertex();
		ArregloDinamicoGenerico<Vertex<String, String, Integer>> res = new ArregloDinamicoGenerico<Vertex<String, String, Integer>>(30);

		for(int i =0; i<aux.size(); i++){
			if( aux.getElemento(i+1).getId().split("-")[0].equals(v) ){
				res.addLast(aux.getElemento(i+1)); 	
			}
		}
		return res;
	}
}




















