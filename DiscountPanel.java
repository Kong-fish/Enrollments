import javax.swing.*;

public class DiscountPanel extends JPanel {
    public DiscountPanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel discountRateLabel = new JLabel("Discount Rate:");
        discountRateLabel.setAlignmentX(CENTER_ALIGNMENT);

        JTextField discountRateField = new JTextField(10);
        discountRateField.setMaximumSize(discountRateField.getPreferredSize());
        discountRateField.setAlignmentX(CENTER_ALIGNMENT);

        JButton updateDiscountButton = new JButton("Update Discount");
        updateDiscountButton.setAlignmentX(CENTER_ALIGNMENT);
        updateDiscountButton.addActionListener(e -> {
            double newRate;
            try {
                newRate = Double.parseDouble(discountRateField.getText());
                JOptionPane.showMessageDialog(this, "Discount rate updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid discount rate.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(Box.createVerticalStrut(20)); // Adds space at the top
        add(discountRateLabel);
        add(Box.createVerticalStrut(10)); // Adds space between label and text field
        add(discountRateField);
        add(Box.createVerticalStrut(20)); // Adds space between text field and button
        add(updateDiscountButton);
        add(Box.createVerticalStrut(20)); // Adds space at the bottom
    }
}
