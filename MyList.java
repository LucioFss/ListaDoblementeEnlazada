public class MyList<T> {
    private Node<T> header;
    private Node<T> trailer;
    private int size;

    public MyList(){
        this.header = new Node<T>(null);
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
        Node<T> newNode = new Node<>(elem);

        Node<T> aux = getNode(index);

        newNode.next = aux;
        newNode.prev = aux.prev;

        aux.prev.next = newNode;
        aux.prev = newNode;
        
    }

    public Node<T> getNode(int index){
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

    public void printStringStart(){
        Node<T> aux = header;
        for(int i = 0; i <= size; i++){
            aux = aux.next;
            System.out.print("[" + aux.data + "]");
        }
    }

    public void printStringFinal(){
        Node<T> aux = trailer;
        for(int i = size; i >= 0; i--){
            aux = aux.prev;
            System.out.print("[" + aux.data + "]");
        }
    }
}
