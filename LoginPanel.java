import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPanel extends JPanel {
    private MainPanel mainPanel;

    public LoginPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(null);
        placeComponents(this);
    }

    private void placeComponents(JPanel panel) {
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(290, 120, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(410, 120, 165, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(290, 160, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(410, 160, 165, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(290, 200, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                // Check the user and password. This is just a simple example.
                // In a real application, you should check the user and password securely, such as hashing the password and checking it against a database.
                if ("admin".equals(user) && "1234".equals(password)) {
                    // If the login is successful, show the Welcome Panel
                    mainPanel.showPanel("Welcome Panel");
                } else {
                    JOptionPane.showMessageDialog(panel, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
