import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class MainGUI extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private JTextField lengthField, startWordField, endWordField;
    private JComboBox<String> algorithmSelector;
    private JTextArea resultArea;
    private WordLadder wordLadder;
    private JButton loadButton, solveButton;

    public MainGUI(){
        setTitle("Word Ladder Solver");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
        add(cardPanel, BorderLayout.CENTER);
    }

    private void initUI(){
        // Panel for word length input
        JPanel lengthPanel = new JPanel();
        lengthPanel.add(new JLabel("Enter words' length (2-15):"));
        lengthField = new JTextField(10);
        lengthPanel.add(lengthField);
        loadButton = new JButton("Load Dictionary");
        loadButton.addActionListener(this::loadDictionary);
        lengthPanel.add(loadButton);

        // Panel for the main solver
        JPanel solverPanel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        startWordField = new JTextField(10);
        endWordField = new JTextField(10);
        algorithmSelector = new JComboBox<>(new String[]{"Uniform Cost Search", "Greedy Best First Search", "A* Search"});
        solveButton = new JButton("Solve");
        solveButton.addActionListener(this::solveAction);

        inputPanel.add(new JLabel("Start Word:"));
        inputPanel.add(startWordField);
        inputPanel.add(new JLabel("End Word:"));
        inputPanel.add(endWordField);
        inputPanel.add(new JLabel("Select Algorithm:"));
        inputPanel.add(algorithmSelector);
        inputPanel.add(solveButton);

        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        solverPanel.add(inputPanel, BorderLayout.NORTH);
        solverPanel.add(scrollPane, BorderLayout.CENTER);

        // Add panels to card layout
        cardPanel.add(lengthPanel, "Length");
        cardPanel.add(solverPanel, "Solver");
        cardLayout.show(cardPanel, "Length");

        // Initially disable input fields and solve button
        toggleInputs(false);
    }

    private void loadDictionary(ActionEvent e){
        try {
            int length = Integer.parseInt(lengthField.getText().trim());
            if (length < 2 || length > 15) {
                JOptionPane.showMessageDialog(this, "Invalid length. Please enter a length between 2 and 15.");
                return;
            }
            // Simulate dictionary loading
            wordLadder = new WordLadder(); 
            wordLadder.loadWords("src/data/dictionary_" + length + ".txt"); 
            cardLayout.show(cardPanel, "Solver");
            toggleInputs(true);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please ensure the length is a valid number.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading dictionary: " + ex.getMessage());
        }
    }

    private void solveAction(ActionEvent e){
        try {
            String startWord = startWordField.getText().trim().toUpperCase();
            String endWord = endWordField.getText().trim().toUpperCase();
            int algorithmChoice = algorithmSelector.getSelectedIndex() + 1;
            long startTime = System.currentTimeMillis();
            List<String> path = wordLadder.solve(startWord, endWord, algorithmChoice);
            long endTime = System.currentTimeMillis();
            if (path.isEmpty()) {
                resultArea.setText("No path found.");
            } else {
                resultArea.setText("Path found: " + path + "\nPath length: " + (path.size() - 1) +
                        " steps\nNumber of nodes visited: " + wordLadder.getVisitedNodes() +
                        "\nExecution time: " + (endTime - startTime) + "ms");
            }
        } catch (Exception ex) {
            resultArea.setText("Error during solving: " + ex.getMessage());
        }
    }

    private void toggleInputs(boolean enable){
        startWordField.setEnabled(enable);
        endWordField.setEnabled(enable);
        algorithmSelector.setEnabled(enable);
        solveButton.setEnabled(enable);
    }

    public static void main(String[] args){
        EventQueue.invokeLater(() -> new MainGUI().setVisible(true));
    }
}

