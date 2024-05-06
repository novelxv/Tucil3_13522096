import java.util.Map; // debug

public  class UCS extends Algorithm {
    public UCS(WordGraph graph){
        super(graph);
    }

    @Override
    protected void startSetup(String startWord, String endWord){
        Node startNode = new Node(startWord, 0, 0);
        frontier.add(startNode);
        origin.put(startNode, null);
    }

    @Override
    protected void exploreNeighbors(Node current, String endWord){
        for (String neighbor : graph.getNeighbors(current.word)){
            // System.out.println("Visiting neighbor: " + neighbor); // debug
            int newPriority = current.priority + 1;
            Node neighborNode = convertStringToNode(neighbor);
            // Node originNode = origin.get(neighborNode);
            // System.out.println(originNode.word); // debug
            if (neighborNode == null){
                int insertionOrder = frontier.getCurrentInsertionOrder();
                Node newNode = new Node(neighbor, newPriority, insertionOrder);
                frontier.add(newNode);
                origin.put(newNode, current);
                System.out.println(current.word); // debug
                System.out.println(newNode.word); // debug
                // debug print origin
                // for (Map.Entry<Node, Node> entry : origin.entrySet()){
                //     System.out.println("Origin: " + entry.getKey().word + " -> " + entry.getValue().word);
                // }
            }
        }
    }
}
