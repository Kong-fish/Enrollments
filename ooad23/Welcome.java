import javax.swing.*;
import java.awt.*;

public class Welcome extends JPanel {
    private CardLayout cardLayout;

    public Welcome(View view) {
        setSize(400, 300);

        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Welcome to MMU");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 48));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new BorderLayout());
        
        JButton btnRegister = new JButton("Register New Student");
        btnRegister.addActionListener(e -> {
            cardLayout = (CardLayout) view.getMainPanel().getLayout();
            cardLayout.show(view.getMainPanel(), "Register");
        });

        buttonPanel.add(btnRegister, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
