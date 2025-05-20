package ordenacion;

import pila.Pila;

public class ArregloQuicksort {
	private long[] arr;						// Referencia a arreglo con elementos de tipo long
	private int numElems;					// Numero actual de elementos en el arreglo
	Pila izquierda = new Pila(10);			// Guarda indice actual de cada subarreglo derecho
	Pila derecha = new Pila(10);			// Guarda indice final de cada subarreglo derecho
	
	
	public ArregloQuicksort(int tam) {
		this.arr = new long[tam];	// crear arreglo
		this.numElems = 0;			// arreglo inicia en 0 elementos
	}
	
	public boolean buscar(long dato) {
		int j;
		for(j=0;j<numElems;j++) {
			if(arr[j] == dato) {
				break;
			}
		}
		
		if(j == numElems) {
			return false;
		}else {
			return true;
		}
	}
	
	public void insertar(long dato) {
		arr[numElems] = dato;		// insertar dato en el arreglo
		numElems++;					// incrementa en uno numero de elementos de arreglo
	}
	
	
	public boolean eliminar(long dato) {
		int j;
		for(j=0; j<numElems; j++){		// busca el elemento en un arreglo
			if(arr[j] == dato) {		// se encontro el elemento buscado
				break;
			}
		}
		
		if(j==numElems) {				// no se encontro el elemento buscado
			return false;
		}else {							// se encontro el elemento buscado
			for(int k=j; k<numElems; k++){	// desplaza elementos superiores al indice j
				arr[k] = arr[k+1];
			}
			numElems--;
			
			return true;
		}
	}
	
	public void mostrarElementos() {
		for(int j=0; j<numElems; j++) {
			System.out.print(arr[j] + " ");
		}
		System.out.println("");
	}
	
	public long valorElemento(int i) {
		return arr[i];
	}
	
	/* Metodo de particionamiento donde el valor del pivote es el ultimo elemento del arreglo
	 * Divide el arreglo en dos sublistas
	 * 	- La sublista izquierda está formada por los elementos menores al valor del pivote
	 *  - La sublista derecha está formada por los elementos iguales o mayores al valor del pivote (el valor del pivote se incluye en esta sublista)
	 * */
	public int particionamiento(int izquierdo, int derecho, long pivote) {
		int punteroIzquierdo = izquierdo;						// Variable que apunta a los indices del arreglo comenzando por el valor del indice izquierdo
		int punteroDerecho = derecho;							// Variable que apunta a los indices del arreglo comenzando por el valor del indice derecho
		while(true){											// Itera indefinidamente hasta encontrar una sentencia break
			while(arr[punteroIzquierdo]<pivote) {				// Mientras se encuentren más valores en el arreglo menores al pivote
				punteroIzquierdo++;								// Movemos el punteroIzquierdo un indice hacia la derecha en el arreglo
			}
			while(punteroDerecho>izquierdo) {					// Mientras el punteroDerecho no llegue hasta la posición del indice indicado por izquierdo
				punteroDerecho--;								// Movemos el punteroDerecho un indice hacia la izquierda en el arreglo
				if(arr[punteroDerecho]<=pivote){				// Si valor en la posición indicada por punteroDerecho es menor o igual al valor del pivote
					break;										// Salimos del bucle while
				}
			}
			if(punteroIzquierdo < punteroDerecho) {				// Evaluamos si punteroIzquierdo sigue apuntando a un indice menor que el punteroDerecho
				intercambiarValores(punteroIzquierdo, punteroDerecho);	// Intercambiamos valores en las posiciones de punteroIzquierdo y punteroDerecho
			}else {												// El punteroIzquierdo apunta a un indice menor al que apunta punteroDerecho
				break;
			}
		}
		intercambiarValores(punteroIzquierdo, derecho);			// Movemos el valor del pivote a la posicion del indice de particionamiento
		return punteroIzquierdo;								// Retornamos el indice de particionamiento
	}
	
	// Metodo que intercambia las posiciones de dos valores del arreglo
	public void intercambiarValores(int indice1, int indice2) {
		long temp;												// Guarda temporalmente valor del arreglo
		temp=arr[indice1];										// Guarda valor del indice 1 temporalmente 
		arr[indice1] = arr[indice2];							// Copia el valor del indice 2 en el indice 1
		arr[indice2] = temp;									// Asignamos el valor de temp en el indice 2
	}
	
	/* Metodo que implementa el algoritmo Quicksort
	 * */
	public void quickSort() {
		int inicio;												// Guarda inidice inicial del subarreglo a particionar		
		int fin;												// Guarda indice final del subarreglo a particionar
		long pivote;											// Guarda el valor del pivote del subarreglo a particionar
		int inidiceParticionamiento;							// Guarda el indice de particionamiento
		izquierda.insertar(0);									// Insertamos el indice inicial del arreglo a ordenar
		derecha.insertar(numElems-1);							// Insertamos el indice final del arreglo a ordenar
		
		while(!izquierda.pilaVacia()) {							// Itera mientras exista indices de subarreglos por particionar
			inicio = (int) izquierda.quitar();					// Extraemos el indice inicial del subarreglo a particionar
			fin = (int) derecha.quitar();						// Extraemos el indice final del subarreglo a particionar
			
			while( (fin - inicio) > 0) {						// Verificamos si el subarreglo a particionar tiene mas de un elemento
				pivote = arr[fin];								// hacemos que el pivote sea el ultimo elemento del subarreglo a particionar
				inidiceParticionamiento = particionamiento(inicio, fin, pivote);	// Realizamos el particionamiento al subarreglo
				
				if( (inidiceParticionamiento + 1) < fin) {		// comprobamos si existe subarreglo derecho
					izquierda.insertar(inidiceParticionamiento+1);	// Guardamos el indice inicial del subarreglo derecho pendiente por particionar
					derecha.insertar(fin);							// Guardamos el indice final del subarreglo derecho pendiente por particionar
				}
				fin=inidiceParticionamiento-1;					// Actualizamos fin para que apunte al ultimo indice del siguiente subarreglo izquierdo
			}
		}
	}
}
