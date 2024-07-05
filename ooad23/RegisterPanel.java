import javax.swing.*;
import java.awt.*;

public class RegisterPanel extends JPanel {
    private JTextField studentNameField;

    public RegisterPanel(View view) {
        setLayout(new GridLayout(2, 2));
        setSize(400, 300);

        JLabel studentNameLabel = new JLabel("Student Name:");
        add(studentNameLabel);

        studentNameField = new JTextField();
        add(studentNameField);

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(e -> {
            String studentName = studentNameField.getText();
            view.getController().addStudent(studentName, null);
            JOptionPane.showMessageDialog(this, "Student register successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        
            CardLayout cardLayout = (CardLayout) view.getMainPanel().getLayout();
            cardLayout.show(view.getMainPanel(), "WelcomePanel");
        });

        

        add(new JLabel());
        add(registerButton);
    }
}
    
