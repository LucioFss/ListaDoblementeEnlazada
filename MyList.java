public class MyList<T> {
    private Node<T> header;
    private Node<T> trailer;
    private int size;

    public MyList(){
        this.header = new Node<T>(null);
        this.trailer = new Node<T>(null);
        this.size = 0;
        header.next = trailer;
        trailer.prev = header;
    }

    public getSize()
}
