public class MyList<T> {
    //Cabecera centinela de la lista
    private final Node<T> header;
    //Cola centinela de la lista
    private final Node<T> trailer;
    //Cantidad de elementos de la lista
    private int size;

    /**
     * Metodo constructor en los que se incializan los centinelas y se enlaza,
     * ademas inicializar la cantidad de elementos en 0
     */
    public MyList(){
        this.header = new Node<>(null);
        this.trailer = new Node<>(null);
        this.size = 0;
        header.next = trailer;
        trailer.prev = header;
    }

    /**
     * Se retorna la cantida de elementos actuales en la lista
     * @return cantidad de elementos de la lista
     */
    public int size(){
        return this.size;
    }

    /**
     * Se retorna si es verdadero que la lista esta vacia
     */
    public boolean isEmpty(){
        return this.size == 0;
    }

    /**
     * Se agrega un elemento en la primera posición de la lista
     * @param elem elemento que se guardara en la lista
     */
    public void addStart(T elem){
        //Se crea el nuevo nodo a agregar y se guarda su contenido
        Node<T> newNode = new Node<>(elem);

        //El siguiente al nodo sentinela a la cabeza es el nuevo nodo 
        header.next.prev = newNode;
        //El siguiente al nuevo nodo es ahora el que era el siguiente al nodo sentinela a la cabeza
        newNode.next = header.next;

        //El anterior al nuevo nodo es el nodo sentinela a la cabeza
        newNode.prev = header;
        //El siguiente al nodo sentinela a la cabeza es el nuevo nodo
        header.next = newNode;

        //Se increnta la cantidad de elementos de la lsita   
        size++;
        
    }

    /**
     * Se agrega un elemento al final de la lista
     * @param elem elemento a agregar
     */
    public void addFinal(T elem){
        //Se incializa el nodo que se añadira y se establece el elemento que almacena
        Node<T> newNode = new Node<>(elem);
        
        //El siguiente a nuestro nuevo nodo es el sentinela en la cola
        newNode.next = trailer;
        //El previo al nuevo nodo es el que el sentinela a la cola poseeia
        newNode.prev = trailer.prev;

        //El siguiente del nodo que estaba antes del sentinela a la cola ahora es el nuevo nodo, dejando de apuntar al sentinela
        trailer.prev.next = newNode;
        //El previo al sentinela a la cola ahora es el nuevo nodo
        trailer.prev = newNode;

        //Se incrementa la cantidad de elementos en la lista
        size++;
    }
    /**
     * Se añade un elemento en una posición especifica del la lista
     */
    public void addPos(T elem, int index){
        //Nuevo nodo a agregar con su contenido
        Node<T> newNode = new Node<>(elem);

        //Nodo auxiliar que posee el nodo el cual pasara a estar adelante nuestro nuevo nodo
        Node<T> aux = getNode(index);

        //El siguiente a nuestro nodo es el que estaba en la poscion moviendose 1 posicion adelante
        newNode.next = aux;
        //El anterior a nuestro nodo es el anterior al que ya estaba en la posición por lo que no se desplaza
        newNode.prev = aux.prev;

        //el nodo anterior al que estaba en la posción ahora su siguiente es nuestro nuevo nodo
        aux.prev.next = newNode;
        //El nodo que estaba en la posción y se desplazo, ahora tiene como previo el nuevo nodo
        aux.prev = newNode;

        size++;
    }
    /**
     * Auxiliar que se encarga de buscar y retornar un nodo de la lista en una posición en especifico
     * @param index posición del nodo buscado
     * @return Nodo buscado
     */
    private Node<T> getNode(int index){
        //Precondicion que valida si la posición ingresada es valida
        if(index > size || index < 0)
            throw new IllegalArgumentException("posición invalida");
        //Nodo auxiliar para recorrer la lista
        Node<T> aux;

        //Planteamos dos casos, uno en el que se comienza desde el principio y otro desde el final
        if(index < size/2){
            //Se comienza desde el primero real, ya que header no cuenta como un elemento en la lista
            aux = header.next;
            //iteración hasta el indice indicado y se guarda el nodo en esa posción
            for(int i = 0; i < index; i++){
                aux = aux.next;
            }
        }else{
            //Inciamos desde el ultimo real, ya que trailer no cuenta como un elemento en la lista
            aux = trailer.prev;
            //Iteración desde atras hasta llegar al indice indicado y guarda el nodo en esa posición
            for(int i = size-1; i > index;i--){
                aux = aux.prev;
            }
        }
        //Se retorna el nodo buscado
        return aux;
    }

