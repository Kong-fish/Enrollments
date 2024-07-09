import javax.swing.*;

public class WelcomePanel extends JPanel {
    private MainPanel main;

    public WelcomePanel(MainPanel main) {
        this.main = main;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel titleLabel = new JLabel("Welcome to MMU");
        titleLabel. setFont(new javax.swing.plaf.FontUIResource("Arial", java.awt.Font.BOLD, 80));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setAlignmentX(CENTER_ALIGNMENT);
        add(Box.createVerticalGlue()); 
        add(titleLabel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton btnLogin = new JButton("Login");
        btnLogin.setAlignmentX(CENTER_ALIGNMENT);
        btnLogin.addActionListener(e -> main.showPanel(main.getLoginPanel())); 

        buttonPanel.add(Box.createVerticalStrut(30)); 
        buttonPanel.add(btnLogin);

        add(buttonPanel);
        add(Box.createVerticalGlue()); 
    }
}
