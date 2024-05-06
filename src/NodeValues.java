public class NodeValues {
    /* Class for calculating node values based on the algorithm */
    public static class UCSNodeValue implements INodeValue {
        @Override
        public int calculateNodeValue(Node node){
            return node.cost;
        }
    }

    public static class GBFSNodeValue implements INodeValue {
        @Override
        public int calculateNodeValue(Node node){
            return node.heuristic;
        }
    }

    public static class AStarNodeValue implements INodeValue {
        @Override
        public int calculateNodeValue(Node node){
            return node.cost + node.heuristic;
        }
    }

    public static int calculateHeuristic(String word1, String word2){
        int diffs = 0;
        for (int i = 0; i < word1.length(); i++){
            if (word1.charAt(i) != word2.charAt(i)){
                diffs++;
            }
        }
        return diffs;
    }
}
