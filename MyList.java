public class MyList<T> {
    private final Node<T> header;
    private final Node<T> trailer;
    private int size;

    public MyList(){
        this.header = new Node<>(null);
        this.trailer = new Node<>(null);
        this.size = 0;
        header.next = trailer;
        trailer.prev = header;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void addStart(T elem){
        Node<T> newNode = new Node<>(elem);

        header.next.prev = newNode;
        newNode.next = header.next;

        newNode.prev = header;
        header.next = newNode;

        size++;
        
    }

    public void addFinal(T elem){
        Node<T> newNode = new Node<>(elem);
        
        newNode.next = trailer;
        newNode.prev = trailer.prev;

        trailer.prev.next = newNode;
        trailer.prev = newNode;

        size++;
    }

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

    private Node<T> getNode(int index){
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

    public T removeStart(){
        //Nodo que sera eliminado
        Node<T> toRemove = header.next;

        //El contenido de el nodo para ser retornado luego
        T elem = toRemove.data;

        //El siguiente a la cabeza sentila ahora es el sucesosr al nodo a eliminar
        header.next = toRemove.next;
        //El nodo previo al nodo sucesor de el eliminado es ahora el nodo a la cabeza centinela
        toRemove.next.prev = header;

        //Se desconceta en nodo de su previo y siguiente para que garbage collector lo elimine automaticamente
        toRemove.next = null;
        toRemove.prev = null;

        //Se decrementa la cantidad de elementos en la lista
        size--;

        //Se retorna el contenido del nodo eliminado
        return elem;

    }

    public T removeFinal(){
        Node<T> aux = trailer.prev;
        T elem = aux.data;

        trailer.prev = trailer.prev.prev;
        trailer.prev.next = trailer;

        aux.prev = null;
        aux.next = null;

        size--;
        
        return elem;
    }

    public T removePos(int index){
        Node<T> current = getNode(index);
        Node<T> aux = current.prev;

        T elem = current.data;

        current.next.prev = aux;
        aux.next = current.next;

        current.next = null;
        current.prev = null;
        
        size--;
        return elem;
    }

    public void printStringStart(){
        Node<T> aux = header;
        for(int i = 0; i < size; i++){
            aux = aux.next;
            System.out.print("[" + aux.data + "]");
        }

        System.out.print("[" + null + "]\n");
    }

    public void printStringFinal(){
        Node<T> aux = trailer;
        System.out.print("[" + null + "]");
        for(int i = size-1; i >= 0; i--){
            aux = aux.prev;
            System.out.print("[" + aux.data + "]");
            
        }
        System.out.print("\n");
    }
}
