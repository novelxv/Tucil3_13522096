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
    private Color darkColor = new Color(34, 34, 34); 
    private Color neonCyan = new Color(0, 255, 255);
    private Color neonPink = new Color(255, 16, 240);
    private Color neonPurple = new Color(148, 0, 211);
    private Color neonBlue = new Color(30, 144, 255);
    private Color neonYellow = new Color(255, 255, 0);

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
        JPanel lengthPanel = createStyledPanel();
        lengthPanel.add(createStyledLabel("Enter words' length (2-15):", neonBlue));
        lengthField = createStyledTextField(10, neonCyan);
        lengthPanel.add(lengthField);
        loadButton = createStyledButton("Load Dictionary", neonPurple);
        loadButton.addActionListener(this::loadDictionary);
        lengthPanel.add(loadButton);

        // Panel for the main solver
        JPanel solverPanel = createStyledPanel();
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.setBackground(darkColor);
        startWordField = createStyledTextField(10, neonPink);
        endWordField = createStyledTextField(10, neonPink);
        algorithmSelector = new JComboBox<>(new String[]{"Uniform Cost Search", "Greedy Best First Search", "A* Search"});
        styleComboBox(algorithmSelector, neonYellow);
        solveButton = createStyledButton("Solve", neonYellow);
        solveButton.addActionListener(this::solveAction);

        inputPanel.add(createStyledLabel("Start Word:", neonCyan));
        inputPanel.add(startWordField);
        inputPanel.add(createStyledLabel("End Word:", neonCyan));
        inputPanel.add(endWordField);
        inputPanel.add(createStyledLabel("Select Algorithm:", neonCyan));
        inputPanel.add(algorithmSelector);
        inputPanel.add(solveButton);

        resultArea = new JTextArea(25, 80);
        styleTextArea(resultArea, neonPink);
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

    private JPanel createStyledPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(darkColor);
        return panel;
    }

    private JLabel createStyledLabel(String text, Color textColor){
        JLabel label = new JLabel(text);
        label.setForeground(textColor);
        return label;
    }

    private JTextField createStyledTextField(int columns, Color textColor){
        JTextField textField = new JTextField(columns);
        textField.setForeground(textColor);
        textField.setBackground(darkColor);
        return textField;
    }

    private JButton createStyledButton(String text, Color textColor){
        JButton button = new JButton(text);
        button.setForeground(textColor);
        button.setBackground(darkColor);
        return button;
    }

    private void styleComboBox(JComboBox<String> comboBox, Color textColor){
        comboBox.setForeground(textColor);
        comboBox.setBackground(darkColor);
    }

    private void styleTextArea(JTextArea textArea, Color textColor){
        textArea.setForeground(textColor);
        textArea.setBackground(darkColor);
        textArea.setEditable(false);
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

