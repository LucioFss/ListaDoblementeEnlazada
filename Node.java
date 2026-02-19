public class Node<T>{
    //Campo que almacena un valor generico
    protected T data;
    //Enlace con un nodo siguiente
    protected Node<T> next;
    //Enlace con un nodo previo
    protected Node<T> prev;

    /**
     * Constructor de el nodo. Se debe ingresar el valor que se guardara en el nodo
     * @param data valor de tipo generico que almacenara el nodo
     */
    public Node(T data){
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    /**
     * Metodo que sobreescribe el toString normal, para que al ejercutarlo se imprima el contenido
     * del nodo de forma especifica, mas concetamente el valor que almacena
     */
    @Override
    public String toString(){
        System.out.println("" + data.toString());
        return data.toString();
    }

}