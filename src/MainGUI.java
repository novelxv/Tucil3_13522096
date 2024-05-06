import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainGUI extends JFrame {
    private JTextField startWordField, endWordField, lengthField;
    private JComboBox<String> algorithmSelector;
    private JTextArea resultArea;
    private WordLadder wordLadder;

    public MainGUI(){
        setTitle("Word Ladder Solver");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        initUI();
    }

    private void initUI(){
        // Styling constants
        Color bgColor = new Color(18, 18, 18);
        Color textColor = new Color(0, 255, 255);
        Font textFont = new Font("Monospaced", Font.BOLD, 16);

        // North panel for inputs
        JPanel northPanel = new JPanel();
        northPanel.setBackground(bgColor);
        northPanel.setLayout(new GridLayout(5, 2));

        northPanel.add(createLabel("Enter words' length (2-15):", textColor, textFont));
        lengthField = new JTextField();
        northPanel.add(lengthField);

        northPanel.add(createLabel("Enter start word:", textColor, textFont));
        startWordField = new JTextField();
        northPanel.add(startWordField);

        northPanel.add(createLabel("Enter end word:", textColor, textFont));
        endWordField = new JTextField();
        northPanel.add(endWordField);

        northPanel.add(createLabel("Select Algorithm:", textColor, textFont));
        String[] algorithms = {"Uniform Cost Search", "Greedy Best First Search", "A* Search"};
        algorithmSelector = new JComboBox<>(algorithms);
        northPanel.add(algorithmSelector);

        JButton solveButton = new JButton("Solve");
        solveButton.addActionListener(this::solveAction);
        northPanel.add(solveButton);

        add(northPanel, BorderLayout.NORTH);

        // Result area setup
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        resultArea.setForeground(textColor);
        resultArea.setBackground(bgColor);
        resultArea.setFont(textFont);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.CENTER);
    }

    private JLabel createLabel(String text, Color textColor, Font font){
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        label.setFont(font);
        return label;
    }

    private void solveAction(ActionEvent e){
        try {
            int length = Integer.parseInt(lengthField.getText().trim());
            if (length < 2 || length > 15){
                resultArea.setText("Invalid length. Please enter a length between 2 and 15.");
                return;
            }

            wordLadder = new WordLadder();
            wordLadder.loadWords("src/data/dictionary_" + length + ".txt");
            String startWord = startWordField.getText().trim();
            String endWord = endWordField.getText().trim();

            if (startWord.length() != length || !wordLadder.getGraph().getWords().contains(startWord) ||
                endWord.length() != length || !wordLadder.getGraph().getWords().contains(endWord)){
                resultArea.setText("Invalid words. Ensure both words are in the dictionary and of correct length.");
                return;
            }

            int algorithmChoice = algorithmSelector.getSelectedIndex() + 1;
            long startTime = System.currentTimeMillis();
            List<String> path = wordLadder.solve(startWord, endWord, algorithmChoice);
            long endTime = System.currentTimeMillis();

            if (path.isEmpty()){
                resultArea.setText("No path found.");
            } else {
                resultArea.setText("Path found: " + path + "\nPath length: " + (path.size() - 1) +
                    " steps\nNumber of nodes visited: " + wordLadder.getVisitedNodes() +
                    "\nExecution time: " + (endTime - startTime) + "ms");
            }
        } catch (NumberFormatException ex){
            resultArea.setText("Please ensure length is a valid number.");
        } catch (Exception ex){
            resultArea.setText("Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            new MainGUI().setVisible(true);
        });
    }
}
