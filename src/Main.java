import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args){
        WordLadder wordLadder = new WordLadder();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the length of the words you want to play (2-15 words): ");
        int length = scanner.nextInt();
        // word length validation
        while (length < 2 || length > 15){
            System.out.print("Invalid length. Please enter a length between 2 and 15: ");
            length = scanner.nextInt();
        }
        // load words from dictionary
        try {
            System.out.println("Loading dictionary...");
            wordLadder.loadWords("src/data/dictionary_" + length + ".txt");
        } catch (Exception e){
            System.out.println("Error loading words: " + e.getMessage());
            scanner.close();
            return;
        }
        System.out.println("Loaded dictionary");

        // input start and end word
        System.out.print("Enter the start word: ");
        String startWord = scanner.next();
        // word length and existence validation
        while (startWord.length() != length || !wordLadder.getGraph().getWords().contains(startWord)){
            System.out.print("Invalid word. Please enter a word with " + length + " characters that is in the dictionary: ");
            startWord = scanner.next();
        }
        System.out.print("Enter the end word: ");
        String endWord = scanner.next();
        // word length and existence validation
        while (endWord.length() != length || !wordLadder.getGraph().getWords().contains(endWord)){
            System.out.print("Invalid word. Please enter a word with " + length + " characters that is in the dictionary: ");
            endWord = scanner.next();
        }

        // choose algorithm
        System.out.println("Choose an algorithm:");
        System.out.println("1. Uniform Cost Search");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A* Search");
        System.out.print("Enter the number of the algorithm you want to use: ");
        int algorithmChoice = scanner.nextInt();

        scanner.close();

        // solve word ladder
        long startTime = System.currentTimeMillis();
        System.out.println("Solving...");
        List<String> path = wordLadder.solve(startWord, endWord, algorithmChoice);
        long endTime = System.currentTimeMillis();

        // print results
        if (path.isEmpty()){
            System.out.println("No path found");
        } else {
            System.out.println("Path found: " + path);
            System.out.println("Path length: " + (path.size() - 1) + " steps");
            System.out.println("Number of nodes visited: " + wordLadder.getVisitedNodes());
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
        }
    }
}
