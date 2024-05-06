import java.io.*;
import java.util.List;

public class WordLadder {
    private WordGraph wordGraph = new WordGraph();
    private Algorithm algorithm;

    /* Load words from a file */
    public void loadWords(String filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null){
                wordGraph.addWord(line);
            }
        } finally {
            if (reader != null){
                reader.close();
            }
        }
        // Add edges between words that differ by one character
        for (String word1 : wordGraph.getWords()){
            for (String word2 : wordGraph.getWords()){
                if (differByOne(word1, word2)){
                    wordGraph.addEdge(word1, word2);
                }
            }
        }
    }

    /* Check if two words differ by one character */
    private boolean differByOne(String word1, String word2){
        if (word1.length() != word2.length()){
            return false;
        }
        int diffCount = 0;
        for (int i = 0; i < word1.length(); i++){
            if (word1.charAt(i) != word2.charAt(i)){
                diffCount++;
            }
        }
        return diffCount == 1;
    }

    /* Get graph */
    public WordGraph getGraph(){
        return wordGraph;
    }

    /* Solve the Word Ladder */
    public List<String> solve(String startWord, String endWord, int algorithmChoice){
        switch (algorithmChoice){
            case 1:
                algorithm = new UCS(wordGraph);
                break;
            case 2:
                algorithm = new GBFS(wordGraph);
                break;
            case 3:
                algorithm = new AStar(wordGraph);
                break;
            default:
                throw new IllegalArgumentException("Invalid algorithm choice");
        }

        return algorithm.solve(startWord, endWord);
    }

    /* Get the number of visited nodes */
    public int getVisitedNodes(){
        return algorithm.getVisitedNodeCount();
    }
}