package model.structures.listaComparable;



public class Nodo<E> {
	private Nodo<E> siguiente;
	private Nodo<E> anterior;
	private E item;

	public Nodo(E item){
		this(item, null, null);
	}
	public Nodo(E item, Nodo<E> siguiente, Nodo<E> anterior){
		this.siguiente = siguiente;
		this.anterior = anterior;
		this.item = item;
		
	}

	public Nodo<E> darSiguiente(){
		return siguiente;
	}

	public void cambiarSiguiente(Nodo<E> pSiguiente){
		siguiente=pSiguiente;
	}

	public E obtenerItem(){
		return item;
	}
	public void cambiarItem(E pItem){
		item=pItem;
	}
	public Nodo<E> darAnterior() {
		return anterior;
	}
	public void CambiarAnterior(Nodo<E> pAnterior) {
		anterior = pAnterior;
	}
	public Nodo<E> getSiguiente() {
		return siguiente;
	}
	public void setSiguiente(Nodo<E> siguiente) {
		this.siguiente = siguiente;
	}
	public Nodo<E> getAnterior() {
		return anterior;
	}
	public void setAnterior(Nodo<E> anterior) {
		this.anterior = anterior;
	}
	public E getItem() {
		return item;
	}
	public void setItem(E item) {
		this.item = item;
	}
}
