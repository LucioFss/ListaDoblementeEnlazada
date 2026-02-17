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
