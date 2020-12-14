/**
 * 2020-11-17
 * Clase Digraph.
 * @author Julián Andrés Méndez
 * @author Juan Miguel Vega Caro
 */
package model.structures.algorithms;

import model.structures.binarySearchTree.BinarySearchTree;
import model.structures.binarySearchTree.BinarySearchTree.NodoBinario;
import model.structures.directedGraph.DiGraph;
import model.structures.directedGraph.Edge;
import model.structures.directedGraph.Vertex;
import model.structures.listaGenerica.ArregloDinamicoGenerico;

/**
 * Representa el algoritmo de DijikstraSP cuyo objetivo es la busquedas de caminos más cortos.
 */
public class DijkstraSP<K extends Comparable<K>,V, A> {

	// -----------------------------------------------------------------
	// Atributos.
	// -----------------------------------------------------------------

	/**
	 * Representa la lista de edges directos en referencia a un vertice.
	 */
	private ArregloDinamicoGenerico<Edge<K,V,A>> edgeTo;

	/**
	 * Representa la estructura de datos arbol rojo negro para el index Min PQ
	 */
	private BinarySearchTree<K, Double> pq;
	
	/**
	 * Representa el peso total de la ruta corta.
	 */
	private double weightTotal;

	/**
	 * Representa el vertice v en el minPath
	 */
	private Vertex<K,V,A> source;

	// -----------------------------------------------------------------
	// Constructor.
	// -----------------------------------------------------------------

	/**
	 * Se encarga de calcular la ruta más corta dado un grafo y el vertice. 
	 * @param DiGraph<K, V, A>G. Grafo dirigido y conexo.
	 * @param Vertex<K,V,A> v. Vertice del grafo a sacar la ruta.
	 */
	public DijkstraSP (DiGraph<K, V, A> G, Vertex<K,V,A> v){
		source = v; 
		weightTotal = 0; 
		edgeTo = new ArregloDinamicoGenerico<Edge<K,V,A>>(G.numVertices());
		pq = new BinarySearchTree<>();
		for(int i = 0; i< G.numVertices(); i++){ 
			Vertex<K, V,A > temp = G.vertex().getElemento(i+1);
			if(v.equals(temp)) temp.setPath(0.0);
			else temp.setPath( Double.POSITIVE_INFINITY);
		}
		pq.put(v.getId(), 0.0);
		while (!pq.isEmpty()){
			NodoBinario n = pq.min();
			pq.delete(pq.min().key);
			relax(G, n);		
		}
	}

	// -----------------------------------------------------------------
	// Métodos.
	// -----------------------------------------------------------------

	/**
	 * Se encarga de actualizar la ruta más corta dado un grafo y un vertice,
	 * @param DiGraph<K, V, A>G. Grafo dirigido y conexo.
	 * @param Vertex<K,V,A> v. Vertice del grafo a sacar la ruta.
	 */
	@SuppressWarnings("unchecked")
	public void relax(DiGraph<K, V, A> G, NodoBinario v){
		Vertex<K,V,A> id = G.getVertex( (K) v.key );
		for(int i = 0; i< id.edgesOut().size(); i++){
			Edge<K,V,A> edge =  id.edgesOut().getElemento(i+1);
			Vertex<K,V,A> destLocation = edge.getDest();

			if( destLocation.getPath() > (Double) v.val +edge.getWeight()){
				destLocation.setPath((Double) v.val + edge.getWeight());
				edge.getSource().setEdgeTo(edge);
				destLocation.setEdgeFrontPath(edge);
				BinarySearchTree<K, Double>.NodoBinario temp = pq.getNodo(destLocation.getId());
				if( temp!=null){
					temp.setValue(destLocation.getPath() );
				}
				else pq.put(destLocation.getId(), destLocation.getPath());
			}
		}
	}

	/**
	 * Calcula la ruta más rapida dado un vestice origen y un destino.
	 * @param Vertex<K,V,A> v. Vertice de partida
	 * @param Vertex<K,V,A> w. Vertice destino. 
	 * @return Retorna la lista de los arcos en la ruta más corta.
	 * @throws Exception 
	 */
	public ArregloDinamicoGenerico<Edge<K,V,A>> minPath(Vertex<K,V,A> v, Vertex<K,V,A> w) throws StackOverflowError {
		weightTotal = 0; 
		edgeTo = new ArregloDinamicoGenerico<Edge<K,V,A>>(30);
		minPathAux(w);
		return edgeTo;
	}

	/**
	 * Actualiza la lista edgeTo con la ruta más corta de un vertice.
	 * @param Vertex<K,V,A> w. Vertice destino en función de un vertice origen.
	 */
	private void minPathAux(Vertex<K, V, A> w){
		Edge<K,V,A> edge = w.getEdgeFrontPath();
		if( edge != null){
			Vertex<K,V,A> v = edge.getSource();
			if(v.equals(source)){
				weightTotal+= edge.getWeight();
				edgeTo.addFirst(edge);
			}
			else{
				edgeTo.addFirst(edge);
				weightTotal+= edge.getWeight();
				minPathAux(v);
			}
		}
	}
	
	public double darWeightTotal( ){
		return weightTotal;
	}


}