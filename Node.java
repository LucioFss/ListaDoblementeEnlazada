public class Node<T>{
    protected T data;
    protected Node<T> next;
    protected Node<T> prev;

    public Node(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

}