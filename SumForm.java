import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SumForm {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Sum Calculator");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        // Create labels and text fields
        JLabel labelA = new JLabel("A:");
        JTextField textA = new JTextField();
        JLabel labelB = new JLabel("B:");
        JTextField textB = new JTextField();

        // Button to calculate sum
        JButton sumButton = new JButton("Sum");

        // Label to show result
        JLabel resultLabel = new JLabel("Result: ");

        // Add action listener for button
        sumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int a = Integer.parseInt(textA.getText());
                    int b = Integer.parseInt(textB.getText());
                    int sum = a + b;
                    resultLabel.setText("Result: " + sum);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid numbers");
                }
            }
        });

        // Add components to frame
        frame.add(labelA);
        frame.add(textA);
        frame.add(labelB);
        frame.add(textB);
        frame.add(sumButton);
        frame.add(resultLabel);

        // Make frame visible
        frame.setVisible(true);
    }
}
