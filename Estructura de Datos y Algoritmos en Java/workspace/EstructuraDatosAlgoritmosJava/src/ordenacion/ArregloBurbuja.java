package ordenacion;

public class ArregloBurbuja {
	private long[] arr;
	private int numElems;
	
	public ArregloBurbuja(int tam) {
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
	
	/* Metodo que ordena un arreglo ascendentemente usando la ordenacion por burbuja
	 * */
	public void ordenacionBurbuja() {
		int i,j;							// variables locales que ayudan en el recorrido del arreglo
		
		/* j inicia en el ultimo indice del arreglo, se mueve una posicion hacia la izquierda cada vez que 
		 * culmina una iteracion.
		 * En cada iteracion, i se mueve desde la posicion 0 hasta la posicion j-1 del arreglo
		*/
		for(j=numElems-1;j>0;j--) {
			for(i=0;i<j;i++) {
				if(arr[i]>arr[i+1]) {		// si elemento en la posicion i es mayor al elemento en la posicion
											// i+1 se intercambian posiciones
					long temp = arr[i];		// guarda en la variable temporal temp el elemento en la posicion i
					arr[i] = arr[i+1];		// asigna en la posicion i el valor en la posicion i+1
					arr[i+1] = temp;		// asigna en la posicion i+1 el valor de la variable temporal temp
				}
			}
		}		
	}	
	
	
}
