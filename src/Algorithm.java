import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Algorithm {
    protected WordGraph graph;
    protected PrioQueueFIFO frontier;
    protected Map<Node, Node> origin = new HashMap<Node, Node>();

    public Algorithm(WordGraph graph){
        this.graph = graph;
        frontier = new PrioQueueFIFO();
    }

    protected abstract void startSetup(String startWord, String endWord);

    public List<String> solve(String startWord, String endWord){
        startSetup(startWord, endWord);
        while (!frontier.isEmpty()){
            Node current = frontier.poll();
            if (current.word.equals(endWord)){
                return buildPath(current);
            }
            exploreNeighbors(current, endWord);
        }
        return Collections.emptyList();
    }

    protected abstract void exploreNeighbors(Node current, String endWord);

    protected List<String> buildPath(Node endNode){
        List<String> path = new ArrayList<String>();
        Node current = endNode;
        while (current != null){
            path.add(current.word);
            current = origin.get(current);
        }
        Collections.reverse(path);
        return path;
    }

    public Node convertStringToNode(String word){
        for (Map.Entry<Node, Node> entry : origin.entrySet()){
            if (entry.getKey().word.equals(word)){
                return entry.getKey();
            }
        }
        return null;
    }
}
