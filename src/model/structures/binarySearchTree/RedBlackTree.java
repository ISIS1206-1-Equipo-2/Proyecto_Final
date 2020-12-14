package model.structures.binarySearchTree;

public class RedBlackTree<K extends Comparable<K>, V> extends BinarySearchTree<K,V> implements TablasSimbolosOrdenada<K,V> 
{
	// -----------------------------------------------------------------
	// Atributos
	// -----------------------------------------------------------------
//
//	/**
//	 * Raiz del arbol binario rojo-negro
//	 */
//	public NodoBinarioRBT rootRBT;

	// -----------------------------------------------------------------
	// Constantes
	// -----------------------------------------------------------------


	/**
	 * Constante booleana que guarda el color del enlace rojo
	 */
	private static final boolean RED = true;

	/**
	 * Constante booleana que guarda el color del enlace negro
	 */
	private static final boolean BLACK = false;



	// -----------------------------------------------------------------
	// Cosntructor
	// -----------------------------------------------------------------

	/**
	 * Construye un nuevo arbol binario
	 */
	public RedBlackTree()
	{
		//xd
	}

	/**
	 * retorna True si el nodo en cuestión es de color rojo, False de lo contrario
	 */
	public boolean esRojo(NodoBinario x)
	{
		if( x == null) return false;
		return x.color == RED;
	}

	/**
	 * Rota a la izquierda el subarbol 
	 */
	public NodoBinario rotateLeft(NodoBinario h)
	{
		NodoBinario x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	/**
	 * Rota a la derecha el subarbol
	 */
	public NodoBinario rotateRight(NodoBinario h)
	{
		NodoBinario x = (NodoBinario)h.left;
		h.left = x.right;
		x.right = h;
		x.color = h.color;
		h.color = RED;
		x.N = h.N;
		h.N = 1 + size(h.left) + size(h.right);
		return x;
	}

	/**
	 * Cambia los colores de los hijos del nodo a negro
	 */
	public void flipColors(NodoBinario h)
	{
		h.color = RED;
		NodoBinario tempLeft = (NodoBinario)h.left;
		tempLeft.color = BLACK;

		h.left = tempLeft;

		NodoBinario tempRight = (NodoBinario)h.right;
		tempRight.color = BLACK;
		h.right = tempRight;
	}
	
	/**
	 * Implementación del put para el arbol rojo-negro
	 */
	public void put(K key, V val)
	{
		root = put(root, key, val);
		root.color = BLACK;
	}
	
	public NodoBinario put(NodoBinario h, K key, V val)
	{
		if(h == null) return new NodoBinario(key, val, 1 , RED);
		
		int cmp = key.compareTo(h.key);
		if(cmp < 0 ) h.left = put(h.left, key, val);
		else if(cmp >0 ) h.right = put(h.right, key, val); 
		else h.val = val;
		
		if(esRojo(h.right) && !esRojo(h.left)) h = rotateLeft(h);
		if(esRojo(h.left) && esRojo(h.left.left)) h = rotateRight(h);
		if(esRojo(h.left) && esRojo(h.right)) flipColors(h);
		
		h.N = 1 + size(h.left) + size(h.right);
		return h;
	}

}