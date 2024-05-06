public  class UCS extends Algorithm {
    public UCS(WordGraph graph){
        super(graph);
    }

    protected void startSetup(String startWord, String endWord){
        Node startNode = new Node(startWord, 0, 0);
        frontier.add(startNode);
        origin.put(startNode, null);
    }

    @Override
    protected void exploreNeighbors(Node current, String endWord){
        for (String neighbor : graph.getNeighbors(current.word)){
            int newPriority = current.priority + 1;
            Node neighborNode = convertStringToNode(neighbor);
            if (neighborNode == null || newPriority < origin.get(neighborNode).priority){
                int insertionOrder = frontier.getCurrentInsertionOrder();
                Node newNode = new Node(neighbor, newPriority, insertionOrder);
                frontier.add(newNode);
                origin.put(newNode, current);
            }
        }
    }
}
