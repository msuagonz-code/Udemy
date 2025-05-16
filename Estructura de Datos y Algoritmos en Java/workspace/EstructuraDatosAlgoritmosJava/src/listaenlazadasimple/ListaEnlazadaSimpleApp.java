package listaenlazadasimple;

public class ListaEnlazadaSimpleApp {

	public static void main(String[] args) {
		// Creamos instancia de la clase ListaEnlazadaSimple
		ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
		
		// Insertamos 5 elementos del tipo double a la cabeza de la lista enlazada
		lista.insertarCabezaLista(2.98);
		lista.insertarCabezaLista(3.11);
		lista.insertarCabezaLista(1.2);
		lista.insertarCabezaLista(12.212);
		lista.insertarCabezaLista(8.732);
		
		// Mostrar los nodos de la lista enlazada simple
		lista.mostrarLista();
		
		// Insertamos nuevo nodo entre dos nodos de la lista enlazada simple
		Nodo nodo = lista.insertar(1.2, 45);
		if(nodo == null) {
			System.out.println("No se pudo insertar nuevo nodo. ");
		} else {
			System.out.println("El Nodo se inserto correctamente. ");
		}
		
		// Volvemos a mostrar nodos de la lista enlazada (Verificamos si la insercion del nodo es correcta)
		lista.mostrarLista();
		
		// Buscamos nodo en la lista enlazada simple
		nodo = lista.buscarLista(3.11);
		if(nodo != null) {
			System.out.println("Se encontro nodo con dato buscado");
		} else {
			System.out.println("No se encontro nodo con dato buscado");
		}
		
		// Borramos nodos de la lista enlazada simple
		while(!lista.vacia()) {
			nodo = lista.eliminarCabezaLista();
			System.out.println("Nodo eliminado ");
			nodo.mostrarNodo();
			System.out.println(" ");
		}
		
		// Comprobamos nuevamente si la lista enlazada simple está vacia
		System.out.println("La lista enlazada simple está vacia?: "+ lista.vacia());
		lista.mostrarLista();
		
		
		// Insertar nuevos nodos a la lista enlazada simple
		lista.insertarCabezaLista("Perro");
		lista.insertarCabezaLista("Gato");
		lista.insertarCabezaLista("Caballo");
		lista.insertarCabezaLista("Conejo");
		
		// Mostramos nodos de la lista enlazada
		lista.mostrarLista();
		
		// Eliminamos un nodo
		lista.eliminar("Gato");
		
		// Mostramos nodos de la lista enlazada simple
		lista.mostrarLista();
	}
}
