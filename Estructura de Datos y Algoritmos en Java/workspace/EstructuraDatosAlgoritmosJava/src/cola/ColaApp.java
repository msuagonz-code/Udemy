package cola;

public class ColaApp {

	public static void main(String[] args) {
		Cola cola = new Cola(5);
			
		// Insertamos 4 elementos a la cola
		cola.insertar(10);
		cola.insertar(15);
		cola.insertar(20);
		cola.insertar(25);
		
		while(!cola.colaVacia()) {
			Object n = cola.quitar();
			System.out.print(n + " ");
		}
		
		// Insertamos 5 elementos
		cola.insertar("Perro");
		cola.insertar("Gato");
		cola.insertar("Caballo");
		cola.insertar("Leon");
		cola.insertar("Tigre");
		
		System.out.println(" ");
		System.out.print("Elemento en la cabeza de la cola: ");
		System.out.print(cola.frenteCola());
		System.out.println(" ");
		
		System.out.print("La cola está vacía?: ");
		System.out.print(cola.colaVacia());
		System.out.println(" ");
		
		System.out.print("La cola está llena?: ");
		System.out.print(cola.colaLlena());
		System.out.println(" ");
		
		System.out.print("Tamaño de la cola: ");
		System.out.print(cola.sizeCola());
		System.out.println(" ");
		
		while(!cola.colaVacia()){
			Object m = cola.quitar();
			System.out.print(m + " ");
		}
	}

}
