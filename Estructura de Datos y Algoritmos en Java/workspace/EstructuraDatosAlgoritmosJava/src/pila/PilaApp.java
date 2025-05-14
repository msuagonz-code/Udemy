package pila;

public class PilaApp {
	
	public static void main(String[] args) {
		
		Pila pila = new Pila(5); 				// Creamos una pila de 5 elementos
		
		// Insertar enteros a la pila
		pila.insertar(20);
		pila.insertar(40);
		pila.insertar(60);
		pila.insertar(80);
		pila.insertar(90);
		
		System.out.println("Elementos insertados en la pila ");
		pila.mostrarElementosPila();
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Elementos en la cima de la pila: " + pila.cimaPila());
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("La pila está llena?: " + pila.pilaLlena());
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Elementos eliminados de la pila: ");
		while(!pila.pilaVacia()) {
			Object valor = pila.quitar();
			System.out.println("Valor eliminado: "+ valor);
		}
		
		System.out.println(" ");
		System.out.println(" ");
		// Insertar nuevos elementos
		pila.insertar("Perro");
		pila.insertar("Oso");
		pila.insertar("Gato");
		System.out.println("Elementos nuevos en la pila ");
		pila.mostrarElementosPila();
		
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("Cantidad de elementos en la pila: "+ pila.size());
	}
}
