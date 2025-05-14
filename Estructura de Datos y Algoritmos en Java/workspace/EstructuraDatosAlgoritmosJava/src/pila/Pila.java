package pila;

/* Implementacion de una clase pila con arreglo encapsulado
 * El arreglo se encargará de guardar los elementos de la pila
 * */

public class Pila {

	private Object[] arregloPila;		// Arreglo que contiene los elementos de la pila
	private int pilaSize;				// Tamaño del arreglo arregloPila
	private int cima;					// Variable que contiene el valor del inidice de la cima de la pila
	
	public Pila(int size) {
		this.pilaSize = size;			// Establece el tamaño del arreglo arregloPila
		this.cima = -1;					// el valor de cima = -1, indica que la pila está vacía(no tiene elementos)
		this.arregloPila = new Object[this.pilaSize];
	}
	
	/* Metodo que inserta un elemento en la cima de la pila
	 * */
	public void insertar(Object elemento) {
		++cima;
		this.arregloPila[cima] = elemento;
	}
	
	/* Metodo que retira el elemento que se encuentra en la cima de la pila y retorna este elemento eliminado
	 * */
	public Object quitar() {
		Object temp = arregloPila[cima];	// Giardamos en variable temp el elemento de la cima de la pila
		--cima;
		return temp;
	}

	/* Metodo que devuelve el elemento que se encuentra en la cima de la pila
	 * */
	public Object cimaPila() {
		return arregloPila[cima];
	} 
	
	/* Metodo que verifica si la pila esta vacia (No tiene elementos)
	 * */
	public boolean pilaVacia() {
		return cima == -1;
	}
	
	/* Metodo que veridica si la pila está llena
	 * Comprueba si la cima es pilaSize - 1
	 * */
	public boolean pilaLlena() {
		return cima == pilaSize - 1;
	}
	
	/* Metodo que devuelve el numero actual de elementos en la pila
	 * */
	public int size() {
		return cima + 1 ;
	}
	
	/* Metodo que muestra todos los elementos de la pila
	 * */
	public void mostrarElementosPila(){
		for(int i = 0; i < this.size(); i++) {
			System.out.print(arregloPila[i] + " ");
		}
		System.out.print(" ");
	}
}
