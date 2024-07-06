import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private MainPanel main;

    public WelcomePanel(MainPanel main) {
        this.main = main;
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to MMU");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        
        JButton btnRegister = new JButton("Register New Student");
        btnRegister.addActionListener(e -> main.showPanel("Register Panel"));

        buttonPanel.add(btnRegister, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
