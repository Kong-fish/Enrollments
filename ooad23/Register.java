import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class Register extends JFrame {
    private Map<String, String> studentCredentials = new HashMap<>();
    private JTextField nameField;
    private JPasswordField passwordField;

    public Register() {
        createUI();
    }

    private void createUI() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 300);
        this.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 2));

        JLabel nameLabel = new JLabel("Enter your name:");
        nameField = new JTextField();

        JLabel passwordLabel = new JLabel("Enter your password:");
        passwordField = new JPasswordField();

        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerStudent();
            }
        });

        this.add(inputPanel, BorderLayout.CENTER);
        this.add(registerButton, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public void registerStudent() {
        String name = nameField.getText();
        String password = new String(passwordField.getPassword());

        studentCredentials.put(name, password);

        JOptionPane.showMessageDialog(this, "Registration completed for " + name);
    }
}
