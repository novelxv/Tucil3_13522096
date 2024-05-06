public class UCS extends Algorithm {
    /* Ctor for UCS */
    public UCS(WordGraph graph) {
        super(graph, new NodeValues.UCSNodeValue());
    }
}