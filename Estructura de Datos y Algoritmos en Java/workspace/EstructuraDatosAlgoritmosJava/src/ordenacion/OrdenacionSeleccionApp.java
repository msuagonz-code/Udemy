package ordenacion;

public class OrdenacionSeleccionApp {
	

	public static void main(String[] args) {
		/*
		 *  Dado un conjunto de valores enteros, retornar todos las pares de números cuya diferencia sea igual a dos. El resultado deberá 
		 *  estar en orden ascendente de valores. Si se ingresan valores enteros duplicados solo se considerarán una vez para el cálculo de 
		 *  las diferencias.
		 */
		
		/*
		 * Paso1: Ingreso de datos a analizar
		 */
		ArregloSeleccion arr=new ArregloSeleccion(10);
		arr.insertar(11);
		arr.insertar(3);
		arr.insertar(1);
		arr.insertar(9);
		arr.insertar(3);
		arr.insertar(15);
		arr.insertar(1);
		arr.insertar(5);
		arr.insertar(5);
		arr.insertar(13);
		
		System.out.print("Se ingreso el siguiente conjunto de caracteres: ");
		arr.mostrarElementos();
		
		/*
		 * Paso 2: Buscamos pares de numeros que tengan una diferencia de dos
		 */
		System.out.print("Pares de Elementos:");
		arr.mostrarParesOrdenados();
	}

}
