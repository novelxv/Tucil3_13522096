public class Node {
    String word;
    int priority;
    int insertionOrder;

    public Node(String word, int priority, int insertionOrder){
        this.word = word;
        this.priority = priority;
        this.insertionOrder = insertionOrder;
    }
}