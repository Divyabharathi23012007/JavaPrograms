import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class QuizApp {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private long startTime;
    private int totalTimeTaken = 0;
    private ArrayList<String> userAnswers = new ArrayList<>();
    private ArrayList<Integer> marksEarned = new ArrayList<>();
    private JTextArea summary;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizApp().createUI());
    }

    private void createUI() {
        frame = new JFrame("Quiz Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        loadPanels();

        frame.add(cardPanel);
        frame.setVisible(true);

        cardLayout.show(cardPanel, "Q1");
    }

    private void loadPanels() {
        cardPanel.removeAll();
        cardPanel.add(createQuestion1Panel(), "Q1");
        cardPanel.add(createQuestion2Panel(), "Q2");
        cardPanel.add(createResultPanel(), "Result");
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    private JPanel createQuestion1Panel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel questionLabel = new JLabel("What is the capital of India?");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.NORTH);

        String[] options = {"Chennai", "Kolkata", "New Delhi", "Trivandrum"};
        ButtonGroup group = new ButtonGroup();
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        JRadioButton[] radioButtons = new JRadioButton[options.length];

        for (int i = 0; i < options.length; i++) {
            radioButtons[i] = new JRadioButton(options[i]);
            group.add(radioButtons[i]);
            optionsPanel.add(radioButtons[i]);
        }

        panel.add(optionsPanel, BorderLayout.CENTER);

        JLabel timerLabel = new JLabel("Time left: 60s", SwingConstants.RIGHT);
        panel.add(timerLabel, BorderLayout.EAST);

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, BorderLayout.SOUTH);

        startVisibleTimer(timerLabel, "Q2");

        submitBtn.addActionListener(e -> {
            for (JRadioButton rb : radioButtons) {
                if (rb.isSelected()) {
                    String answer = rb.getText();
                    userAnswers.add(answer);
                    boolean isCorrect = answer.equals("New Delhi");
                    int mark = isCorrect ? 2 : 0;
                    marksEarned.add(mark);
                    showFeedback(isCorrect, "New Delhi", mark, "Q2");
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Please select an option.");
        });

        return panel;
    }

    private JPanel createQuestion2Panel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel questionLabel = new JLabel("List the crops available in all seasons:");
        questionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.NORTH);

        String[] options = {"Rice", "Maize", "Jute", "Ragi"};
        JCheckBox[] checkBoxes = new JCheckBox[options.length];
        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));

        for (int i = 0; i < options.length; i++) {
            checkBoxes[i] = new JCheckBox(options[i]);
            optionsPanel.add(checkBoxes[i]);
        }

        panel.add(optionsPanel, BorderLayout.CENTER);

        JLabel timerLabel = new JLabel("Time left: 60s", SwingConstants.RIGHT);
        panel.add(timerLabel, BorderLayout.EAST);

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, BorderLayout.SOUTH);

        startVisibleTimer(timerLabel, "Result");

        submitBtn.addActionListener(e -> {
            ArrayList<String> selected = new ArrayList<>();
            for (JCheckBox cb : checkBoxes) {
                if (cb.isSelected()) selected.add(cb.getText());
            }

            String answer = String.join(", ", selected);
            userAnswers.add(answer);

            boolean hasRice = selected.contains("Rice");
            boolean hasMaize = selected.contains("Maize");

            int mark = 0;
            if (hasRice && hasMaize) {
                mark = 2;
            } else if (hasRice || hasMaize) {
                mark = 1;
            }

            marksEarned.add(mark);
            boolean isCorrect = mark > 0;
            showFeedback(isCorrect, "Rice, Maize", mark, "Result");
        });

        return panel;
    }

    private JPanel createResultPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel resultLabel = new JLabel("Evaluation Summary");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(resultLabel, BorderLayout.NORTH);

        summary = new JTextArea();
        summary.setEditable(false);
        panel.add(new JScrollPane(summary), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton restartBtn = new JButton("Start New Game");
        JButton closeBtn = new JButton("Close");
        buttonPanel.add(restartBtn);
        buttonPanel.add(closeBtn);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        closeBtn.addActionListener(e -> System.exit(0));

        restartBtn.addActionListener(e -> {
            userAnswers.clear();
            marksEarned.clear();
            totalTimeTaken = 0;
            summary.setText("");
            loadPanels(); // recreate panels and timers
            cardLayout.show(cardPanel, "Q1");
        });

        return panel;
    }

    private void updateResultSummary() {
        summary.setText("Your Answers:\n");

        String ans1 = userAnswers.size() > 0 ? userAnswers.get(0) : "Not answered";
        String ans2 = userAnswers.size() > 1 ? userAnswers.get(1) : "Not answered";
        int mark1 = marksEarned.size() > 0 ? marksEarned.get(0) : 0;
        int mark2 = marksEarned.size() > 1 ? marksEarned.get(1) : 0;

        summary.append("1. Capital of India: " + ans1 + "\n");
        summary.append("   → " + (ans1.equals("New Delhi") ? "Correct ✅" : "Incorrect ❌ (Correct: New Delhi)") + "\n");
        summary.append("   Marks: " + mark1 + "\n\n");

        summary.append("2. Crops in all seasons: " + ans2 + "\n");
        summary.append("   → " + (mark2 == 2 ? "Fully Correct ✅" : mark2 == 1 ? "Partially Correct ⚠️" : "Incorrect ❌ (Correct: Rice, Maize)") + "\n");
        summary.append("   Marks: " + mark2 + "\n\n");

        summary.append("Total Marks: " + (mark1 + mark2) + " / 4\n");
        summary.append("Total Time Taken: " + totalTimeTaken + " seconds");
    }

    private void startVisibleTimer(JLabel timerLabel, String nextCard) {
        startTime = System.currentTimeMillis();
        final int[] timeLeft = {60};
        timerLabel.setText("Time left: 60s");

        Timer countdown = new Timer(1000, null);
        countdown.addActionListener(e -> {
            timeLeft[0]--;
            timerLabel.setText("Time left: " + timeLeft[0] + "s");
            if (timeLeft[0] <= 0) {
                countdown.stop();
                totalTimeTaken += 60;
                if (nextCard.equals("Result")) updateResultSummary();
                cardLayout.show(cardPanel, nextCard);
            }
        });
        countdown.start();
    }

    private void showFeedback(boolean isCorrect, String correctAnswer, int mark, String nextCard) {
        totalTimeTaken += (System.currentTimeMillis() - startTime) / 1000;
        String message = isCorrect
                ? "Correct ✅ (Marks: " + mark + ")"
                : "Incorrect ❌ (Marks: 0)\nCorrect Answer: " + correctAnswer;
        JOptionPane.showMessageDialog(frame, message);
        if (nextCard.equals("Result")) updateResultSummary();
        cardLayout.show(cardPanel, nextCard);
    }
}