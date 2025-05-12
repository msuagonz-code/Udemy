package arreglo;

public class ArregloApp {

	public static void main(String[] args) {
		long[] arr; 			// referencia a arreglo
		arr = new long[20];		// creación con items del tipo primitivo long
		int numElems; 			// variable que almacena el numero actual de items en el arreglo arr
		int j;					// inidice de arreglo
		long buscarItem;		// Item a buscar en arreglo
		//Insertar 10 items en el arreglo
		
		arr[0] = 77;
		arr[1] = 99;
		arr[2] = 44;
		arr[3] = 55;
		arr[4] = 22;
		arr[5] = 88;
		arr[6] = 11;
		arr[7] = 00;
		arr[8] = 66;
		arr[9] = 33;
		numElems = 10;

		// Recorrido items arreglo
		for(j = 0; j < numElems; j++) {
			System.out.print(arr[j] + " ");
		}
		System.out.println("");
		
		// Busqueda Lineal
		// busca item con valor 66 en arreglo
		buscarItem = 14;
		for(j=0; j < numElems; j++) {
			if(arr[j] == buscarItem) {		// Se encontro item
				break;						// salir del bucle for porque el item se encontro
			}
		}
		
		if(j == numElems){					// Se llego al final del arreglo y no se encontro el item
			System.out.println("No se pudo encontrar el numero " + buscarItem);
		}else {								// Se encontro Item
			System.out.println("Se encontro el numero "+ buscarItem);
		}
		
		// Borrar un elemento de un arreglo numerico
		buscarItem = 55;
		for(j=0; j < numElems; j++){		// Bscar item
			if(arr[j] == buscarItem){
				break;						// item encontrado, salid del bucle
			}
		}
		
		for(int k=j; k < numElems; k++) { 	// mueve items superiores hacia abajo a partir del indice encontrado
			arr[k] = arr[k+1];
		}
		
		numElems--;							// reduce el nuermo de items del arreglo en 1
		
		// Imprime items
		for(j=0; j<numElems; j++){
			System.out.print(arr[j] + " ");
		}
	}

}
