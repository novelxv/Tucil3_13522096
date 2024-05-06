public class GBFS extends Algorithm {
    public GBFS(WordGraph graph){
        super(graph);
    }

    @Override
    protected void startSetup(String startWord, String endWord){
        int priority = AlgoUtils.calculateHeuristic(startWord, endWord);
        Node startNode = new Node(startWord, priority, 0);
        frontier.add(startNode);
        origin.put(startNode, null);
    }

    @Override
    protected void exploreNeighbors(Node current, String endWord){
        for (String neighbor : graph.getNeighbors(current.word)){
            int newPriority = AlgoUtils.calculateHeuristic(neighbor, endWord);
            Node neighborNode = convertStringToNode(neighbor);
            if (neighborNode == null){
                int insertionOrder = frontier.getCurrentInsertionOrder();
                Node newNode = new Node(neighbor, newPriority, insertionOrder++);
                frontier.add(newNode);
                origin.put(newNode, current);
            }
        }
    }
}
