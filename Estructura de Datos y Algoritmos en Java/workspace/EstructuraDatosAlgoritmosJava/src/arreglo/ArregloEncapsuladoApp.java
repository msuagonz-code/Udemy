package arreglo;

public class ArregloEncapsuladoApp {

	public static void main(String[] args) {
		// 1- crear un arreglo con un tamaño de 30 elementos
		int arrSize = 30; 				// Tamaño arreglo
		Arreglo arr;					// Referencia a clase arreglo
		arr = new Arreglo(arrSize);		// Crea instancia de clase arreglo. El Constructor
										// Acepta como argumento el tamaño del arreglo
		
		// 2- insertar 10 elementos en el arreglo
		arr.insertar(77);
		arr.insertar(99);
		arr.insertar(44);
		arr.insertar(55);
		arr.insertar(22);
		arr.insertar(88);
		arr.insertar(11);
		arr.insertar(0);
		arr.insertar(66);
		arr.insertar(33);
		
		// 3- imprimir los valores del arreglo en pantalla
		arr.mostrarElementos();
		
		// 4- Buscamos elemento en arreglo
		int valorBuscar = 35;			// Valor a buscar
		if(arr.buscar(valorBuscar)){
			System.out.println("Se encontro el valor "+ valorBuscar);
		}else {
			System.out.println("No se encontro el valor "+ valorBuscar);
		}
		
		// 5- Borramos 3 elementos del arreglo
		arr.eliminar(0);
		arr.eliminar(55);
		arr.eliminar(99);
		
		
		// 6- Imprimimos valores del arreglo nuevamento
		arr.mostrarElementos();
	
	}

}
