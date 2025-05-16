package ordenacion;

import java.util.Scanner;

public class OrdenacionInsercionApp {

	public static void main(String[] args) {
		
		// creamos instancia de la clase ArregloInsercion con una capacidad para almacenar 10 elementos
		ArregloInsercion arr=new ArregloInsercion(10);
		
		// insertamos 6 valores al arreglo
		arr.insertar(16);
		arr.insertar(11);
		arr.insertar(56);
		arr.insertar(4);
		arr.insertar(87);
		arr.insertar(78);
		
		// solicitamos valor para el cual queremos averiguar los numeros en el arreglo menores a el
		Scanner entrada=new Scanner(System.in);
		System.out.print("Ingrese valor: ");
		int valor=entrada.nextInt();
		
		// cerramos instancia de la clase Scanner creada
		entrada.close();
		
		// mostramos elementos insertados
		System.out.print("Elementos insertados: ");
		arr.mostrarElementos();
		
		// imprimimos valores menores al valor de la variable valor
		System.out.print("Elementos menores a " + valor + ": ");
		arr.mostrarElementosMenoresA(valor);
	}
}
