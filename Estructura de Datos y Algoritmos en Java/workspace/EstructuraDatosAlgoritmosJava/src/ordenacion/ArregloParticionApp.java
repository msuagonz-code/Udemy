package ordenacion;

public class ArregloParticionApp {

	public static void main(String[] args) {
		int size = 10;												// Tamaño del arreglo
		ArregloParticion arr = new ArregloParticion(size);			// Creamos instancia de la clase ArregloParticion
		
		// Insertamos valores al arreglo
		arr.insertar(20);
		arr.insertar(30);
		arr.insertar(65);
		arr.insertar(55);
		arr.insertar(75);
		arr.insertar(90);
		arr.insertar(40);
		arr.insertar(60);
		arr.insertar(85);
		arr.insertar(70);
		
		// mostrar arreglo
		System.out.print("Arreglo sin particionamiento: ");
		arr.mostrarElementos();
		System.out.println();
		
		// Seleccionamos valor del pivote
		long pivote = 70;
		int izquierdo = 0;
		int derecho = size - 1;
		int indiceParticionamiento = arr.particionamiento(izquierdo, derecho, pivote);
		
		// mostrar arreglo con particionamiento
		System.out.println("Valor del pivote es: "+ pivote);
		System.out.println("Particionamiento está en el indice: "+ indiceParticionamiento);
		System.out.print("Arreglo con particionamiento: ");
		arr.mostrarElementos();
		System.out.println();
		
	}

}
