import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ScientificCalculator extends JFrame implements ActionListener {
    JTextField display;
    String input = "";

    public ScientificCalculator() {
        setTitle("Scientific Calculator");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Display field
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setEditable(false);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 5;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(display, gbc);

        // Button labels
        String[] buttons = {
            "7", "8", "9", "/", "sin",
            "4", "5", "6", "*", "cos",
            "1", "2", "3", "-", "tan",
            "0", ".", "=", "+", "sqrt",
            "log", "ln", "^", "(", ")",
            "C"
        };

        int row = 1, col = 0;
        for (String label : buttons) {
            JButton btn = new JButton(label);
            btn.setFont(new Font("Arial", Font.PLAIN, 18));
            btn.addActionListener(this);

            gbc.gridx = col;
            gbc.gridy = row;
            gbc.gridwidth = 1;
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1;
            gbc.weighty = 1;
            add(btn, gbc);

            col++;
            if (col == 5) {
                col = 0;
                row++;
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "=":
                try {
                    double result = evaluate(input);
                    display.setText(String.valueOf(result));
                    input = "";
                } catch (Exception ex) {
                    display.setText("Error");
                    input = "";
                }
                break;
            case "C":
                input = "";
                display.setText("");
                break;
            default:
                input += cmd;
                display.setText(input);
        }
    }

    public double evaluate(String expr) throws Exception {
        expr = expr.toLowerCase()
                   .replace("sin", "Math.sin")
                   .replace("cos", "Math.cos")
                   .replace("tan", "Math.tan")
                   .replace("log", "Math.log10")
                   .replace("ln", "Math.log")
                   .replace("sqrt", "Math.sqrt");

        // Convert degrees to radians for trig functions
        expr = expr.replaceAll("Math\\.sin\\(([^)]+)\\)", "Math.sin(Math.toRadians($1))")
                   .replaceAll("Math\\.cos\\(([^)]+)\\)", "Math.cos(Math.toRadians($1))")
                   .replaceAll("Math\\.tan\\(([^)]+)\\)", "Math.tan(Math.toRadians($1))");

        return (double) new javax.script.ScriptEngineManager()
                .getEngineByName("JavaScript")
                .eval(expr);
    }

    public static void main(String[] args) {
        new ScientificCalculator();
    }
}