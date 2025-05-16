package ordenacion;

import java.util.Iterator;

public class ArregloShell {
	private long[] arr;
	private int numElems;
	
	public ArregloShell(int tam) {
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
	
	/* Ordenacion Shell (Shell sort)
	 * */
	public void ordenacionShell() {
		int h=1;						// Incremento (Determina espaciamiento entre elementos para el ordenamiento)
		long temp;						// guarda temporalmente elemento del arreglo
		int i;							// Indice del arreglo
		int j;							// Indice del arreglo
		
		// Bucle encuentra el valor inicial de h
		while(h<numElems){				// mientras h sea menor a la cantidad de elementos del arreglo
			h=h*3+1;					// formula del intervalo de Knuth (genera la serie 1,4,13,40,121,364...)
		}
		while(h>0) {					// iteramos hasta que h sea igual a 1
			
			for (i = h; i < numElems; i++) {	// Desplazamos el indice i de acuerdo a los valores que va tomando h
				temp =  arr[i];					// Guardamos temporalmente el valor del elemento apuntado por el indice i
				j = i;							// Indice j comienza en la posición del indice i
				
				// Ordenamos Subarreglo
				while( j-h >=0){					// itera si existen más elementos en subarreglo anteriores al elemento en temp
					if( arr[j-h] < temp) {			// Elemento a una distancia h de temp es menor que temp
						break;						// Terminamos el bucle while (nos movemos al siguiente valor de j)
					}
					
					arr[j] = arr[j-h];				// movemos elemento en la posición j-h a la posición j
					j=j-h;							// movemos el inidice j hacia la izquierda del arreglo h posiciones
				}
				arr[j] = temp;						// colocamos el valor de temp en la posición del indice j del arreglo
			}
			
			h=(h-1)/3;					// Decrementamos el valor de h usando la inversa de la formula de knuth
		}
	}
	
	
	
}
