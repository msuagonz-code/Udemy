package ordenacion;

public class ArregloParticion {
	private long[] arr;
	private int numElems;
	
	public ArregloParticion(int tam) {
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
	
	/* Metodo que define el algoritmo de particionamiento de un arreglo.
	 * Este algoritmo utiliza un elemento pivote en el arreglo.
	 * A partir de este elemento pivote se divide el arreglo en:
	 * - una sublista izquierda que son los elementos menores al valor del pivote en la lista (Esta sublista izquierda no inclye el valor del pivote)
	 * - una sublista derecha que son lo elementos mayores o iguales al valor del pivote (La sublista derecha incluirá el valor del pivote)
	 * */
	public int particionamiento1(int izquierdo, int derecho, long pivote) {
		int punteroIzquierdo = izquierdo;						// Variable que apunta a los indices desde el inicio al final de la lista
		int punteroDerecho = derecho;							// Variable que apunta a los indices desde el final al inicio de la lista
		while(true) {											// Bucle while que itera indefinidamente hasta encontrar una sentencia break;
			while(punteroIzquierdo < derecho) {					// Itera mientras el punteroIzquierdo no llegue hasta el indice indicad por variable derecho
				if(arr[punteroIzquierdo]>=pivote){				// Si valor apuntado por punteroIZquierdo es igual o mayor al valor del pivote
					break;										// Salidmos del bucle while
				}
				punteroIzquierdo++;								// Movemos el punteroIzquierdo un indice hacia la derecha
			}				
			while(punteroDerecho > izquierdo) {					// Mientras el punteroDerecho no llegue hasta el indice apuntado por la variable izquierdo
				if(arr[punteroDerecho]<=pivote) {				// Si el valor apuntado por punteroDerecho es menor o igual al valor del pivote
					break;										// Salidmos del bucle while					
				}
				punteroDerecho--;								// Movemos el punteroDerecho un indice hacia la izquierda
			}
			
			if(punteroIzquierdo >= punteroDerecho){				// Si punteroIzquierdo es igual o mayor al punteroDrecho todos los elementos fueron visitados
				break;											// Salidmos del bucle while externo									
			}
			intercambiarValores(punteroIzquierdo, punteroDerecho);	// Intercambiamos valores de la lista entre las posiciones de los punteros izquierdo y derecho
		}
		return punteroIzquierdo;									// Retornamos el inidice del pivote en la lista
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
	
}
