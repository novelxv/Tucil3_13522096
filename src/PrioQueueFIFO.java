import java.util.PriorityQueue;
import java.util.Comparator;

public class PrioQueueFIFO {
    private PriorityQueue<Node> queue;
    private int insertionOrder = 0;

    /* Priority queue with FIFO ordering */
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

    /* Get the current insertion order */
    public int getCurrentInsertionOrder(){
        return insertionOrder;
    }

    /* Add a node to the queue */
    public void add(Node node){
        queue.add(new Node(node.word, node.cost, node.heuristic, node.priority, insertionOrder++, node.origin));
    }

    /* Poll the node with the highest priority */
    public Node poll(){
        return queue.poll();
    }

    /* Check if the queue is empty */
    public boolean isEmpty(){
        return queue.isEmpty();
    }   
}
