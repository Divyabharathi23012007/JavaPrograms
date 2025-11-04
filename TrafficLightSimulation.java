import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TrafficLightSimulation {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Traffic Light");
        frame.setSize(300, 250);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create radio buttons
        JRadioButton red = new JRadioButton("Red");
        JRadioButton yellow = new JRadioButton("Yellow");
        JRadioButton green = new JRadioButton("Green");

        red.setBounds(30, 30, 100, 20);
        yellow.setBounds(30, 60, 100, 20);
        green.setBounds(30, 90, 100, 20);

        // Group buttons
        ButtonGroup group = new ButtonGroup();
        group.add(red);
        group.add(yellow);
        group.add(green);

        // Label to show result
        JLabel label = new JLabel("Light is OFF");
        label.setBounds(30, 130, 200, 20);

        // Action listeners
        red.addActionListener(e -> label.setText("Light is RED"));
        yellow.addActionListener(e -> label.setText("Light is YELLOW"));
        green.addActionListener(e -> label.setText("Light is GREEN"));

        // Add components
        frame.add(red);
        frame.add(yellow);
        frame.add(green);
        frame.add(label);

        frame.setVisible(true);
    }
}