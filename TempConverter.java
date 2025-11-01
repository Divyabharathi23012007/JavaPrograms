import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TempConverter extends JFrame implements ActionListener {

    JTextField inputField;
    JLabel resultLabel;
    JButton toCelsiusBtn, toFahrenheitBtn;

    public TempConverter() {
        setTitle("Temperature Converter");
        setSize(350, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Temperature:"));

        inputField = new JTextField(10);
        add(inputField);

        toCelsiusBtn = new JButton("Convert to Celsius");
        toFahrenheitBtn = new JButton("Convert to Fahrenheit");

        add(toCelsiusBtn);
        add(toFahrenheitBtn);

        resultLabel = new JLabel("Result: ");
        add(resultLabel);

        toCelsiusBtn.addActionListener(this);
        toFahrenheitBtn.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String text = inputField.getText().trim();

            if (text.isEmpty()) {
                resultLabel.setText("Result: Input required!");
                return; // TC4
            }

            double value = Double.parseDouble(text);

            if (e.getSource() == toCelsiusBtn) {
                double c = (value - 32) * 5 / 9;
                resultLabel.setText("Result: " + String.format("%.2f", c) + " °C"); // TC1
            } else {
                double f = (value * 9 / 5) + 32;
                resultLabel.setText("Result: " + String.format("%.2f", f) + " °F"); // TC2, TC3
            }

        } catch (Exception ex) {
            resultLabel.setText("Result: Invalid input!"); // TC5
        }
    }

    public static void main(String[] args) {
    	System.out.println("Name: Divya Bharathi I");
    	System.out.println("Reg no: 2117240020096");
        new TempConverter();
    }
}
