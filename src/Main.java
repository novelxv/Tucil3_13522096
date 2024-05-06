import java.io.File;
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        WordLadder wordLadder = new WordLadder();
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the length of the words you want to play: ");
        int length = scanner.nextInt();

        try {
            System.out.println("Loading dictionary...");
            wordLadder.loadWords("src/data/dictionary_" + length + ".txt");
        } catch (Exception e){
            System.out.println("Error loading words: " + e.getMessage());
            scanner.close();
            return;
        }
        System.out.println("Loaded dictionary");

        System.out.print("Enter the start word: ");
        String startWord = scanner.next();
        System.out.print("Enter the end word: ");
        String endWord = scanner.next();

        System.out.println("Choose an algorithm:");
        System.out.println("1. Uniform Cost Search");
        System.out.println("2. Greedy Best First Search");
        System.out.println("3. A* Search");
        System.out.print("Enter the number of the algorithm you want to use: ");
        int algorithmChoice = scanner.nextInt();

        scanner.close();

        long startTime = System.currentTimeMillis();
        System.out.println("Solving...");
        List<String> path = wordLadder.solve(startWord, endWord, algorithmChoice);
        long endTime = System.currentTimeMillis();

        if (path.isEmpty()){
            System.out.println("No path found");
        } else {
            System.out.println("Path found: " + path);
            // System.out.println("Number of nodes visited: " + wordLadder.getVisitedNodes();
            System.out.println("Execution time: " + (endTime - startTime) + "ms");
        }
    }
}
