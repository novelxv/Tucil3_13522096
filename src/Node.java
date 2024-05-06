public class Node {
    String word;
    int cost;
    int heuristic;
    int priority;
    int insertionOrder;
    Node origin;

    /* Constructor */
    public Node(String word, int cost, int heuristic, int priority, int insertionOrder, Node origin){
        this.word = word;
        this.cost = cost;
        this.heuristic = heuristic;
        this.priority = priority;
        this.insertionOrder = insertionOrder;
        this.origin = origin;
    }
}