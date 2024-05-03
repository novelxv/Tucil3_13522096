import java.util.*;

public class WordGraph {
    private Map<String, Set<String>> graph = new HashMap();

    /* Add a word to the graph */
    public void addWord(String word){
        graph.putIfAbsent(word, new HashSet());
    }

    /* Add an edge between two words */
    public void addEdge(String word1, String word2){
        graph.get(word1).add(word2);
        graph.get(word2).add(word1);
    }

    /* Get the neighbors of a word */
    public Set<String> getNeighbors(String word){
        return graph.getOrDefault(word, Collections.emptySet());
    }
}