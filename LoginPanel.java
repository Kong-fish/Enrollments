import javax.swing.*;

public class LoginPanel extends JPanel {
    private MainPanel mainPanel;

    //SRP implementation = good
    public LoginPanel(MainPanel mainPanel) {
        this.mainPanel = mainPanel;
        setLayout(null);
        placeComponents(this);
    }

    private void placeComponents(JPanel panel) {
        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(290, 120, 80, 25);// manually this take 15min
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

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(410, 200, 80, 25);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String user = userText.getText();
            String password = new String(passwordText.getPassword());
            
            // only one set of username and password
            // not quite safe need improvement such as storing data in database then use try catch block better
            if ("admin".equals(user) && "1234".equals(password)) {
                // If the login is successful, show the Home Panel
                mainPanel.showPanel(mainPanel.getHomePanel());
                userText.setText("");
                passwordText.setText("");//clearing
            } else {
                JOptionPane.showMessageDialog(panel, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                // Clear the password field
                passwordText.setText("");
            }
        });
    }
}
