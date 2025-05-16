package listaenlazadasimple;

public class ListaEnlazadaSimple {
	// Variables de instancia
	private Nodo primero;						// Referencia al primer nodo de la lista enlazada

	public ListaEnlazadaSimple() {
		this.primero =  null;					// Creamos una lista enlazada simple sin elementos
	}
	
	/* Verifica si la lista enlazada simple está vacia
	 * */
	public boolean vacia(){
		return (this.primero == null);			// Comprueba si la variable primero apunta a null (lista vacía)
	}
	
	/* Inserta nodo en la cabeza de la lista enlazada simple
	 * */
	public void insertarCabezaLista(Object d) {
		Nodo nuevoNodo = new Nodo(d);			// Creamos un nuevo nodo
		nuevoNodo.siguiente = this.primero;			// Enlazamos el nuevo nodo con el nodo en la cabeza de la lista enlazada simple
		this.primero = nuevoNodo;				// El nuevo nodo es la nueva cabeza de la lista enlazada
	}
	
	/* Elimina nodo de la cabeza de la lista enlazada simple (asume que la lista enlazada no está vacía)
	 * */
	public Nodo eliminarCabezaLista(){
		Nodo temp = primero;				// Guardar referencia al primer nodo en la variable temp
		primero = primero.siguiente;		// Cambiamos referencia de la variable primero para que apunte al segundo nodo
		return temp;							//
	}
	
	/* Imprime el contenido de los nodos de la lista enlazada simple
	 * */
	public void mostrarLista() {
		System.out.print("Lista(primero -> ultimo): ");	// Recorremos lista enlazada desde el primero hasta el ultimo nodo
		Nodo nodoActual = primero;						// Comenzamos en el primer nodo de la lista enlazada simple
		
		while(nodoActual != null) {							// Mientras no se llegue al final de la lista enlazada simple
			nodoActual.mostrarNodo();						// Imprimimos el valor del campo dato del nodo actual
			nodoActual = nodoActual.siguiente;				// Nos movemos al siguiente nodode la lista enlazada simple
		}
		
		System.out.println("");
	}
	
	/* Busca nodo con el dato del parametro pasado al metodo (Se asume que la lista nunca estará vacía)
	 * */
	public Nodo buscarLista(Object dato) {
		Nodo nodoActual = primero;							// Iniciamos busqueda en el primer nodo de la lista enlazada simple
		
		while(!nodoActual.dato.equals(dato)) {				// Mientras no se encuentre nodo con el dato buscado
			if(nodoActual.siguiente == null) {				// Si llegamos al final de la lista enlazada simple
				return null;								// No se encontro el dato buscado
			}else {											// No es el final de la lista enlazada simple
				nodoActual = nodoActual.siguiente;
			}
		}
		
		return nodoActual;
	}
	
	/* Borrar nodo que contiene el objeto dato ingresado como parametros en el metodo
	 * */
	public Nodo eliminar(Object dato) {
		Nodo nodoActual = this.primero;						// Iniciamos nodoActual para que apunte al primer nodo de la lista
		Nodo nodoAnterior = this.primero;					// Iniciamos nodoAnterior para que apunte al primer nodo de la lista
		
		while(!nodoActual.dato.equals(dato)){				// Bucle itera mientras no se encuentre nodo con el valor de dato
			if(nodoActual.siguiente == null){				// Hemos llegado al final de la lista enlazada simple
				return null;								// No se encontro nodo con el valor del dato, el metodo retorna null
			}else {											// No hemos llegado al final de la lista enlazada simple
				nodoAnterior = nodoActual;					// Desplazamos la variable nodoAnterior al siguiente nodo
				nodoActual = nodoActual.siguiente;			// Desplazamos la variable nodoActual al siguiente nodo
			}
		}
		
		// Se encontro nodo a borrar
		if(nodoActual == this.primero) {					// Si nodo a borrar es el primero de la lista enlazada simple
			primero = primero.siguiente;					// Eliminamos el primer nodo
		} else {											// Si nodo a borrar no es el primer nodo de la lista enlazada simple
			nodoAnterior.siguiente = nodoActual.siguiente;	// Borramos el nodo referenciado por la variable nodoActual
		}
		
		return nodoActual;									// El metodo retorna el nodo Eliminado
	}
	
	/* Inserta nodo con parametro valorNuevo despues del nodo que contiene el valor del parametros valorExistente, metodo retorna valor insertado
	 * caso contrario de no poderse insertar el metodo retorna null (El metodo asume lista enlazada no vacía)
	 * */
	public Nodo insertar(Object valorExistente, Object valorNuevo) {
		Nodo nuevoNodo = new Nodo(valorNuevo);				// Creamos nuevo nodo con valorNuevo
		Nodo nodoActual = primero;							// Iniciamos busqueda del nodo con valorExistente en el primer nodo de la lista enlazada simple
		
		while(!nodoActual.dato.equals(valorExistente)){		// Bucle itera hasta encontrar nodo con valorExistente
			if(nodoActual.siguiente == null){				// Si llegamos al final de la lista enlazada
				return null;								// Metodo retorna null (No se encontro nodo con valorExistente)
			} else {										// Seguimos buscando nodo que contenga valorExistente
				nodoActual = nodoActual.siguiente;			// Nos movemos al siguiente nodo para seguir buscando
			}
		}
		
		nuevoNodo.siguiente = nodoActual.siguiente;			// Enlazamos el nuevo nodo con el nodo siguiente al nodo encontrado
		nodoActual.siguiente = nuevoNodo;					// Enlazamos nodo encontrado con nuevo nodo creado
		
		return nuevoNodo;									// Retornamos nuevo nodo insertado en la lista enlazada simple
	}
	
}