    /**
     * Metodo que busca y retorna un elemento de la lista en una posición en especifico 
     * @param index indice de el elemento buscado
     * @return elemento buscado
     */
    public T get(int index){
        //Se busca el nodo y se extrae su contenido y se guarda
        T elem = getNode(index).data;

        //Se retorna el elemento
        return elem;
    }

    /**
     * Meotodo que remueve el primer elemento de la lista
     * @return elemento eliminado
     */
    public T removeStart(){
        if(size == 0){
            throw new IllegalStateException("La lista esta vacia");
        }
        //Nodo que sera eliminado
        Node<T> toRemove = header.next;

        Node<T> newFirst = toRemove.next;
        //El contenido de el nodo para ser retornado luego
        T elem = toRemove.data;

        //El siguiente a la cabeza sentila ahora es el sucesosr al nodo a eliminar
        header.next = newFirst;
        //El nodo previo al nodo sucesor de el eliminado es ahora el nodo a la cabeza centinela
        newFirst.prev = header;

        //Se desconceta en nodo de su previo y siguiente para que garbage collector lo elimine automaticamente
        toRemove.next = null;
        toRemove.prev = null;

        //Se decrementa la cantidad de elementos en la lista
        size--;

        //Se retorna el contenido del nodo eliminado
        return elem;

    }

    /**
     * Metodo que elimina el ultimo elemento de la lista y lo retorna
     * @return elemento eliminado
     */
    public T removeFinal(){
        if(size == 0){
            throw new IllegalStateException("La lista esta vacia");
        }

        //Nodo a eliminar
        Node<T> toRemove = trailer.prev;

        //El nodo que pasara a ser el ultimo elemento
        Node<T> newLast = toRemove.prev;

        //Se guarda el contenido de el nodo a eliminar para luego retornarlo
        T elem = toRemove.data;

        //El nuevo nodo final se conecta con el centinela, reemplazando al anterior
        newLast.next = trailer;
        //El nuevo nodo previo al centinela de la cola es el nuevo nodo final
        trailer.prev = newLast;

        //Se decrementa la cantidad de elementos en la lista
        size--;
        
        //Se retorna el contenido de el nodo eliminado
        return elem;
    }

    /**
     * Metodo que elimina y retorna un elemento de la lista en una posición en es especifico
     * @param index indice de el elemento a eliminar
     * @return elemento eliminado
     */
    public T removePos(int index){
        //Nodo que sera eliminado
        Node<T> toRemove = getNode(index);
        //El nodo siguiente al nodo eliminado
        Node<T> successor = toRemove.next;
        //El nodo previo al nodo eliminado
        Node<T> predecessor = toRemove.prev;
        
        //Se guarda el contenido de el nodo a eliminar para luego retornarlo
        T elem = toRemove.data;

        //El nodo posterior y el anterior se conectan entre si
        predecessor.next = successor;
        successor.prev = predecessor;

        //El nodo a eliminar se desconecta de su previo y siguiente para ser eliminado por el garbage collector
        toRemove.next = null;
        toRemove.prev = null;

        //Se decrementa la cantidad de elementos de la lista
        size--;

        //Se retorna el contenido de el nodo elimiando;
        return elem;
    }

    /**
     * Meotodo que imprime en consola todos los elementos de la lista en fila, del primer al ultimo elemento
     */
    public void printStringStart(){
        //Se incializa el nodo que nos ayudara a recorrer la lista 
        Node<T> aux = header;

        //Se imprime el nodo sentinela a la cabeza
        System.out.print("[" + aux.data + "]");
        //Iteracion por todos los elementos de la lista e imprimiendolos unos al lado del otro

        for(int i = 0; i < size; i++){
            //El nodo actual es ahora su siguiente
            aux = aux.next;
            System.out.print("[" + aux.data + "]");
        }

        //Se imprime el nodo sentinela al final
        System.out.print("[" + null + "]\n");
    }

    /**
     * Metodo que imprime en consola todos los elementos de la lista en fila, del ultimo al primer elemento de la lista
     */
    public void printStringFinal(){
        //Se incializa el nodo que nos ayudara a recorrer la lista 
        Node<T> aux = trailer;

        //Se imprime el nodo sentinela a la cola
        System.out.print("[" + null + "]");

        //Iteración desde el ultimo elemento hacia atras, imprimiendo uno a uno
        for(int i = size-1; i >= 0; i--){
            //El nodo actual es su nodo anterior
            aux = aux.prev;
            System.out.print("[" + aux.data + "]");
            
        }
        //Salto de linea
        System.out.print("\n");
    }
}
