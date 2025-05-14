package cola;

public class Cola {
	
	private Object[] colaArr;				// Arreglo que contiene los elementos de la cola
	private int maxSize;					// Guarda el tamaño máximo del arreglo
	private int frente;						// Apunta al primer elemento de la cola
	private int fin;						// Apunta al ultimo elemento de la cola
	private int numElem;					// Lleva control del numero de elementos en la cola
	
	public Cola(int size) {
		this.maxSize = size;
		this.colaArr = new Object[this.maxSize];	// Instancia arreglo que guarda los elementos de la cola
		this.fin = -1;								// La variable fin la inicializamos a -1
		this.frente = 0;							// La variable frente la inicializamos a 0
		this.numElem = 0;							// La cola está vacía, sin elementos
	}
	
	/* Inserta elemento al final de la cola
	 * En estge ejemplo se asume que la cola nunca se llena, y no se comprueba si la cola está llena o no
	 * */
	public void insertar(Object elemento){
		if(this.fin == this.maxSize - 1) {
			this.fin = -1;
		}
		this.fin++;
		this.colaArr[this.fin] = elemento;
		this.numElem++;
	}
	
	/* Retirar el elemento que se encuentra al frente de la cola
	 * Este ejemplo asume que la cola nunca estará vacía, y no se comprueba si la cola está vacía o no
	 * */
	public Object quitar() {
		Object temp = this.colaArr[frente];			// Obtiene el elemento del frente de la cola y lo asigna a una variable temporal
		++this.frente;								// Se mueve frente una posición para que apunte al siguiente inidice de la cola
		if(this.frente == this.maxSize) {
			this.frente = 0;						// Hace que la cola sea circulas (Aritmetida modular)
		}
		this.numElem--;								// Se reduce en uno el numero de elementos de la cola
		return temp;
	}
	
	/* Devuelve el elemento que se encuentra al frente o primero en la cola
	 * */
	public Object frenteCola(){
		return this.colaArr[this.frente];				// retorna elemento que está en la cabeza de la cola
	}
	
	/* Devuelve true si la cola está vacía (no tiene elementos)
	 * */
	public boolean colaVacia(){
		return (this.numElem == 0);						// retorna valor booleanod dependiendo si la cola está o no vacía
	}
	
	/* Devuelve true si la cola está llena
	 * */
	public boolean colaLlena(){
		return (this.numElem == this.maxSize);		// compara los valores del numero de elementos del arreglo con el tamaño del arreglo
	}
	
	/* Devuelve el nuermo de elementos que tiene la cola
	 * */
	public int sizeCola() {
		return this.numElem;						// Retorna el valor de la variable numElem
	}
	
}
