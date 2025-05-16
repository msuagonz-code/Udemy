package ordenacion;

public class ArregloSeleccion {
	private long[] arr;
	private int numElems;
	
	public ArregloSeleccion(int tam) {
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
	
	/* Metodo que ordena un arreglo ascendentemente usando ordenación por Seleccion
	 * */
	public void ordenacionSeleccion() {
	  int minimo;             			// guarda valor minimo
	  long temp;              			// variable temporal que ayudara en el intercambio de elementos del arreglo
	    
	  for(int i=0;i<numElems-1;i++) {   	// el valor de i establece el comienzo de cada recorrido (bucle externo)
	    minimo=i;           				// al inicio de cada recorrido el valor de minimo se inicializa apuntando al primer elemento
	    for(int j=i+1;j<numElems;j++) { 	// recorremos cada uno de los elementos del arreglo a partir del indice i+1 (bucle interno)
	      if(arr[j] < arr[minimo]) {  		// comparamos elemento en el indice j con el actual valor minimo
	        minimo=j;       				// el elemento en la posicion j es menor que el actual valor minimo; elemento en la posicion j es el nuevo minimo
	      }
	    }
	    // intercambiamos elementos del arreglo en los indices apuntados por i y minimo
	    temp=arr[i];
	    arr[i]=arr[minimo];
	    arr[minimo]=temp;
	  }   
	} 
	
	// metodo que elimina los elementos repetidos del arreglo
	public void eliminarElementosRepetidos() {
	  long[] temp=new long[numElems];	// arreglo que guarda temporalmente elementos sin repeticiones
	  int j;							// nos ayudara a recorrer arreglo temporal temp
	  int numElemsTemp=0;				// numero de elementos del arreglo temporal temp
		
	  for(int i=0;i<numElems;i++) {		// recorremos todos los elementos del arreglo arr
		 for(j=0;j<numElemsTemp;j++) 	// recorremos arreglo temporal buscando el elemento en la posicion i del arreglo arr
			 if(arr[i]==temp[j]) 		// si el elemento en la posicion i del arreglo arr se encuentra en el arreglo temporal temp
				 break;					// no seguimos recorriendo arreglo temporal porque se encontro elemento 
			
		 if(j==numElemsTemp) {			// si el indice j llega hasta el final del arreglo temporal el elemento no esta repetido
			 temp[numElemsTemp]=arr[i];	// guardamos elemento del arreglo arr en la posicion i en arreglo temporal (en la posicion k)
			 numElemsTemp++;			// aumenta en uno el numero de elementos en el arreglo temporal
		 }
	  }
		
	  // copiamos elementos no repetidos del arreglo temporal temp al arreglo arr
	  numElems=0;						// borramos (reseteamos) todos los elementos del arreglo arr
	  for(int p=0;p<numElemsTemp;p++) {	// recorremos todo el arreglo temp
		arr[p]=temp[p];				// copiamos cada elemento de temp a arr
		numElems++;					// aumentamos en uno el numero de elementos de arr para elemento copiado
	  }
	}
	
	// metodo que muestra los elementos del arreglo que tengan una diferencia de dos unidades en pares
	public void mostrarParesOrdenados	() {
	  String elemPares="";										// guarda los pares de elementos con diferencia de dos
	  int j=0;													// apunta al indice del primer elemento del arreglo arr
	  int i=1;													// apunta al indice del segundo elemento del arreglo arr
	  long diferencia;											// diferencia entre elementos del arreglo arr

	  eliminarElementosRepetidos();								// eliminamos elementos repetidos en el arreglo arr
	  ordenacionSeleccion();									// ordenamos elementos del arreglo arr de manera ascendente
	  while(i<numElems) {										// mientras no lleguemos al final del arreglo arr
		diferencia=arr[i]-arr[j];								// calculamos diferencia entre elemento en la posicion i de arr y el minimoValor
		if(diferencia<2) {										// diferencia es diferente a dos
			  i++;												// nos movemos al siguiente elemento del arreglo arr
			  continue;											// saltamos a la siguiente iteracion del bucle while
		}
		if(diferencia==2)										// diferencia es igual a dos
			elemPares+="(" + arr[j] + "," + arr[i] + ") ";		// guardamos elementos pares con diferencia igual a dos
		j++;
		i=j+1;													// movemos el indice del segundo elemento del arreglo
	 }		  
	 System.out.print(elemPares);
	}
	
}
