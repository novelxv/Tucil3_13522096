import java.util.Map;
import java.util.HashMap;

public class Astar extends Algorithm {
    private Map<Node, Integer> costSoFar = new HashMap<Node, Integer>();

    public Astar(WordGraph graph){
        super(graph);
    }

    @Override
    protected void startSetup(String startWord, String endWord){
        int priority = AlgoUtils.calculateHeuristic(startWord, endWord);
        Node startNode = new Node(startWord, priority, 0);
        frontier.add(startNode);
        origin.put(startNode, null);
        costSoFar.put(startNode, 0);
        System.out.println("COSTSTART " + costSoFar.get(startNode)); // debug
    }

    @Override
    protected void exploreNeighbors(Node current, String endWord){
        System.out.println("costsofar word " + current.word); // debug
        int currCost = costSoFar.get(current);
        for (String neighbor : graph.getNeighbors(current.word)){
            int newCost = currCost + 1;
            int newPriority = newCost + AlgoUtils.calculateHeuristic(neighbor, endWord);
            Node neighborNode = convertStringToNode(neighbor);
            // Node originNode = origin.get(neighborNode);
            // System.out.println(originNode.word); // debug
            if (neighborNode == null){
                int insertionOrder = frontier.getCurrentInsertionOrder();
                Node newNode = new Node(neighbor, newPriority, insertionOrder++);
                frontier.add(newNode);
                origin.put(newNode, current);
                costSoFar.put(newNode, newCost);
            }
        }
    }
}
