import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class QuizAppDB {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private ArrayList<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private Connection conn;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new QuizAppDB().startApp());
    }

    private void startApp() {
        connectToDatabase();
        loadQuestionsFromDB();
        createUI();
    }

    private void connectToDatabase() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/quizdb", "root", "Maha246@");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database connection failed: " + e.getMessage());
            System.exit(1);
        }
    }

    private void loadQuestionsFromDB() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM questions")) {
            while (rs.next()) {
                questions.add(new Question(
                    rs.getInt("id"),
                    rs.getString("question_text"),
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4"),
                    rs.getString("correct_answer"),
                    rs.getBoolean("is_multiselect")
                ));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Failed to load questions: " + e.getMessage());
        }
    }

    private void createUI() {
        frame = new JFrame("Database Quiz");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(createStartPanel(), "Start");
        frame.add(cardPanel);
        frame.setVisible(true);
    }

    private JPanel createStartPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel welcome = new JLabel("Welcome to the Quiz!", SwingConstants.CENTER);
        JButton startBtn = new JButton("Start");

        startBtn.addActionListener(e -> {
            currentQuestionIndex = 0;
            showQuestionPanel();
        });

        panel.add(welcome, BorderLayout.CENTER);
        panel.add(startBtn, BorderLayout.SOUTH);
        return panel;
    }

    private void showQuestionPanel() {
        if (currentQuestionIndex >= questions.size()) {
            cardPanel.add(createThankYouPanel(), "ThankYou");
            cardLayout.show(cardPanel, "ThankYou");
            return;
        }

        Question q = questions.get(currentQuestionIndex);
        JPanel panel = new JPanel(new BorderLayout());

        JLabel questionLabel = new JLabel(q.text, SwingConstants.CENTER);
        panel.add(questionLabel, BorderLayout.NORTH);

        JPanel optionsPanel = new JPanel(new GridLayout(4, 1));
        JComponent[] optionComponents = new JComponent[4];
        String[] optionTexts = {q.option1, q.option2, q.option3, q.option4};

        for (int i = 0; i < 4; i++) {
            if (q.isMultiselect) {
                optionComponents[i] = new JCheckBox(optionTexts[i]);
            } else {
                optionComponents[i] = new JRadioButton(optionTexts[i]);
            }
            optionsPanel.add(optionComponents[i]);
        }

        if (!q.isMultiselect) {
            ButtonGroup group = new ButtonGroup();
            for (JComponent comp : optionComponents) {
                group.add((JRadioButton) comp);
            }
        }

        panel.add(optionsPanel, BorderLayout.CENTER);

        JLabel timerLabel = new JLabel("Time left: 60s", SwingConstants.RIGHT);
        panel.add(timerLabel, BorderLayout.EAST);

        JButton submitBtn = new JButton("Submit");
        panel.add(submitBtn, BorderLayout.SOUTH);

        startVisibleTimer(timerLabel, () -> {
            currentQuestionIndex++;
            showQuestionPanel();
        });

        submitBtn.addActionListener(e -> {
            ArrayList<String> selectedAnswers = new ArrayList<>();
            for (JComponent comp : optionComponents) {
                if (q.isMultiselect && comp instanceof JCheckBox && ((JCheckBox) comp).isSelected()) {
                    selectedAnswers.add(((JCheckBox) comp).getText());
                } else if (!q.isMultiselect && comp instanceof JRadioButton && ((JRadioButton) comp).isSelected()) {
                    selectedAnswers.add(((JRadioButton) comp).getText());
                }
            }

            if (selectedAnswers.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please select an option.");
                return;
            }

            for (String answer : selectedAnswers) {
                submitAnswerToDB(q.id, answer);
            }

            currentQuestionIndex++;
            showQuestionPanel();
        });

        cardPanel.add(panel, "Q" + currentQuestionIndex);
        cardLayout.show(cardPanel, "Q" + currentQuestionIndex);
    }

    private void submitAnswerToDB(int questionId, String selectedAnswer) {
        try (CallableStatement stmt = conn.prepareCall("{call evaluate_response(?, ?)}")) {
            stmt.setInt(1, questionId);
            stmt.setString(2, selectedAnswer);
            stmt.execute();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "Failed to submit answer: " + e.getMessage());
        }
    }

    private JPanel createThankYouPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel thankYou = new JLabel("Thank you for completing the quiz!", SwingConstants.CENTER);
        panel.add(thankYou, BorderLayout.CENTER);
        return panel;
    }

    private void startVisibleTimer(JLabel timerLabel, Runnable onTimeout) {
        final int[] timeLeft = {60};
        timerLabel.setText("Time left: 60s");

        Timer countdown = new Timer(1000, null);
        countdown.addActionListener(e -> {
            timeLeft[0]--;
            timerLabel.setText("Time left: " + timeLeft[0] + "s");
            if (timeLeft[0] <= 0) {
                countdown.stop();
                onTimeout.run();
            }
        });
        countdown.start();
    }

    class Question {
        int id;
        String text, option1, option2, option3, option4, correct;
        boolean isMultiselect;

        Question(int id, String text, String o1, String o2, String o3, String o4, String correct, boolean isMultiselect) {
            this.id = id;
            this.text = text;
            this.option1 = o1;
            this.option2 = o2;
            this.option3 = o3;
            this.option4 = o4;
            this.correct = correct;
            this.isMultiselect = isMultiselect;
        }
    }
}