public class Node<T>{
    protected T data;
    protected Node<T> next;
    protected Node<T> prev;

    public Node(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    @Override
    public String toString(){
        System.out.println("" + data.toString());
        return data.toString();
    }

}