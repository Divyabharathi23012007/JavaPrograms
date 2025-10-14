import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RestaurantMenuSelectorSwing extends JFrame {

    public RestaurantMenuSelectorSwing() {
        setTitle("Restaurant Menu Selector");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Create panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Menu items and prices
        JCheckBox cbPizza = new JCheckBox("Pizza ($8.50)");
        JCheckBox cbBurger = new JCheckBox("Burger ($5.50)");
        JCheckBox cbPasta = new JCheckBox("Pasta ($7.00)");
        JCheckBox cbSalad = new JCheckBox("Salad ($4.50)");

        JSpinner spPizza = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        JSpinner spBurger = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        JSpinner spPasta = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));
        JSpinner spSalad = new JSpinner(new SpinnerNumberModel(0, 0, 10, 1));

        JButton btnCalculate = new JButton("Calculate Total");
        JLabel lblTotal = new JLabel("Total: $0.00");

        // Row 1: Pizza
        gbc.gridx = 0; gbc.gridy = 0; panel.add(cbPizza, gbc);
        gbc.gridx = 1; panel.add(spPizza, gbc);

        // Row 2: Burger
        gbc.gridx = 0; gbc.gridy = 1; panel.add(cbBurger, gbc);
        gbc.gridx = 1; panel.add(spBurger, gbc);

        // Row 3: Pasta
        gbc.gridx = 0; gbc.gridy = 2; panel.add(cbPasta, gbc);
        gbc.gridx = 1; panel.add(spPasta, gbc);

        // Row 4: Salad
        gbc.gridx = 0; gbc.gridy = 3; panel.add(cbSalad, gbc);
        gbc.gridx = 1; panel.add(spSalad, gbc);

        // Row 5: Button and total label
        gbc.gridx = 0; gbc.gridy = 4; panel.add(btnCalculate, gbc);
        gbc.gridx = 1; panel.add(lblTotal, gbc);

        // Button Action
        btnCalculate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double total = 0;
                if (cbPizza.isSelected()) total += 8.50 * (Integer) spPizza.getValue();
                if (cbBurger.isSelected()) total += 5.50 * (Integer) spBurger.getValue();
                if (cbPasta.isSelected()) total += 7.00 * (Integer) spPasta.getValue();
                if (cbSalad.isSelected()) total += 4.50 * (Integer) spSalad.getValue();

                lblTotal.setText(String.format("Total: $%.2f", total));
            }
        });

        // Add panel to frame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new RestaurantMenuSelectorSwing().setVisible(true);
        });
    }
}
