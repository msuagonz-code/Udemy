package arreglo;

public class ArregloOrdenadoApp {

	public static void main(String[] args) {
		// 1. Creamos arreglo con 30 elementos
		int maxSize = 30;					// tamaño del arreglo
		ArregloOrdenado arrOrd;				// referencia a la clase ArregloOrdenado
		
		arrOrd = new ArregloOrdenado(maxSize);		// Crea instancia de clase ArregloOrdenado con un tamaño maximo de 
													// de elementos para el arreglo igual a variable maxSize
		
		// 2. Insertamos 10 valores al arreglo
		arrOrd.insertar(77);
		arrOrd.insertar(99);
		arrOrd.insertar(44);
		arrOrd.insertar(55);
		arrOrd.insertar(22);
		arrOrd.insertar(89);
		arrOrd.insertar(11);
		arrOrd.insertar(00);
		arrOrd.insertar(66);
		arrOrd.insertar(34);
		
		// 3. Imprimimos valores de arreglo arrOrd
		arrOrd.mostrarArreglo();
		
		//4. Buscamos elemento en arreglo
		long buscar = 55;
		int posicion;
		
		posicion = arrOrd.busquedaBinaria(buscar);
		
		if(posicion != -1) {
			System.out.println("Se encontro elemento "+ buscar +" en la posición "+ posicion);			
		}else {
			System.out.println("No se encontro elemento "+ buscar);						
		}
		
		// 5. Borrar items del arreglo
		arrOrd.eliminar(0);
		arrOrd.eliminar(55);
		arrOrd.eliminar(99);
		
		// 6. Mostrar valores del arreglo arrOrd
		arrOrd.mostrarArreglo();
		
	}

}
