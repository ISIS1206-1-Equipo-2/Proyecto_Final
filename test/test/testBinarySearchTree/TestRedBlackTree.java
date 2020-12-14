package test.testBinarySearchTree;

import model.structures.listaGenerica.*;
import model.structures.binarySearchTree.*;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class TestRedBlackTree{

	/**
	 * Tabla de separate Chaining
	 */
	private RedBlackTree <String, Integer>RedBlackTree;

	@Before
	public void setUp1() {
		RedBlackTree = new RedBlackTree();
	}

	public void setUp2() {
		RedBlackTree.put("a", 1);
		RedBlackTree.put("b", 2);
		RedBlackTree.put("c", 3);
		RedBlackTree.put("d", 4);
		RedBlackTree.put("e", 5);
		RedBlackTree.put("f", 6);
		RedBlackTree.put("g", 7);
		RedBlackTree.put("h", 8);
		RedBlackTree.put("i", 9);
		RedBlackTree.put("j", 10);
		RedBlackTree.put("k", 11);
		RedBlackTree.put("l", 12);
		RedBlackTree.put("m", 13);
		RedBlackTree.put("n", 14);
		RedBlackTree.put("o", 15);
		RedBlackTree.put("p", 16);
		RedBlackTree.put("q", 17);
		RedBlackTree.put("r", 18);
		RedBlackTree.put("s", 19);
		RedBlackTree.put("t", 20);
		RedBlackTree.put("u", 21);
		RedBlackTree.put("v", 22);
		RedBlackTree.put("w", 23);
		RedBlackTree.put("x", 24);
		RedBlackTree.put("y", 25);
		RedBlackTree.put("z", 26);
	}

	public void setUp3() {
		RedBlackTree.put("a", 1);
		RedBlackTree.put("a", 2);
		RedBlackTree.put("a", 3);
		RedBlackTree.put("d", 4);
		RedBlackTree.put("e", 5);
		RedBlackTree.put("f", 6);
		RedBlackTree.put("g", 7);
		RedBlackTree.put("g", 8);
		RedBlackTree.put("i", 9);
		RedBlackTree.put("j", 10);
		RedBlackTree.put("k", 11);
		RedBlackTree.put("l", 12);
		RedBlackTree.put("l", 13);
		RedBlackTree.put("l", 14);
		RedBlackTree.put("o", 15);
		RedBlackTree.put("p", 16);
		RedBlackTree.put("p", 17);
		RedBlackTree.put("r", 18);
		RedBlackTree.put("s", 19);
		RedBlackTree.put("t", 20);
		RedBlackTree.put("u", 21);
		RedBlackTree.put("v", 22);
		RedBlackTree.put("v", 23);
		RedBlackTree.put("v", 24);
		RedBlackTree.put("y", 25);
		RedBlackTree.put("z", 26);
	}
	
	public void setUp4() {
		RedBlackTree.put("b", 1);
		RedBlackTree.put("a", 2);
		RedBlackTree.put("c", 3);
	}
	
	public void setUp5()
	{
		RedBlackTree.put("s", 5);
		RedBlackTree.put("e", 25);
		RedBlackTree.put("a", 50);
	}
	
	public void setUp6()
	{
		RedBlackTree.put("s", 5);
		RedBlackTree.put("x", 25);
	}
	

	/**
	 * Se encarga de verificar que el tamaño del set up sea el correcto (prueba 1 - Elementos sin repetir).
	 * Retorna el número de parejas [llave, valor] del árbol.
	 */
	@Test
	public void size1(){
		assertSame("El valor no corresponde al tamaño del árbol",0,  RedBlackTree.size());
		setUp2();
		assertSame("El valor no corresponde al tamaño del árbol", 26, RedBlackTree.size());
	}

	/**
	 * Se encarga de verificar que el tamaño del set up sea el correcto (prueba 2 - Elementos repetidos). 
	 * Retorna el número de parejas [llave, valor] del árbol.
	 */
	@Test
	public void size2(){
		setUp3();
		assertSame("El valor no corresponde al tamaño del árbol", 18, RedBlackTree.size());
	}

	/**
	 * Se encarga de verificar si el árbol se encuentra vacío o no.
	 * Retorna un booleano con base a si el arbol se encuentra vacío.
	 */
	@Test
	public void isEmpty(){
		assertTrue("El árbol se encuentra vacío", RedBlackTree.isEmpty());
		setUp2();
		assertFalse("El árbol se encuentra vacío", RedBlackTree.isEmpty());
	}

	/**
	 * Se encarga de verificar si la el valor asociado a una llave dada es el mismo en el setUp (prueba 1 - Elementos sin repetir).
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se encuentra se retorna null.
	 */
	@Test
	public void get1(){
		setUp2();
		assertSame("El valor registrado es difente al valor esperado", 1, RedBlackTree.get("a"));
		assertSame("El valor registrado es difente al valor esperado", 2, RedBlackTree.get("b"));
		assertSame("El valor registrado es difente al valor esperado", 3, RedBlackTree.get("c"));
		assertSame("El valor registrado es difente al valor esperado", 4, RedBlackTree.get("d"));
		assertSame("El valor registrado es difente al valor esperado", 5, RedBlackTree.get("e"));
		assertSame("El valor registrado es difente al valor esperado", 6, RedBlackTree.get("f"));
		assertSame("El valor registrado es difente al valor esperado", 7, RedBlackTree.get("g"));
		assertSame("El valor registrado es difente al valor esperado", 8, RedBlackTree.get("h"));
		assertSame("El valor registrado es difente al valor esperado", 9, RedBlackTree.get("i"));
		assertSame("El valor registrado es difente al valor esperado", 10, RedBlackTree.get("j"));
		assertSame("El valor registrado es difente al valor esperado", 11, RedBlackTree.get("k"));
		assertSame("El valor registrado es difente al valor esperado", 12, RedBlackTree.get("l"));
		assertSame("El valor registrado es difente al valor esperado", 13, RedBlackTree.get("m"));
		assertSame("El valor registrado es difente al valor esperado", 14, RedBlackTree.get("n"));
		assertSame("El valor registrado es difente al valor esperado", 15, RedBlackTree.get("o"));
		assertSame("El valor registrado es difente al valor esperado", 16, RedBlackTree.get("p"));
		assertSame("El valor registrado es difente al valor esperado", 17, RedBlackTree.get("q"));
		assertSame("El valor registrado es difente al valor esperado", 18, RedBlackTree.get("r"));
		assertSame("El valor registrado es difente al valor esperado", 19, RedBlackTree.get("s"));
		assertSame("El valor registrado es difente al valor esperado", 20, RedBlackTree.get("t"));
		assertSame("El valor registrado es difente al valor esperado", 21, RedBlackTree.get("u"));
		assertSame("El valor registrado es difente al valor esperado", 22, RedBlackTree.get("v"));
		assertSame("El valor registrado es difente al valor esperado", 23, RedBlackTree.get("w"));
		assertSame("El valor registrado es difente al valor esperado", 24, RedBlackTree.get("x"));
		assertSame("El valor registrado es difente al valor esperado", 25, RedBlackTree.get("y"));
		assertSame("El valor registrado es difente al valor esperado", 26, RedBlackTree.get("z"));
	}

	/**
	 * Se encarga de verificar si la el valor asociado a una llave dada es el mismo en el setUp (prueba 2 - elementos repetidos).
	 * Retorna el valor V asociado a la llave key dada. Si la llave no se encuentra se retorna null.
	 */
	@Test
	public void get2(){
		setUp3();
		assertSame("El valor registrado es difente al valor esperado", 3, RedBlackTree.get("a"));
		assertSame("El valor registrado es difente al valor esperado", 4, RedBlackTree.get("d"));
		assertSame("El valor registrado es difente al valor esperado", 5, RedBlackTree.get("e"));
		assertSame("El valor registrado es difente al valor esperado", 6, RedBlackTree.get("f"));
		assertSame("El valor registrado es difente al valor esperado", 8, RedBlackTree.get("g"));
		assertSame("El valor registrado es difente al valor esperado", 9, RedBlackTree.get("i"));
		assertSame("El valor registrado es difente al valor esperado", 10, RedBlackTree.get("j"));
		assertSame("El valor registrado es difente al valor esperado", 11, RedBlackTree.get("k"));
		assertSame("El valor registrado es difente al valor esperado", 14, RedBlackTree.get("l"));
		assertSame("El valor registrado es difente al valor esperado", 15, RedBlackTree.get("o"));
		assertSame("El valor registrado es difente al valor esperado", 17, RedBlackTree.get("p"));
		assertSame("El valor registrado es difente al valor esperado", 18, RedBlackTree.get("r"));
		assertSame("El valor registrado es difente al valor esperado", 19, RedBlackTree.get("s"));
		assertSame("El valor registrado es difente al valor esperado", 20, RedBlackTree.get("t"));
		assertSame("El valor registrado es difente al valor esperado", 21, RedBlackTree.get("u"));
		assertSame("El valor registrado es difente al valor esperado", 24, RedBlackTree.get("v"));
		assertSame("El valor registrado es difente al valor esperado", 25, RedBlackTree.get("y"));
		assertSame("El valor registrado es difente al valor esperado", 26, RedBlackTree.get("z"));
	}

	/**
	 * Se encarga de verificar si la altura del arbol corresponde a la altura del setUp (prueba 1 - elementos sin repetir).
	 * Retorna la altura del árbol definida como la áltura de la rama más alta; es decir, aquella que tenga mayor número de enlaces desde la raíz a una hoja.
	 */
	@Test
	public void height1(){
		setUp2();
		assertSame("La altura del árbol no es la correcta", 4, RedBlackTree.height());
	}

	/**
	 * Se encarga de verificar si la altura del arbol corresponde a la altura del setUp (prueba 2 - elementos repetidos).
	 * Retorna la altura del árbol definida como la áltura de la rama más alta; es decir, aquella que tenga mayor número de enlaces desde la raíz a una hoja.
	 */
	@Test
	public void height2(){
		setUp4();
		assertSame("La altura del árbol no es la correcta", 1, RedBlackTree.height());
	}

	/**
	 * Se encarga de verificar si la llave más pequeña del arbol corresponde a la llave más pequeña del setUp.
	 * Retorna la llave más pequeña del árbol. Valor null si árbol vacío.
	 */
	@Test
	public void min(){
		assertNull("El valor registrado debería ser null", RedBlackTree.min());
		setUp2();
		assertSame("El valor registrado no corresponde al esperado", "a", RedBlackTree.min().key);
	}

	/**
	 * Se encarga de verificar si la llave más grande del arbol corresponde a la llave más grande del setUp.
	 * Retorna la llave más grande del árbol. Valor null si árbol vacío
	 */
	@Test
	public void max(){
		assertNull("El valor registrado debería ser null", RedBlackTree.max());
		setUp2();
		assertSame("El valor registrado no corresponde al esperado", "z", RedBlackTree.max());
	}

	/**
	 * Se encarga de verificar que las llaves del arbol correspondan a las llaves del setUp.
	 * Retorna las llaves del árbol. Para su implementación en BST o RBT deben retornarse usando un recorrido en Inorden.
	 */
	@Test
	public void keySet(){
		setUp2();
		ArregloDinamicoGenerico<String> p = (ArregloDinamicoGenerico) RedBlackTree.keySet();
		assertSame("El tamaño del keySet no es el correcto", 26, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("a"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("b"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("c"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("d"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("e"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("f"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("g"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("h"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("i"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("j"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("k"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("l"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("m"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("n"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("o"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("p"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("q"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("r"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("s"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("t"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("u"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("v"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("w"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("x"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("y"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("z"));	
	}

	/**
	 * Se encarga de verificar si las llaves se encuentran entre dos parámetros de  fechas con base al setUp.
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado. Las llaves en el rango deben retornarse en orden ascendente. 
	 */
	@Test
	public void keyInRange1(){
		setUp2();
		ArregloDinamicoGenerico<String> p = (ArregloDinamicoGenerico) RedBlackTree.keysInRange("a", "c");
		assertSame("El tamaño de la lista no coincide", 3, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("a"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("b"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("c"));
	}
	
	/**
	 * Se encarga de verificar si las llaves se encuentran entre dos parámetros de  fechas con base al setUp.
	 * Retorna todas las llaves K en el árbol que se encuentran en el rango de llaves dado. Las llaves en el rango deben retornarse en orden ascendente. 
	 */
	@Test
	public void keyInRange2(){
		setUp2();
		ArregloDinamicoGenerico<String> p = (ArregloDinamicoGenerico) RedBlackTree.keysInRange("a", "z");
		assertSame("El tamaño de la lista no coincide", 26, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("a"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("b"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("c"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("d"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("e"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("f"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("g"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("h"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("i"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("j"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("k"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("l"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("m"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("n"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("o"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("p"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("q"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("r"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("s"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("t"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("u"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("v"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("x"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("y"));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent("z"));
		
	}

	/**
	 * Se encarga de verificar si los valores se encuentran entre dos parámetros de  fechas con base al setUp.
	 * Retorna todos los valores V en el árbol que estén asociados al rangode llaves dado.
	 */
	@Test
	public void valuesInRange(){
		setUp2();
		ArregloDinamicoGenerico<Integer> p = (ArregloDinamicoGenerico) RedBlackTree.valuesInRange("a", "c");
		assertSame("El tamaño del keySet no es el correcto", 3, p.size());
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent(1));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent(2));
		assertNotSame("El elemento encontrado debería estar en el arreglo", -1, p.isPresent(3));
	}
	
	/**
	 * Se encarga de verificar si la rotación a la derecha del árbol se completo de forma correcta.
	 * Retorna El subarbol rotado a la derecha.
	 */
	@Test
	public void rotateRight() {
		setUp5(); 
		assertSame("El valor del hijo izquierdo de la raiz debería ser 50", 50,   RedBlackTree.getRoot().left.val);
		assertSame("El valor del hijo derecho de la raiz debería ser 5", 5,   RedBlackTree.getRoot().right.val);
	}
	
	/**
	 * Se encarga de verificar si la rotación a la izquierda del árbol se completo de forma correcta.
	 * Retorna El subarbol rotado a la derecha.
	 */
	@Test
	public void rotateLeft()
	{
		setUp6();
		assertSame("El valor del hijo izquierdo de la raiz debería ser 5", 5,   RedBlackTree.getRoot().left.val);
	}
	
}