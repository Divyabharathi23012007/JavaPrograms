import javax.swing.*;
import java.awt.event.*;

public class ComboBox {
    public static void main(String[] args) {
        JFrame f = new JFrame("ComboBox Action");
        String[] items = {"Java", "Python", "C++"};
        JComboBox<String> cb = new JComboBox<>(items);
        JLabel label = new JLabel("Select a language");

        cb.setBounds(50, 50, 100, 20);
        label.setBounds(50, 90, 200, 20);

        // Add action listener to combo box
        cb.addActionListener(e -> {
            String selected = (String) cb.getSelectedItem();
            label.setText("You selected: " + selected);
        });

        f.add(cb);
        f.add(label);
        f.setLayout(null);
        f.setSize(250, 200);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}