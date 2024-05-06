public class GBFS extends Algorithm {
    /* Ctor for GBFS */
    public GBFS(WordGraph graph) {
        super(graph, new NodeValues.GBFSNodeValue());
    }
}