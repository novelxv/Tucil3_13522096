import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Algorithm {
    protected WordGraph graph;
    protected PrioQueueFIFO frontier;
    protected Set<String> visited = new HashSet<String>();
    protected INodeValue nodeValue;

    /* Constructor */
    public Algorithm(WordGraph graph, INodeValue nodeValue){
        this.graph = graph;
        this.nodeValue = nodeValue;
        frontier = new PrioQueueFIFO();
    }

    /* Setup the algorithm */
    protected void startSetup(String startWord, String endWord){
        int cost = 0;
        int heuristic = NodeValues.calculateHeuristic(startWord, endWord);
        int priority = nodeValue.calculateNodeValue(new Node(startWord, cost, heuristic, 0, 0, null));
        Node startNode = new Node(startWord, cost, heuristic, priority, 0, null);
        frontier.add(startNode);
    }

    /* Solve the algorithm */
    public List<String> solve(String startWord, String endWord){
        startSetup(startWord, endWord);
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            markVisited(current.word);
            if (current.word.equals(endWord)){
                return buildPath(current);
            }
            exploreNeighbors(current, endWord);
        }
        return Collections.emptyList();
    }

    /* Explore the neighbors of the current node */
    protected void exploreNeighbors(Node current, String endWord){
        for (String neighbor : graph.getNeighbors(current.word)){
            if (!hasVisited(neighbor)){
                int insertionOrder = frontier.getCurrentInsertionOrder();
                int newCost = current.cost + 1;
                int newHeuristic = NodeValues.calculateHeuristic(neighbor, endWord);
                int newPriority = nodeValue.calculateNodeValue(new Node(neighbor, newCost, newHeuristic, 0, insertionOrder, current));
                Node newNode = new Node(neighbor, newCost, newHeuristic, newPriority, insertionOrder, current);
                frontier.add(newNode);
            }
        }
    }    

    /* Build the path from the end node to the start node */
    protected List<String> buildPath(Node endNode){
        List<String> path = new ArrayList<String>();
        Node current = endNode;
        while (current != null){
            path.add(current.word);
            current = current.origin;
        }
        Collections.reverse(path);
        return path;
    }

    /* Check if a word has been visited */
    private boolean hasVisited(String word){
        return visited.contains(word);
    }

    /* Mark a word as visited */
    private void markVisited(String word){
        visited.add(word);
    }

    /* Get the number of visited nodes */
    public int getVisitedNodeCount(){
        return visited.size();
    }   
}
