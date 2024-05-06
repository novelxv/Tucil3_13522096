public class AlgoUtils {
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
