package listaenlazadasimple;

public class Nodo {
	
	// Variables de instancia
	private Object dato;				// Referencia campo dato
	private Nodo siguiente;				// Referencia al siguiente nodo en la lista enlazada
	
	public Nodo(Object dato) {
		this.dato = dato;				// Guarda dato del nodo 
		this.siguiente = null;			// Siguiente apunta a null porque no conocemos el nodo siguiente
	}
	
	// Metodo que imprime el valor del campo dato
	public void mostrarNodo() {
		System.out.println("{" + this.dato + "}");
	}
}
