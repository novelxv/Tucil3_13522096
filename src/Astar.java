public class AStar extends Algorithm {
    /* Ctor for AStar */
    public AStar(WordGraph graph) {
        super(graph, new NodeValues.AStarNodeValue());
    }
}