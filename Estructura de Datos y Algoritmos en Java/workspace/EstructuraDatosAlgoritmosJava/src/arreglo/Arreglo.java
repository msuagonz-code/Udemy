package arreglo;

public class Arreglo {
	private long[] arr;
	private int numElems;
	
	public Arreglo(int tam) {
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
}
