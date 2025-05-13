package arreglo;

public class ArregloOrdenado {
	private long[] arr; // referencia a arreglo con elementos del tipo long
	private int numElem; // numero actual de elementos en el arreglo

	public ArregloOrdenado(int size) {
		arr = new long[size]; // crear arreglo de tamaño igual a size
	}

	/*
	 * Busqueda binaria retorna la posición del elemento buscado en el arreglo
	 * retorna -1 en caso de no encontrarlo
	 */
	public int busquedaBinaria(long dato) {
		int minimo = 0; // inidice minimo del arreglo
		int maximo = numElem - 1; // inidce maximo del arreglo
		int central;

		while (minimo <= maximo) {
			central = (minimo + maximo) / 2; // punto central del arreglo

			if (arr[central] == dato) {
				return central; // elemento encontrado
			} else {
				if (dato > arr[central]) { // elemento se encuentra en la mitad superior
					minimo = central + 1;
				} else {
					maximo = central - 1;
				}
			}
		}

		return -1;
	}

	/* En el curso este es un caso ideal, tomando en cuenta que el arreglo tiene espacios vacíos sin utilizar
	 * si no los tuviera, tendrías que crear un arreglo mas grande con espacios vacíos y copiar los valores del
	 * arreglo original al arreglo nuevo con espacios vacíos para después emplear este algoritmo
	 * */
	public void insertar(long dato) {
		int j;
		for (j = 0; j < numElem; j++) { // recorre arreglo elemento por elemento
			if (arr[j] > dato) { // elemento en la posición j es mayor a elemento a insertar
				break; // se detiene el bucle. el elemento a insertar estará en la posición j
			}
		}

		// desplaza celdas de arreglo una posición hacia arriba
		for (int k = numElem; k > j; k--) { // recorre el arreglo en reversa desde el ultimo indice hasta el indice j
			arr[k] = arr[k - 1]; // copia el valor de celda una celda hacia arriba
		}

		arr[j] = dato; // insertar dato en arreglo
		numElem++;
	}
	
	/* Elimina dato de arreglo
	 * retorna false si no se puede eliminar dato porque no existe en el arreglo
	 * */
	public boolean eliminar(long dato) {
		int j = busquedaBinaria(dato);		// realiza busqueda de argumento dato en arreglo. Retorna indice con la 
											// ubicación de dato en arreglo
		
		if(j == -1) {
			return false;
		}else {
			for(int k=j; k < numElem; k++) {	// desplazar elementos hacia abajo a partir del indice j 
				arr[k] = arr[k+1];				// desplaza elemento hacia abajo
			}
			numElem--;							// decrementa en uno la cantidad
			return true;
		}
		
	}
	
	public void mostrarArreglo() {
		for(int j = 0; j < numElem; j++) {
			System.out.print(arr[j] + " ");
		}
		System.out.println(" ");
	}
	
	public long valorElemento(int i) {
		return arr[i];
	}
}
