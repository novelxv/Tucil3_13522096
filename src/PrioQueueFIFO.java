import java.util.PriorityQueue;
import java.util.Comparator;

public class PrioQueueFIFO {
    private PriorityQueue<Node> queue;
    private int insertionOrder = 0;

    public PrioQueueFIFO() {
        queue = new PriorityQueue<Node>(new Comparator<Node>() {
            public int compare(Node n1, Node n2){
                int priorityComparison = Integer.compare(n1.priority, n2.priority);
                if (priorityComparison != 0){
                    return priorityComparison;
                }
                return Integer.compare(n1.insertionOrder, n2.insertionOrder);
            }
        });
    }

    public int getCurrentInsertionOrder(){
        return insertionOrder;
    }

    public void add(Node node){
        queue.add(new Node(node.word, node.priority, insertionOrder++));
    }

    public Node poll(){
        return queue.poll();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }   
}
