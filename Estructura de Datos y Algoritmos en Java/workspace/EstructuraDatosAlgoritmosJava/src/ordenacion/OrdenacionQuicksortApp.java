package ordenacion;

public class OrdenacionQuicksortApp {

	public static void main(String[] args) {
		int size = 10;																	// Definimos tamaño del arreglo
		ArregloQuicksort arreglo = new ArregloQuicksort(size);							// Creamos arreglo quicksort
		
		// Insertamos valores del arreglo
		arreglo.insertar(45);
		arreglo.insertar(90);
		arreglo.insertar(65);
		arreglo.insertar(15);
		arreglo.insertar(70);
		arreglo.insertar(11);
		arreglo.insertar(10);
		arreglo.insertar(40);
		arreglo.insertar(100);
		arreglo.insertar(5);
		
		arreglo.quickSort();															// Ordenamos arreglo
		arreglo.mostrarElementos();														// mostrar elementos ordenados ascendentemente
	}

}
