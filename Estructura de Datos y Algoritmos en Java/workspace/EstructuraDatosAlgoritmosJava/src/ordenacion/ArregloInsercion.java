package ordenacion;

import java.util.Iterator;

public class ArregloInsercion {
	private long[] arr;
	private int numElems;
	
	public ArregloInsercion(int tam) {
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
	
	/* Metodo que ordena un arreglo usando el algoritmo de insercion
	 * */
	public void ordenacionInsercion() {
		int i;							// Indice i recorre cada elemento del arreglo
		int j; 							// Indice j recorre elementos a comparar con el valor i
		
		for (i = 0; i < numElems; i++) {		// Bucle Externo (Itera todo el arreglo)
			long temp= arr[i];					// Guarda temporalmente el elemento removido en la posición i
			j=i;								// j comienza en la posición i del arreglo
			
			while(j > 0 && arr[j-1]>= temp) {	// itera hasta encontrar el primer elemento menor al valor de temp o cuando j llega a 0
				arr[j] = arr[j-1];				// movemos el elemento en la posición j-1 a la posición j
				--j;							// movemos j a la siguiente posición a la izquierda
			}
			arr[j] = temp;						// Insertamos el valor de temp en la posición j
		}
	}
	
	// metodo que imprime los valores menores al valor ingresado como parametro
	public void mostrarElementosMenoresA(int valor) {
		int i=0;							// indice para recorrer arreglo
		ordenacionInsercion();				// ordenamos arreglo ascendentemente
		while(arr[i]<valor) {				// mientras parametro valor sea menor al elemento en el indice i del arreglo
			System.out.print(arr[i]+" ");	// imprime elemento en el indice i del arreglo por ser menor al parametro valor
			i++;							// nos movemos al siguiente elemento del arreglo
		}
		System.out.println("");				// dejamos una linea en blanco
	}
	
}
