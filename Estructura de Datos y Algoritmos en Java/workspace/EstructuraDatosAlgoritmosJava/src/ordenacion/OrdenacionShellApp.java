package ordenacion;

import java.util.Scanner;

public class OrdenacionShellApp {

	public static void main(String[] args) {
		/*
		 Dado un numero entero no negativo, retornarlo con sus digitos en orden descendente
		 */
		
		ArregloShell arr;
		
		// Ingresamos numero a analizar
		Scanner scanner=new Scanner(System.in);
		System.out.print("Ingrese numero entero: ");
		int numero=scanner.nextInt();
		scanner.close();
		
		// contamos el numero de digitos
		int n=numero;
		int numDigitos=0;
		while (n>0) {
			n=n/10;
			numDigitos++;
		}
		
		// obtenemos cada digito del numero y lo ingresamos al arreglo
		long cociente;
		long digito;
		long temp=numero;
		arr=new ArregloShell(numDigitos);
		while(temp!=0) {
			cociente=temp/10;
			digito=temp-cociente*10;
			arr.insertar(digito);
			temp=cociente;
		}
		
		// ordenamos el arreglo usando la ordenacion Shell
		arr.ordenacionShell();
		
		// imprimimos valores de arreglo desde ultimo elemento hasta el primero
		arr.mostrarElementosOrdenInverso();
		
	}
}
