public class Test {
    public static void main(String[] args) {
        MyList<Integer> listaD = new MyList<>();

        listaD.addStart(12);
        listaD.addStart(13);
        listaD.addStart(14);
        listaD.addStart(15);
        listaD.addStart(16);
        listaD.addStart(17);
        listaD.addStart(118);
        listaD.addStart(119);
        listaD.addPos(27, 5);
        listaD.printStringStart();
        listaD.printStringFinal();

        Node<Integer> num1 = new Node<Integer>(12);

        num1.toString();

        listaD.getNode(3).toString();
        listaD.getNode(0).toString();

        
    }
}
