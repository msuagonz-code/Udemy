package ordenacion;

import java.util.Scanner;

public class OrdenacionBurbujaApp {

	public static void main(String[] args) {
		
		// Problema: Calcular el maximo y el minimo valor de un conjunto de N datos
		
		// Variable que guarda los valores ingresados por el teclado
		long dato;
		
		// Declaramos instancia de la clase Scanner para ingresar datos por la consola
		Scanner entrada = new Scanner(System.in);
		
		// Ingresamos cantidad de datos a procesar
		System.out.print("Ingrese la cantidad de datos a analizar: ");
		int size = entrada.nextInt();
		
		// Creamos una instancia de la clase ArregloBurbuja (pasamos la variable size al constructor)
		ArregloBurbuja arr = new ArregloBurbuja(size);
		
		// Ingresar conjunto datos
		System.out.println("Ingrese los " + size + " datos ");
		
		// Leemos los datos numericos ingresados por teclado
		for(int i = 0; i < size; i++) {
			System.out.print("Dato "+ (i + 1) +": ");
			dato = entrada.nextLong();
			arr.insertar(dato);
		}
		
		// Cerramos instancia de Scanner
		entrada.close();
		
		// Imprimimos datos ingresados
		System.out.println("Datos antes de estar ordenados: ");
		arr.mostrarElementos();
		
		// Ordenamos los datos usando la ordenación por burbuja
		arr.ordenacionBurbuja();
		
		// Imprimimos datos ordenados
		System.out.println("Datos ordenados: ");
		arr.mostrarElementos();
		
		// Imprimimos el menor y mayor
		System.out.println("El menor valor es: "+ arr.valorElemento(0));
		System.out.println("El mayor valor es: "+ arr.valorElemento(size-1));
		
		// Total de comparaciones se calcula con la formula (n-1)(n)/2, en notation O grande sería O(n^2), porque se ignoran las constantes
		// es decir en la formula se ignora la constante -1 y 2 quedanto solo (n)(n)
		// este es un algoritmo lento
	}

}
