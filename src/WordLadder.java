import java.io.*;

public class WordLadder {
    private WordGraph wordGraph = new WordGraph();

    /* Load words from a file */
    public void loadWords(String filename) throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null){
                System.out.println("Adding word: " + line); // debug
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
                    System.out.println("Adding edge between " + word1 + " and " + word2); // debug
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
}
