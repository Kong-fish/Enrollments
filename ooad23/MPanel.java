import javax.swing.*;
import java.awt.*;

public class MPanel extends JPanel {
    private CardLayout cardLayout;

    public MPanel(View view) {
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
            cardLayout.show(view.getMainPanel(), "RegisterPanel");
        });

        buttonPanel.add(btnRegister, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}
