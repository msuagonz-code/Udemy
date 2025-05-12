package arreglo;

public class ArregloApp {

	public static void main(String[] args) {
		long[] arr; 			// referencia a arreglo
		arr = new long[20];		// creación con items del tipo primitivo long
		int numElems; 			// variable que almacena el numero actual de items en el arreglo arr
		int j;					// inidice de arreglo
		
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
		
		for(j = 0; j < numElems; j++) {
			System.out.print(arr[j] + " ");
		}
		System.out.println("");
	}

}
