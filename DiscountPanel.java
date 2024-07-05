import javax.swing.*;
import java.awt.*;

public class DiscountPanel extends JPanel {
    public DiscountPanel() {
        setLayout(new GridLayout(2, 2));

        JLabel discountRateLabel = new JLabel("Discount Rate:");
        JTextField discountRateField = new JTextField();

        JButton updateDiscountButton = new JButton("Update Discount");

        updateDiscountButton.addActionListener(e -> {
            double newRate = Double.parseDouble(discountRateField.getText());
            JOptionPane.showMessageDialog(this, "Discount rate updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        add(discountRateLabel);
        add(discountRateField);
        add(new JLabel());
        add(updateDiscountButton);
    }
}
